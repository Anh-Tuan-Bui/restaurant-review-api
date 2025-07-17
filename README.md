# 🍽️ Restaurant Review API – Hệ thống đánh giá nhà hàng + Phân tích NLP

[![Java](https://img.shields.io/badge/Java-21-blue)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-green)]()
[![Redis](https://img.shields.io/badge/Redis-Leaderboard-critical)]()
[![License](https://img.shields.io/badge/license-MIT-lightgrey)]()

## 📘 Giới thiệu
**Restaurant Review API** là một hệ thống backend RESTful cho phép người dùng gửi đánh giá nhà hàng. Nội dung review sẽ được phân tích bằng NLP để xác định sentiment (tích cực/tiêu cực), và kết quả sẽ được sử dụng để cập nhật **bảng xếp hạng (leaderboard)** trong Redis.

---

## 🎯 Mục tiêu dự án

- Thực hành xây dựng API backend theo hướng **microservice nhẹ** (Java ↔ Python).
- Áp dụng **NLP cơ bản** để tự động phân loại đánh giá người dùng.
- Thử nghiệm **Redis Sorted Set** để làm bảng xếp hạng (leaderboard).
- Sản phẩm phục vụ cho việc **demo kỹ năng Backend Java** trong CV xin việc.

## ✨ Tính năng nổi bật
- 👤 **Phân quyền người dùng**
  - `ADMIN`: quản lý toàn bộ hệ thống
  - `USER`: gửi review và xem bảng xếp hạng

- 📝 **Review nhà hàng**
  - Gửi đánh giá với `restaurantId` và `content`
  - Hệ thống tự động phân tích sentiment (tích cực/tiêu cực)
  - Lưu kết quả vào DB + cập nhật Redis

- 🧠 **Xử lý NLP**
  - Phân tích cảm xúc từ nội dung đánh giá
  - tích hợp thư viện NLP có sẵn

- 🏆 **Leaderboard bằng Redis**
  - Redis được dùng để lưu bảng xếp hạng nhà hàng theo lượng review tích cực
  - Cập nhật real-time khi có đánh giá mới

- 📊 **Thống kê / truy vấn**
  - Xem số lượng review theo nhà hàng
  - Xem điểm sentiment trung bình
  - Xem top nhà hàng tích cực nhất

---

## 🏗️ Kiến trúc tổng thể
- Mô hình phân tầng: `Controller → Service → Repository`
- Tích hợp NLP tại tầng Service
- Redis được dùng tại tầng caching/ranking
- Sử dụng DTO để tách entity và payload request

---

## 📁 Cấu trúc thư mục chính
```
src/
├── config/ # Bảo mật, ModelMapper, Redis, v.v...
├── controller/ # REST API
├── dto/ # Request/Response models
├── entity/ # Entity ánh xạ với bảng DB
├── repository/ # Interface thao tác với DB
├── service/ # Logic nghiệp vụ
│ └── impl/ # Implement của service
├── exception # Xử lý lỗi tập trung
└── application.yml # Cấu hình
```

---

## ⚙️ Công nghệ sử dụng

| Thành phần | Công nghệ |
|------------|-----------|
| Ngôn ngữ   | Java 21, Python 3.10 |
| Backend    | Spring Boot 3 |
| ORM        | JPA + Hibernate |
| NLP        | Transformers |
| Caching    | Redis (Sorted Set) |
| Bảo mật    | Spring Security + JWT |
| Database   | MySQL |
| Đóng gói   | Docker |
| Build Tool | Maven |

---

## ▶️ Hướng dẫn chạy dự án
### 1. Yêu cầu
- Java 17+
- Maven 3.8+
- MySQL 8+
- Redis (có thể chạy Docker)
- Postman để test API

### 2. Cài đặt
#### Clone repo
```bash
# Clone repo
git clone https://github.com/Anh-Tuan-Bui/restaurant-review-api.git
```

#### Backend Java
1. Chạy bằng intelliJ
    - Mở project trong IntelliJ
    - Cấu hình file src/main/resources/application.yml theo MySQL và Redis của bạn
    - Chạy file RestaurantReviewApplication.java

2. Chạy bằng terminal
    ```bash
    # Di chuyển đến thư mục restaurant-review-api
    cd restaurant-review-api

    # Cấu hình database & Redis trong file application.yml

    # Build và chạy
    ./mvnw spring-boot:run
    ```

#### NLP Service (Python)
```bash
cd nlp-service
pip install -r requirements.txt
uvicorn app:app --reload
```
## 🧠 Xử lý NLP Sentiment
NLP service nhận nội dung đánh giá và trả về sentiment (positive hoặc negative) để Java sử dụng.
- Sau khi người dùng gửi đánh giá, hệ thống sẽ phân tích sentiment của review:
    - POSITIVE: điểm +1
    - NEGATIVE: điểm -1

- Kết quả sẽ ảnh hưởng trực tiếp tới Redis Leaderboard

Bạn có thể tích hợp:
- Rule-based: tìm keyword tích cực/tiêu cực
- Hoặc thư viện: VnCoreNLP, TextBlob (nếu xử lý tiếng Anh)

---

## 🏆 Redis Leaderboard
- Redis lưu trữ xếp hạng nhà hàng bằng Sorted Set
- Cập nhật mỗi khi có đánh giá mới
- Có thể truy vấn:
    - Top 5 nhà hàng được yêu thích nhất
    - Thứ hạng của 1 nhà hàng cụ thể

---

## 🌐 Demo API (ví dụ)
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| `POST` | `/api/restaurants` | Tạo nhà hàng |
| `POST` | `/api/reviews` | Gửi đánh giá |
| `GET`  | `/api/restaurants/{id}/reviews` | Xem tất cả review của nhà hàng |
| `GET`  | `/api/leaderboard` | Xem bảng xếp hạng từ Redis |

---

## 📄 License
Dự án mang tính học tập – mở và tự do sử dụng cho cộng đồng.