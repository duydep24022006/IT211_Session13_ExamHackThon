# BÁO CÁO PROJECT HACKATHON API

## Môn học: Lập trình Web Service / RESTful API

## Tên project: Hackathon Management API

* Họ và tên sinh viên: Lê Bá Duy
* Mã sinh viên: B24DTCN205
* Lớp: CNTT4
* Ngày báo cáo: 28/05/2026

---

# I. GIỚI THIỆU DỰ ÁN

## 1. Mục tiêu dự án

Project Hackathon Management API được xây dựng nhằm mục đích:

* Thực hành xây dựng RESTful API bằng Spring Boot.
* Hiểu cách tổ chức backend theo mô hình phân tầng.
* Thực hành CRUD với MySQL.
* Áp dụng Spring Data JPA.
* Thực hành validate dữ liệu bằng annotation.
* Hiểu cách xử lý exception và HTTP status code.
* Làm quen với Pagination và ResponseEntity.

---

## 2. Chức năng chính của hệ thống

### Chức năng 01: Nghiệp vụ quản lý Hackathon

Hệ thống hỗ trợ:

* Lấy danh sách hackathon.
* Lấy chi tiết hackathon theo ID.
* Thêm hackathon mới.
* Cập nhật thông tin hackathon.
* Xóa hackathon.
* Tìm kiếm hackathon theo tên.

### Chức năng 02: Quản lý người tham gia

Hệ thống hỗ trợ:

* Thêm người tham gia.
* Xóa người tham gia.
* Xem danh sách người tham gia.
* Quản lý thông tin đội thi.
* Quản lý trạng thái tham gia.

---

# II. CÔNG NGHỆ SỬ DỤNG

| Công nghệ       | Mô tả                     |
| --------------- | ------------------------- |
| Java 17         | Ngôn ngữ lập trình        |
| Spring Boot 3.x | Framework backend         |
| MySQL           | Hệ quản trị cơ sở dữ liệu |
| Spring Data JPA | ORM framework             |
| Gradle          | Quản lý dependency        |
| Lombok          | Giảm code boilerplate     |
| Postman         | Test API                  |

---

# III. CẤU TRÚC PROJECT

```text
src/main/java/com/example/hackathon/
 ├── controller/
 ├── dto/
 ├── entity/
 ├── exception/
 ├── repository/
 ├── service/
 ├── config/
 └── HackathonApplication.java
```

---

# IV. MÔ TẢ CÁC THÀNH PHẦN

## 1. Entity

### Hackathon.java

Entity Hackathon đại diện cho bảng hackathon trong database.

### Các thuộc tính chính

| Thuộc tính  | Kiểu dữ liệu |
| ----------- | ------------ |
| id          | Long         |
| title       | String       |
| description | String       |
| startDate   | LocalDate    |
| endDate     | LocalDate    |
| location    | String       |

---

### Participant.java

Entity Participant đại diện cho người tham gia hackathon.

| Thuộc tính | Kiểu dữ liệu |
| ---------- | ------------ |
| id         | Long         |
| fullName   | String       |
| email      | String       |
| teamName   | String       |

---

## 2. Repository

Repository kế thừa JpaRepository để thao tác với database.

### Các repository chính

* HackathonRepository
* ParticipantRepository

### Chức năng

* CRUD dữ liệu.
* Tìm kiếm dữ liệu.
* Pagination.

---

## 3. DTO

### Các DTO chính

* HackathonRequestDTO
* HackathonResponseDTO
* ParticipantRequestDTO
* ParticipantResponseDTO

### Vai trò DTO

* Nhận dữ liệu từ client.
* Trả dữ liệu về client.
* Giảm dữ liệu dư thừa.
* Bảo mật entity.

---

## 4. Service Layer

Service xử lý toàn bộ logic nghiệp vụ.

### Chức năng chính

* Validate dữ liệu.
* CRUD hackathon.
* CRUD participant.
* Xử lý exception.
* Pagination.

---

## 5. Controller

Controller tiếp nhận request từ client.

