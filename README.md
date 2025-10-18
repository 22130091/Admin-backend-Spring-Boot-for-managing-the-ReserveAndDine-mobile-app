# Admin-backend-Spring-Boot-for-managing-the-ReserveAndDine-mobile-app

Backend for Mobile App in admin site (Spring Boot)

## Mục lục

-   [Tính năng chính](#tính-năng-chính)
-   [Các công nghệ sử dụng](#các-công-nghệ-sử-dụng)
-   [Cài đặt và Chạy dự án](#cài-đặt-và-chạy-dự-án)
    -   [Yêu cầu](#yêu-cầu)
    -   [Cài đặt](#cài-đặt)
    -   [Cấu hình](#cấu-hình)
    -   [Chạy ứng dụng](#chạy-ứng-dụng)
-   [Tài liệu API](#tài-liệu-api-endpoints)

---

## Tính năng chính

-   **Thống kê & Báo cáo:** Cung cấp API thống kê doanh thu, số lượng đơn đặt bàn (thành công/hủy).
-   **Quản lý Người dùng:** Xem danh sách, khóa/mở khóa tài khoản người dùng.
-   **Quản lý Nhà hàng:** Cấu hình thông tin nhà hàng, quản lý bàn.
-   **Duyệt Đơn đặt bàn:** Xác nhận hoặc từ chối các yêu cầu đặt bàn từ người dùng.

---

## Các công nghệ sử dụng

-   **Framework:** [Spring Boot](https://spring.io/projects/spring-boot)
-   **Ngôn ngữ:** [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) 

-   **Truy cập dữ liệu (Data Access):**
    -   [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
    -   [MySQL Connector/J](https://dev.mysql.com/doc/connector-j/en/): Trình điều khiển (JDBC Driver) kết nối database MySQL.

-   **Bảo mật (Security):**
    -   [Spring Security](https://spring.io/projects/spring-security): Xử lý xác thực và phân quyền cho Admin.
    -   [Spring OAuth2 Client](https://docs.spring.io/spring-security/reference/servlet/oauth2/client/index.html): Hỗ trợ đăng nhập Admin qua Google, ...

-   **Validation:**
    -   [Spring Boot Validation](https://docs.spring.io/spring-boot/docs/current/reference/html/io.html#io.validation): Kiểm tra tính hợp lệ của dữ liệu đầu vào.

-   **Build Tool:**
    -   [Maven](https://maven.apache.org/) (hoặc Gradle)

-   **Tiện ích (Utilities):**
    -   [Lombok](https://projectlombok.org/): Giảm thiểu code soạn sẵn (getters, setters...).

---

## Cài đặt và Chạy dự án

### Yêu cầu

Dưới đây là các phần mềm bạn cần cài đặt trước khi chạy dự án:

-   [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (hoặc phiên bản tương ứng)
-   [Maven 3.x+](https://maven.apache.org/download.cgi)
-   [MySQL Server](https://www.mysql.com/downloads/)

### Cấu hình

Bạn cần thiết lập các thông tin trong file `src/main/resources/application.properties` để dự án có thể kết nối tới database.
    ```properties
    # Cổng chạy của DỊCH VỤ ADMIN
    server.port=8081

    # Cấu hình kết nối Database MySQL
    spring.datasource.url=jdbc:mysql://localhost:3306/admin_db
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # Cấu hình JPA/Hibernate
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    ```

---
