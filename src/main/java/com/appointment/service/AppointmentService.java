package com.appointment.service;

import com.appointment.dto.request.CreateAppointmentRequest;
import com.appointment.dto.request.GetAppointmentsRequest;
import com.appointment.dto.request.GetAppointmentHistoryRequest;
import com.appointment.dto.response.AppointmentResponse;
import com.appointment.entity.SaAppointment;
import com.appointment.entity.SaCalendar;
import com.appointment.entity.SaAddressBook;
import com.appointment.enums.AppointmentStatus;
import com.appointment.enums.CalendarType;
import com.appointment.enums.CalenderStatus;
import com.appointment.exception.ResourceNotFoundException;
import com.appointment.repository.SaAppointmentRepository;
import com.appointment.repository.SaCalendarRepository;
import com.appointment.repository.SaAddressBookRepository;
import jakarta.persistence.criteria.Predicate;
import com.appointment.mapper.AppointmentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final SaAppointmentRepository appointmentRepository;
    private final SaCalendarRepository calendarRepository;
    private final SaAddressBookRepository addressBookRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(SaAppointmentRepository appointmentRepository,
                              SaCalendarRepository calendarRepository,
                              SaAddressBookRepository addressBookRepository,
                              AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.calendarRepository = calendarRepository;
        this.addressBookRepository = addressBookRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Transactional
    public AppointmentResponse createAppointment(CreateAppointmentRequest request) {
        SaAppointment appointment = appointmentMapper.toEntity(request);

        appointment.setStatus(AppointmentStatus.A);

        SaCalendar calendar = null;
        if (request.getCalendarId() != null) {
            calendar = calendarRepository.findById(request.getCalendarId())
                    .orElseThrow(() -> new RuntimeException("Calendar not found with ID: " + request.getCalendarId()));
        } else {
            calendar = new SaCalendar();
            calendar.setType(CalendarType.A);
            calendar.setTitle(appointment.getTitle());
            calendar.setDescription(appointment.getNote());
            calendar.setEventDate(appointment.getDueDateTime().toLocalDate());
            calendar.setStatus(CalenderStatus.A);
            calendar.setCustId(appointment.getCustomer() != null ? appointment.getCustomer().getCustomerId() : null);
            calendar = calendarRepository.save(calendar);
        }
        appointment.setCalendar(calendar);

        SaAppointment savedAppointment = appointmentRepository.save(appointment);
        return createAppointmentResponseWithAddress(savedAppointment);
    }

    public Page<AppointmentResponse> getAppointments(GetAppointmentsRequest request, Pageable pageable) {
        Specification<SaAppointment> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getStatus() != null && !request.getStatus().toString().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("status"), request.getStatus()));
            }
            if (request.getCustomerId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("customer").get("customerId"), request.getCustomerId()));
            }
            if (request.getStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dueDateTime"), request.getStartDate()));
            }
            if (request.getEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dueDateTime"), request.getEndDate()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<SaAppointment> appointments = appointmentRepository.findAll(spec, pageable);
        return appointments.map(this::createAppointmentResponseWithAddress);
    }

    @Transactional
    public AppointmentResponse updateAppointmentStatus(Long id, AppointmentStatus newStatus) {
        SaAppointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));

        appointment.setStatus(newStatus);
        appointment.setUpdatedAt(LocalDateTime.now());
        appointment.setUpdatedBy(1L); // Replace with actual user ID

        SaAppointment updatedAppointment = appointmentRepository.save(appointment);
        return createAppointmentResponseWithAddress(updatedAppointment);
    }

    public List<AppointmentResponse> getAppointmentHistoryLast30Days(GetAppointmentHistoryRequest request) {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        LocalDateTime now = LocalDateTime.now();

        Specification<SaAppointment> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.between(root.get("dueDateTime"), thirtyDaysAgo, now));

            if (request.getCustomerId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("customer").get("customerId"), request.getCustomerId()));
            }
            if (request.getStatus() != null && !request.getStatus().isEmpty()) {
                try {
                    AppointmentStatus enumStatus = AppointmentStatus.valueOf(request.getStatus());
                    predicates.add(criteriaBuilder.equal(root.get("status"), enumStatus));
                } catch (IllegalArgumentException e) {
                    predicates.add(criteriaBuilder.equal(root.get("status"), AppointmentStatus.A));
                }
            } else {
                predicates.add(criteriaBuilder.notEqual(root.get("status"), AppointmentStatus.A));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        List<SaAppointment> history = appointmentRepository.findAll(spec);
        return history.stream()
                .map(this::createAppointmentResponseWithAddress)
                .collect(Collectors.toList());
    }

    /**
     * Tạo AppointmentResponse với thông tin địa chỉ customer
     */
    private AppointmentResponse createAppointmentResponseWithAddress(SaAppointment appointment) {
        AppointmentResponse response = appointmentMapper.toResponse(appointment);

        // Lấy thông tin địa chỉ customer
        SaAddressBook addressBook = getCustomerAddress(
            appointment.getCustomer() != null ? appointment.getCustomer().getCustomerId() : null,
            appointment.getAddrBookId()
        );

        // Set thông tin địa chỉ vào response
        if (addressBook != null) {
            response.setAddressTitle(addressBook.getTitle());
            response.setAddressLine(addressBook.getAddressLine());
            response.setFullAddress(addressBook.getFullAddress());
            response.setLongitude(addressBook.getLongitude());
            response.setLatitude(addressBook.getLatitude());
        }

        return response;
    }

    /**
     * Lấy thông tin địa chỉ customer
     * @param custId ID của customer
     * @param addrBookId ID của địa chỉ cụ thể (có thể null)
     * @return SaAddressBook hoặc null nếu không tìm thấy
     */
    private SaAddressBook getCustomerAddress(Long custId, Long addrBookId) {
        // Nếu có addrBookId cụ thể, lấy theo ID đó
        if (addrBookId != null) {
            return addressBookRepository.findById(addrBookId).orElse(null);
        }

        // Nếu không có addrBookId nhưng có custId, lấy địa chỉ mặc định
        if (custId != null) {
            return addressBookRepository.findFirstByCustomer_CustomerIdOrderByOrderNoAsc(custId).orElse(null);
        }

        return null;
    }

}