-- Script tao Database va bang cho Product Service (MySQL)
-- De tai: Quan ly quan nuoc / Quan ca phe / Tra sua

CREATE DATABASE IF NOT EXISTS product_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE product_db;

-- 1. Bang loai san pham (Danh muc thuc don)
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

-- 2. Bang thuong hieu / nguon goc
CREATE TABLE IF NOT EXISTS brands (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

-- 3. Bang san pham (Do uong)
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    price DECIMAL(12, 2) NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    category_id BIGINT,
    brand_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL,
    FOREIGN KEY (brand_id) REFERENCES brands(id) ON DELETE SET NULL
);

-- 4. Bang hinh anh san pham
CREATE TABLE IF NOT EXISTS product_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    image_url VARCHAR(255) NOT NULL,
    is_primary BOOLEAN DEFAULT FALSE,
    product_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- Du lieu mau ban dau
INSERT INTO categories (name, description) VALUES
('Cà phê', 'Cà phê pha phin truyền thống và cà phê pha máy'),
('Trà sữa', 'Các món trà sữa đậm vị trà kèm topping hấp dẫn'),
('Trà trái cây', 'Trà hoa quả nhiệt đới tươi mát giải nhiệt'),
('Topping', 'Trân châu, thạch, pudding ăn kèm');

INSERT INTO brands (name, description) VALUES
('Arabica Cầu Đất', 'Hạt cà phê nguyên chất Lâm Đồng'),
('Trà Oolong Bảo Lộc', 'Lá trà Oolong thượng hạng');

INSERT INTO products (name, description, price, is_available, category_id, brand_id) VALUES
('Cà phê muối', 'Cà phê nguyên chất kết hợp lớp kem muối béo ngậy', 35000, true, 1, 1),
('Trà sữa Oolong nướng', 'Trà sữa đậm vị thơm ngậy sốt nướng', 45000, true, 2, 2),
('Trà đào cam sả', 'Trà đào thanh ngọt kết hợp sả và cam vàng', 40000, true, 3, 2),
('Trân châu đen đường đen', 'Topping trân châu dẻo dai', 10000, true, 4, 2);
