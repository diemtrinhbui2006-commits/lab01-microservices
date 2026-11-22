# lab01-microservices / Coffee & Milk Tea Shop Management

Hệ thống Spring Boot Microservices: **Quản lý quán nước / Quán cà phê / Trà sữa**
- **Sinh viên:** Bùi Thị Diễm Trinh
- **MSSV:** 2124110287
- **Lớp:** CCQ2411D

---

## Cấu trúc thư mục đồ án:
```
lab01-microservices/
├── product-service/       # Service 1: Quản lý thực đơn, đồ uống, topping (MySQL - Port 8081)
├── order-service/         # Service 2: Quản lý đơn hàng, chi tiết order (SQL Server - Port 8082)
├── frontend/              # Giao diện ứng dụng
│   ├── index.html         # Giao diện tổng quan tích hợp Lab 01
│   ├── admin/             # Giao diện quản trị quán (Quản lý, Thu ngân, Pha chế)
│   └── client/            # Giao diện đặt món của khách hàng
├── .gitignore
└── README.md
```

## Các Service chính:
- **Product Service**: Cổng 8081 | Quản trị thực đơn, danh mục đồ uống | CSDL: **MySQL**
- **Order Service**: Cổng 8082 | Quản lý giỏ hàng và đơn hàng | CSDL: **SQL Server**
