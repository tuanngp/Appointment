# Opportunity Management Feature

## Tổng quan
Chức năng quản lý cơ hội được xây dựng dựa trên các entity hiện có:
- **SaDeal**: Entity chính đại diện cho cơ hội
- **SaProductCategory**: Quản lý 4 danh mục cơ hội (Bảo hiểm, Dịch vụ thẻ, Tiết kiệm, Vay)
- **SaAppointment**: Quản lý lịch hẹn liên quan đến cơ hội
- **SaDealStages**: Theo dõi các giai đoạn của cơ hội

## Cài đặt

### 1. Chạy script SQL để tạo dữ liệu master
```sql

```

### 2. Khởi động ứng dụng
```bash
mvn spring-boot:run
```

## API Endpoints

### 1. Tạo cơ hội mới
**POST** `/api/opportunities/create`

**Request Body:**
```json
{
  "opportunityName": "Bảo hiểm nhân thọ cho gia đình",
  "interestedProductId": 1,
  "customerId": 123,
  "phoneNumber": "0987654321",
  "appointmentDateTime": "2024-01-15 14:30:00",
  "note": "Khách hàng quan tâm đến bảo hiểm",
  "priority": 1,
  "durationMin": 60
}
```

**Response:**
```json
{
  "id": 1,
  "opportunityName": "Bảo hiểm nhân thọ cho gia đình",
  "createdDate": "2024-01-10 10:00:00",
  "contactPhone": "0987654321",
  "email": "customer@email.com",
  "categoryName": "Bảo hiểm",
  "customerName": "Nguyễn Văn A",
  "dealStatus": "O"
}
```

### 2. Tìm kiếm cơ hội
**POST** `/api/opportunities/search`

**Request Body:**
```json
{
  "categoryCode": "INSURANCE",
  "customerId": 123,
  "fromDate": "2024-01-01",
  "toDate": "2024-01-31",
  "customerName": "Nguyễn",
  "dealStatus": "O"
}
```

### 3. Chi tiết cơ hội
**POST** `/api/opportunities/detail`

**Request Body:**
```json
{
  "opportunityId": 1
}
```

**Response:**
```json
{
  "opportunityInfo": {
    "id": 1,
    "opportunityName": "Bảo hiểm nhân thọ cho gia đình",
    "opportunityType": "Bảo hiểm",
    "customerName": "Nguyễn Văn A",
    "phoneNumber": "0987654321",
    "productName": "Bảo hiểm nhân thọ",
    "description": "Khách hàng quan tâm đến bảo hiểm",
    "dealStatus": "O"
  },
  "stageStatus": {
    "approachDate": "2024-01-10 10:00:00",
    "profileCreationDate": "2024-01-12 14:00:00",
    "approvalWaitingDate": null,
    "resultDate": null
  },
  "appointmentInfo": {
    "appointmentId": 1,
    "title": "Appointment for Bảo hiểm nhân thọ cho gia đình",
    "appointmentDateTime": "2024-01-15 14:30:00",
    "note": "Khách hàng quan tâm đến bảo hiểm",
    "status": "A",
    "durationMin": 60,
    "contactMobile": "0987654321"
  },
  "contractInfo": {
    "contractNumber": null,
    "totalFee": null,
    "status": "O",
    "description": "Khách hàng quan tâm đến bảo hiểm"
  }
}
```

### 4. Cơ hội theo danh mục
**POST** `/api/opportunities/by-category`

**Request Body:**
```json
{
  "categoryCode": "INSURANCE",
  "customerId": 123,
  "dealStatus": "O"
}
```

### 5. Danh sách danh mục cơ hội
**POST** `/api/opportunities/categories`

**Response:**
```json
[
  {
    "categoryId": 1,
    "categoryCode": "INSURANCE",
    "categoryName": "Bảo hiểm",
    "categoryShortName": "Bảo hiểm",
    "description": "Các sản phẩm bảo hiểm"
  },
  {
    "categoryId": 2,
    "categoryCode": "CARD_SERVICE",
    "categoryName": "Dịch vụ thẻ",
    "categoryShortName": "Dịch vụ thẻ",
    "description": "Các dịch vụ liên quan đến thẻ"
  }
]
```

## Danh mục cơ hội

1. **INSURANCE** - Bảo hiểm
2. **CARD_SERVICE** - Dịch vụ thẻ  
3. **SAVINGS** - Tiết kiệm
4. **LOAN** - Vay

## Giai đoạn cơ hội

1. **Tiếp cận** - Giai đoạn tiếp cận khách hàng
2. **Tạo hồ sơ** - Giai đoạn tạo hồ sơ cho khách hàng
3. **Chờ duyệt** - Giai đoạn chờ duyệt hồ sơ
4. **Kết quả hồ sơ** - Giai đoạn có kết quả hồ sơ

## Lưu ý

- Tất cả API sử dụng method POST theo pattern hiện có
- Các biến sử dụng tiếng Anh
- Tích hợp với hệ thống appointment hiện có
- Sử dụng pagination cho API search
- Validation đầy đủ cho các request
