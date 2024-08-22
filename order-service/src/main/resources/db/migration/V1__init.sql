CREATE TABLE t_orders
(
    id SERIAL PRIMARY KEY,
    order_number varchar(255),
    sku_code varchar(255),
    price decimal(19, 2),
    quantity int
);
