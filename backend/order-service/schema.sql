-- Script tao Database va bang cho Order Service (SQL Server)
-- De tai: Quan ly quan nuoc / Quan ca phe / Tra sua

IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'order_db')
BEGIN
    CREATE DATABASE order_db;
END
GO

USE order_db;
GO

-- 1. Bang don hang (orders)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='orders' and xtype='U')
BEGIN
    CREATE TABLE orders (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        order_code VARCHAR(50) NOT NULL UNIQUE,
        customer_name NVARCHAR(100),
        customer_phone VARCHAR(20),
        table_number INT,
        order_type VARCHAR(20) DEFAULT 'DINE_IN', -- DINE_IN (tai ban), TAKE_AWAY (mang ve)
        total_amount DECIMAL(12, 2) NOT NULL,
        status VARCHAR(30) DEFAULT 'PENDING',    -- PENDING, PREPARING, COMPLETED, CANCELLED
        created_at DATETIME2 DEFAULT GETDATE()
    );
END
GO

-- 2. Bang chi tiet don hang (order_items)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='order_items' and xtype='U')
BEGIN
    CREATE TABLE order_items (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        order_id BIGINT NOT NULL,
        product_id BIGINT NOT NULL,
        product_name NVARCHAR(150) NOT NULL,
        unit_price DECIMAL(12, 2) NOT NULL,
        quantity INT NOT NULL,
        size VARCHAR(10) DEFAULT 'M',          -- S, M, L
        sugar_level VARCHAR(10) DEFAULT '100%', -- 0%, 30%, 50%, 70%, 100%
        ice_level VARCHAR(10) DEFAULT '100%',   -- 0%, 50%, 100%
        topping_notes NVARCHAR(255),
        subtotal DECIMAL(12, 2) NOT NULL,
        CONSTRAINT FK_Order_Items FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
    );
END
GO

-- 3. Bang gio hang (carts)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='carts' and xtype='U')
BEGIN
    CREATE TABLE carts (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        session_id VARCHAR(100) NOT NULL,
        customer_id BIGINT,
        updated_at DATETIME2 DEFAULT GETDATE()
    );
END
GO

-- 4. Bang chi tiet gio hang (cart_items)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='cart_items' and xtype='U')
BEGIN
    CREATE TABLE cart_items (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        cart_id BIGINT NOT NULL,
        product_id BIGINT NOT NULL,
        product_name NVARCHAR(150),
        unit_price DECIMAL(12, 2),
        quantity INT DEFAULT 1,
        size VARCHAR(10) DEFAULT 'M',
        sugar_level VARCHAR(10) DEFAULT '100%',
        ice_level VARCHAR(10) DEFAULT '100%',
        topping_notes NVARCHAR(255),
        CONSTRAINT FK_Cart_Items FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE
    );
END
GO
