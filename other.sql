-- This assumes orders can have multiple different products
SELECT u.name, u.email, sum(o.quantity * p.price) as total
FROM users u
         inner join orders o on u.id = o.user_id
         inner join products p on o.product_id = p.id
WHERE o.id IN (
    SELECT o2.id
    FROM orders o2
             inner join products p2 on o2.product_id = p2.id
    WHERE p2.category = 'Electronics'
)
GROUP BY u.id
HAVING sum(o.quantity * p.price) > 1000
   AND count(*) >= 3
ORDER BY total DESC;

-- Orders full info
SELECT o.id, o.created_at, u.name, p.name, p.category, o.quantity, p.price * o.quantity as linetotal
FROM orders o
         inner join products p on p.id = o.product_id
         inner join users u on o.user_id = u.id;


select * from users;

select * from products;

select * from orders;
