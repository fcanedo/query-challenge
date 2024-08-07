-- Final query
SELECT u.name, u.email, sum(o.quantity * p.price) as total
FROM users u
         inner join orders o on u.id = o.user_id
         inner join products p on o.product_id = p.id
WHERE p.category = 'Electronics'
GROUP BY u.id
HAVING sum(o.quantity * p.price) > 1000
    AND count(*) >= 3
ORDER BY total DESC;