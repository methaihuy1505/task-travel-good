# Travel Good System - Quản Lý Chuyến Đi (Trips) & Lộ Trình (Destinations)

Hệ thống **Travel Good** được thiết kế để hỗ trợ người dùng lập kế hoạch du lịch cá nhân một cách khoa học. Dự án tập trung vào việc tối ưu hóa trình tự các điểm dừng chân, quản lý trạng thái chuyến đi và đảm bảo trải nghiệm người dùng mượt mà trên nền tảng Web Java truyền thống.

---

## Tính năng nổi bật

### 1. Quản lý Chuyến đi (Trip Module)

- **Lập kế hoạch linh hoạt:** Cho phép khởi tạo chuyến đi với định danh người dùng, hỗ trợ đặt tên tùy chỉnh (VD: Du lịch hè 2026, Công tác Đà Nẵng).
- **Quản lý trạng thái:** Hệ thống tự động thiết lập trạng thái `PLANNED` khi tạo mới. Hỗ trợ thay đổi trạng thái linh hoạt thông qua nút Toggle.
- **Cập nhật & Hoàn tác:** Chức năng chỉnh sửa tên chuyến đi nhanh chóng và cơ chế **Toggle Cancel/Undo** thông minh giúp người dùng thay đổi trạng thái chỉ với một cú click ngay tại danh sách.
- **Xóa dữ liệu an toàn:** Tích hợp cơ chế xóa đồng bộ (Cascade Delete) - khi xóa chuyến đi, toàn bộ lộ trình liên quan sẽ được tự động dọn dẹp để tối ưu dữ liệu.

### 2. Quản lý Lộ trình chi tiết (Destination Module)

- **Sắp xếp theo trình tự (Ordering):** Mỗi điểm đến được gán một chỉ số `order_index`, cho phép hệ thống hiển thị lộ trình theo đúng thứ tự ưu tiên của người dùng.
- **Liên kết dữ liệu:** Tự động bắt cặp điểm đến với Chuyến đi thông qua `trip_id`, đảm bảo dữ liệu hiển thị đúng ngữ cảnh của từng hành trình.
- **Giao diện trực quan:** Sử dụng bảng danh sách kết hợp Form thêm mới ngay tại một màn hình, tích hợp CSS Bootstrap 5 cho trải nghiệm hiện đại.
- **Việt hóa hoàn toàn:** Hỗ trợ nhập và hiển thị tiếng Việt (UTF-8) từ giao diện xuống Database, đảm bảo không lỗi font.

---

## Công nghệ sử dụng & Phiên bản

### Backend (Monolithic Architecture)

- **Ngôn ngữ:** Java 21
- **Cấu trúc:** MVC (Model - View - Controller)
- **Servlet Container:** Apache Tomcat 7 (via Maven Plugin)
- **Database Access:** JDBC (Java Database Connectivity)
- **Database:** MySQL 9.0.2
- **Thư viện:** - `mysql-connector-j`: Kết nối cơ sở dữ liệu MySQL.
  - `JSTL`: Xử lý logic hiển thị dữ liệu trên trang JSP.

### Frontend (Giao diện người dùng)

- **Template Engine:** JSP (JavaServer Pages)
- **Styling:** Bootstrap 5.3 (CDN) - Giao diện Responsive & Hiện đại.
- **Font:** Google Fonts (Inter) & Material Icons.

---

## Kiến trúc dữ liệu (Database Schema)

- `users`: Lưu trữ thông tin tài khoản người dùng.
- `trips`: Quản lý thông tin chuyến đi (Tên, Trạng thái), liên kết khóa ngoại với `users`.
- `destinations`: Lưu trữ các điểm dừng trong lộ trình, liên kết khóa ngoại với `trips`.

---

## Giao diện ứng dụng (Screenshots)

|                                           Trip Management Page                                           |
| :------------------------------------------------------------------------------------------------------: |
| ![TripManagement](https://github.com/methaihuy1505/task-travel-good/blob/main/images/TripManagement.png) |

|                                              Destination Management Page                                               |
| :--------------------------------------------------------------------------------------------------------------------: |
| ![DestinationManagement](https://github.com/methaihuy1505/task-travel-good/blob/main/images/DestinationManagement.png) |

---

## Hướng dẫn cài đặt và chạy dự án (Local)

### 1. Yêu cầu hệ thống

- Java Development Kit (JDK 21)
- Maven 3.x
- MySQL Server.

### 2.2. Thiết lập Database

1.  **Tạo database mới:**
    Sử dụng MySQL Workbench hoặc Command Line để tạo cơ sở dữ liệu hỗ trợ tiếng Việt:

    ```sql
    CREATE DATABASE travel_good_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
    ```

    Sau đó chạy query trong thư mục db

2.  **Cấu hình kết nối:**
    Mở file `src/main/java/util/DBConnection.java` và cập nhật thông tin tài khoản MySQL của bạn:
    - `user`: Tên đăng nhập (mặc định thường là `root`).
    - `pass`: Mật khẩu MySQL của bạn.

---

## 3. Khởi chạy dự án (Local)

Dự án tích hợp sẵn Maven Tomcat Plugin, giúp bạn chạy ngay mà không cần cài đặt Apache Tomcat thủ công:

1.  Mở terminal tại thư mục gốc của dự án.
2.  Chạy lệnh Maven:
    ```bash
    mvn tomcat7:run
    ```
3.  Truy cập ứng dụng qua trình duyệt tại địa chỉ: `http://localhost:8080`

---

## 4. Danh sách chức năng chính (Routes)

Hệ thống điều hướng thông qua các Servlet Action chuyên biệt:

| URL                         | Method | Mô tả                                                        |
| :-------------------------- | :----- | :----------------------------------------------------------- |
| `/trips`                    | GET    | Xem danh sách toàn bộ chuyến đi đã tạo.                      |
| `/trips?action=edit`        | GET    | Giao diện chỉnh sửa tên chuyến đi.                           |
| `/trips?action=toggle`      | GET    | **Hủy hoặc Hoàn tác** trạng thái chuyến đi ngay lập tức.     |
| `/trips?action=delete`      | GET    | Xóa vĩnh viễn chuyến đi và các lộ trình liên quan (Cascade). |
| `/destinations?tripId={id}` | GET    | Xem và quản lý lộ trình chi tiết của một chuyến đi cụ thể.   |
| `/destinations`             | POST   | Lưu điểm dừng chân mới vào Database.                         |

---

## Tác giả

[methaihuy1505](https://github.com/methaihuy1505)
