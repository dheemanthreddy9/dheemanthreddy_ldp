USE sakila;

-- 1. PG-13 Comedy movies
SELECT 
    f.title,
    f.rating,
    c.name AS category
FROM film f
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON fc.category_id = c.category_id
WHERE f.rating = 'PG-13'
  AND c.name = 'Comedy';


-- 2. Top 3 rented Horror movies
SELECT
    f.title,
    COUNT(r.rental_id) AS rental_count
FROM film f
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON fc.category_id = c.category_id
JOIN inventory i ON f.film_id = i.film_id
JOIN rental r ON i.inventory_id = r.inventory_id
WHERE c.name = 'Horror'
GROUP BY f.film_id, f.title
ORDER BY rental_count DESC
LIMIT 3;


-- 3. Indian customers who rented Sports movies
SELECT DISTINCT
    c.customer_id,
    c.first_name,
    c.last_name
FROM customer c
JOIN address a ON c.address_id = a.address_id
JOIN city ci ON a.city_id = ci.city_id
JOIN country co ON ci.country_id = co.country_id
JOIN rental r ON c.customer_id = r.customer_id
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film f ON i.film_id = f.film_id
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category ca ON fc.category_id = ca.category_id
WHERE co.country = 'India'
  AND ca.name = 'Sports';


-- 4. Canadian customers who rented Nick Wahlberg movies
SELECT DISTINCT
    c.customer_id,
    c.first_name,
    c.last_name
FROM customer c
JOIN address a ON c.address_id = a.address_id
JOIN city ci ON a.city_id = ci.city_id
JOIN country co ON ci.country_id = co.country_id
JOIN rental r ON c.customer_id = r.customer_id
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film f ON i.film_id = f.film_id
JOIN film_actor fa ON f.film_id = fa.film_id
JOIN actor ac ON fa.actor_id = ac.actor_id
WHERE co.country = 'Canada'
  AND ac.first_name = 'NICK'
  AND ac.last_name = 'WAHLBERG';


-- 5. Number of movies Sean Williams acted in
SELECT
    COUNT(DISTINCT fa.film_id) AS number_of_movies
FROM actor a
JOIN film_actor fa ON a.actor_id = fa.actor_id
WHERE a.first_name = 'SEAN'
  AND a.last_name = 'WILLIAMS';


-- 6. Number of documentaries with Deleted Scenes
SELECT
    COUNT(DISTINCT f.film_id) AS documentary_count
FROM film f
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON fc.category_id = c.category_id
WHERE c.name = 'Documentary'
  AND FIND_IN_SET('Deleted Scenes', f.special_features) > 0;


-- 7. Sci-Fi movies rented by Jon Stephens' store
SELECT
    COUNT(DISTINCT f.film_id) AS sci_fi_movies_rented
FROM staff s
JOIN store st ON s.staff_id = st.manager_staff_id
JOIN inventory i ON st.store_id = i.store_id
JOIN rental r ON i.inventory_id = r.inventory_id
JOIN film f ON i.film_id = f.film_id
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON fc.category_id = c.category_id
WHERE s.first_name = 'Jon'
  AND s.last_name = 'Stephens'
  AND c.name = 'Sci-Fi';


-- 8. Total sales from Animation movies
SELECT
    SUM(p.amount) AS total_sales
FROM payment p
JOIN rental r ON p.rental_id = r.rental_id
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film f ON i.film_id = f.film_id
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON fc.category_id = c.category_id
WHERE c.name = 'Animation';


-- 9. Top 3 rented categories by Patricia Johnson
SELECT
    ca.name AS category,
    COUNT(r.rental_id) AS rental_count
FROM customer c
JOIN rental r ON c.customer_id = r.customer_id
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film f ON i.film_id = f.film_id
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category ca ON fc.category_id = ca.category_id
WHERE c.first_name = 'PATRICIA'
  AND c.last_name = 'JOHNSON'
GROUP BY ca.category_id, ca.name
ORDER BY rental_count DESC
LIMIT 3;


-- 10. R-rated movies rented by Susan Wilson
SELECT
    COUNT(DISTINCT f.film_id) AS r_rated_movies
FROM customer c
JOIN rental r ON c.customer_id = r.customer_id
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film f ON i.film_id = f.film_id
WHERE c.first_name = 'SUSAN'
  AND c.last_name = 'WILSON'
  AND f.rating = 'R';