### Các endpoint chính

| Method | Endpoint             | Chức năng               |
| ------ | -------------------- | ----------------------- |
| GET    | /api/hackathons      | Lấy danh sách hackathon |
| GET    | /api/hackathons/{id} | Lấy chi tiết            |
| POST   | /api/hackathons      | Thêm hackathon          |
| PUT    | /api/hackathons/{id} | Cập nhật                |
| DELETE | /api/hackathons/{id} | Xóa hackathon           |

---

# V. THIẾT KẾ HỆ THỐNG

## 1. Use Case

### Tác nhân

* Admin
* Participant

### Chức năng quản lý Hackathon

* Thêm hackathon
* Cập nhật hackathon
* Xóa hackathon
* Xem danh sách hackathon

### Chức năng quản lý Participant

* Đăng ký tham gia
* Xem thông tin đội thi
* Quản lý participant

> Chèn hình Use Case tại đây

---

## 2. Activity Diagram

### Quy trình thêm hackathon

1. Admin gửi request thêm hackathon.
2. Controller nhận request.
3. Service validate dữ liệu.
4. Repository lưu database.
5. Trả response thành công.

### Quy trình đăng ký participant

1. Người dùng nhập thông tin.
2. Hệ thống validate dữ liệu.
3. Lưu thông tin participant.
4. Trả kết quả thành công.

> Chèn hình Activity Diagram tại đây

---

## 3. Sequence Diagram

### Quy trình lấy danh sách hackathon

* Client gửi request GET.
* Controller gọi Service.
* Service gọi Repository.
* Repository truy vấn MySQL.
* Trả dữ liệu về client.

### Quy trình đăng ký participant

* Client gửi request POST.
* Controller nhận request.
* Service validate dữ liệu.
* Repository lưu database.
* Trả kết quả.

> Chèn hình Sequence Diagram tại đây

---

## 4. Class Diagram

### Lớp Hackathon

| Thuộc tính  | Kiểu   |
| ----------- | ------ |
| id          | Long   |
| title       | String |
| description | String |
| location    | String |

---

### Lớp Participant

| Thuộc tính | Kiểu   |
| ---------- | ------ |
| id         | Long   |
| fullName   | String |
| email      | String |
| teamName   | String |

---

### Quan hệ giữa các lớp

* Một Hackathon có nhiều Participant.
* Controller gọi Service.
* Service gọi Repository.
* Repository thao tác với Entity.

> Chèn hình Class Diagram tại đây

---

# VI. CẤU HÌNH HỆ THỐNG

## application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hackathon
spring.datasource.username=root
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update

server.port=8080
```

---

# VII. KẾT QUẢ ĐẠT ĐƯỢC

Sau khi hoàn thành project:

* Xây dựng thành công RESTful API.
* Kết nối MySQL thành công.
* Thực hiện CRUD đầy đủ.
* Áp dụng Pagination thành công.
* Validate dữ liệu hoạt động tốt.
* Xử lý exception đúng chuẩn RESTful.
* Tổ chức project theo mô hình phân tầng.

---

# VIII. KHÓ KHĂN VÀ CÁCH KHẮC PHỤC

## 1. Validate dữ liệu chưa đúng

### Khắc phục

* Sử dụng annotation validation.

---

## 2. Lỗi MySQL connection

### Khắc phục

* Kiểm tra datasource trong application.properties.

---

## 3. Pagination bị lỗi page index

### Khắc phục

* Kiểm tra giá trị page và size trước khi query.

---

# IX. KẾT LUẬN

Qua project này em đã:

* Hiểu rõ cách xây dựng RESTful API bằng Spring Boot.
* Thành thạo CRUD với MySQL.
* Hiểu cách tổ chức backend theo mô hình nhiều tầng.
* Hiểu cách validate dữ liệu.
* Có thêm kinh nghiệm xử lý exception và pagination.

Project giúp em hiểu rõ hơn về quy trình phát triển backend thực tế và là nền tảng để phát triển các hệ thống lớn hơn trong tương lai.
