-- Insert sample users
INSERT INTO users (name, email)
VALUES ('Alice Johnson', 'alice@example.com'),
       ('Bob Smith', 'bob@example.com'),
       ('Charlie Brown', 'charlie@example.com');

-- Insert sample products
INSERT INTO products (name, price, category)
VALUES ('Laptop', 1200, 'Electronics'),
       ('Smartphone', 800, 'Electronics'),
       ('Desk Chair', 150, 'Furniture'),
       ('Coffee Table', 200, 'Furniture');

-- Insert sample orders
INSERT INTO orders (user_id, product_id, quantity, created_at)
VALUES (1, 1, 1, '2023-01-13'),
       (1, 2, 1, '2023-01-14'),
       (1, 2, 1, '2023-01-15'),
       (2, 2, 2, '2023-01-16'),
       (3, 3, 1, '2023-01-17'),
       (1, 4, 1, '2023-01-18'),
       (3, 2, 1, '2024-08-04'),
       (3, 2, 1, '2024-08-05'),
       (3, 2, 1, '2024-08-06');
