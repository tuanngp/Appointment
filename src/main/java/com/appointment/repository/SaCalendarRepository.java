package com.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appointment.entity.SaCalendar;
import com.appointment.enums.CalendarType;

public interface SaCalendarRepository extends JpaRepository<SaCalendar, Long> {
    @Query("SELECT s FROM SaCalendar s WHERE s.type = :type AND EXTRACT(MONTH FROM s.eventDate) = :month AND EXTRACT(DAY FROM s.eventDate) = :day")
    List<SaCalendar> findByTypeAndEventDateMonthAndEventDateDay(
            @Param("type") CalendarType type,
            @Param("month") int month,
            @Param("day") int day
    );

    @Query("SELECT s FROM SaCalendar s WHERE s.type = :type AND EXTRACT(MONTH FROM s.eventDate) = :month AND EXTRACT(DAY FROM s.eventDate) = :day AND s.custId = :custId")
    List<SaCalendar> findByTypeAndEventDateMonthAndEventDateDayAndCustId(
            @Param("type") CalendarType type,
            @Param("month") int month,
            @Param("day") int day,
            @Param("custId") Long custId
    );
}
