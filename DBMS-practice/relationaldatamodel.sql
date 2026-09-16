

CREATE DATABASE college_db;

USE college_db;

CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50) NOT NULL UNIQUE,
    location VARCHAR(50)
);

CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    age INT CHECK (age >= 18),
    department_id INT,

    FOREIGN KEY (department_id)
        REFERENCES departments(department_id)
);

CREATE TABLE courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(50) NOT NULL,
    credits INT CHECK (credits BETWEEN 1 AND 6),
    department_id INT,

    FOREIGN KEY (department_id)
        REFERENCES departments(department_id)
);

CREATE TABLE enrollments (
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    marks INT CHECK (marks BETWEEN 0 AND 100),

    PRIMARY KEY (student_id, course_id),

    FOREIGN KEY (student_id)
        REFERENCES students(student_id),

    FOREIGN KEY (course_id)
        REFERENCES courses(course_id)
);

INSERT INTO departments
VALUES
(1, 'Computer Science', 'Block A'),
(2, 'Electronics', 'Block B'),
(3, 'Mechanical', 'Block C'),
(4, 'Civil', 'Block D');

INSERT INTO students
VALUES
(101, 'Ravi', 'ravi@gmail.com', 21, 1),
(102, 'Arun', 'arun@gmail.com', 22, 1),
(103, 'Kiran', 'kiran@gmail.com', 20, 2),
(104, 'Priya', 'priya@gmail.com', 23, 2),
(105, 'Sneha', 'sneha@gmail.com', 21, 3),
(106, 'Rahul', 'rahul@gmail.com', 24, 1),
(107, 'Anjali', 'anjali@gmail.com', 22, 4),
(108, 'Vikram', 'vikram@gmail.com', 25, 3);

INSERT INTO courses
VALUES
(201, 'DBMS', 4, 1),
(202, 'Java', 4, 1),
(203, 'Python', 3, 1),
(204, 'Digital Electronics', 4, 2),
(205, 'Mechanical Design', 5, 3),
(206, 'Engineering Drawing', 3, 4);

INSERT INTO enrollments
VALUES
(101, 201, '2026-01-10', 85),
(101, 202, '2026-01-11', 90),
(102, 201, '2026-01-10', 78),
(102, 203, '2026-01-12', 88),
(103, 204, '2026-01-13', 75),
(104, 204, '2026-01-13', 92),
(105, 205, '2026-01-14', 81),
(106, 201, '2026-01-15', 95),
(106, 202, '2026-01-15', 89),
(107, 206, '2026-01-16', 76),
(108, 205, '2026-01-17', 91);

DESCRIBE departments;

DESCRIBE students;

DESCRIBE courses;

DESCRIBE enrollments;

SELECT * FROM departments;

SELECT * FROM students;

SELECT * FROM courses;

SELECT * FROM enrollments;

SELECT student_name
FROM students;

SELECT student_name, age
FROM students;

SELECT student_id, student_name, age, department_id
FROM students;

SELECT *
FROM students
WHERE age > 21;

SELECT *
FROM students
WHERE age = 21;

SELECT *
FROM students
WHERE age < 23;

SELECT *
FROM students
WHERE department_id = 1;

SELECT *
FROM students
WHERE age > 21
AND department_id = 1;

SELECT *
FROM students
WHERE department_id = 1
OR department_id = 2;

SELECT *
FROM students
WHERE NOT department_id = 1;

SELECT *
FROM students
WHERE age BETWEEN 21 AND 24;

SELECT *
FROM enrollments
WHERE marks BETWEEN 80 AND 90;

SELECT *
FROM students
WHERE department_id IN (1, 2, 3);

SELECT *
FROM courses
WHERE department_id IN (1, 3);

SELECT *
FROM students
WHERE department_id NOT IN (1, 2);

SELECT *
FROM students
WHERE student_name LIKE 'R%';

SELECT *
FROM students
WHERE student_name LIKE '%a';

SELECT *
FROM students
WHERE student_name LIKE '%i%';

SELECT *
FROM students
WHERE student_name LIKE '_a%';

SELECT DISTINCT department_id
FROM students;

SELECT DISTINCT age
FROM students;

SELECT DISTINCT course_id
FROM enrollments;

SELECT *
FROM students
ORDER BY age;

SELECT *
FROM students
ORDER BY age DESC;

SELECT *
FROM students
ORDER BY student_name ASC;

SELECT *
FROM students
ORDER BY department_id, age DESC;

SELECT
    student_name AS Name,
    age AS Age
FROM students;

SELECT
    s.student_name,
    s.age
FROM students AS s;


INSERT INTO students
VALUES
(109, 'Meena', 'meena@gmail.com', 22, 4);


INSERT INTO students
(student_id, student_name, age, department_id)
VALUES
(110, 'Raj', 23, 1);


UPDATE students
SET age = 23
WHERE student_id = 101;


UPDATE students
SET department_id = 2
WHERE student_id = 110;


UPDATE students
SET age = 24,
    department_id = 3
WHERE student_id = 109;

DELETE FROM students
WHERE student_id = 110;

SELECT COUNT(*)
FROM students;

SELECT AVG(age)
FROM students;

SELECT MAX(age)
FROM students;

SELECT MIN(age)
FROM students;

SELECT SUM(marks)
FROM enrollments;

SELECT AVG(marks)
FROM enrollments;

SELECT
    department_id,
    COUNT(*) AS student_count
FROM students
GROUP BY department_id;

SELECT
    department_id,
    AVG(age) AS average_age
FROM students
GROUP BY department_id;

SELECT
    course_id,
    MAX(marks) AS highest_marks
FROM enrollments
GROUP BY course_id;

SELECT
    department_id,
    COUNT(*) AS student_count
FROM students
GROUP BY department_id
HAVING COUNT(*) > 2;

SELECT
    course_id,
    AVG(marks) AS average_marks
FROM enrollments
GROUP BY course_id
HAVING AVG(marks) > 80;

SELECT
    s.student_name,
    d.department_name
FROM students s
INNER JOIN departments d
ON s.department_id = d.department_id;

SELECT
    c.course_name,
    d.department_name
FROM courses c
INNER JOIN departments d
ON c.department_id = d.department_id;

SELECT
    s.student_name,
    c.course_name,
    e.marks
FROM students s
JOIN enrollments e
ON s.student_id = e.student_id
JOIN courses c
ON e.course_id = c.course_id;


SELECT
    d.department_name,
    s.student_name
FROM departments d
LEFT JOIN students s
ON d.department_id = s.department_id;


SELECT
    s.student_name,
    e.course_id,
    e.marks
FROM students s
LEFT JOIN enrollments e
ON s.student_id = e.student_id;



SELECT
    c.course_name,
    e.student_id,
    e.marks
FROM enrollments e
RIGHT JOIN courses c
ON e.course_id = c.course_id;

SELECT
    s.student_name,
    c.course_name,
    e.marks
FROM students s
JOIN enrollments e
ON s.student_id = e.student_id
JOIN courses c
ON e.course_id = c.course_id
WHERE e.marks > 85;

SELECT
    d.department_name,
    COUNT(s.student_id) AS student_count
FROM departments d
LEFT JOIN students s
ON d.department_id = s.department_id
GROUP BY
    d.department_id,
    d.department_name;

SELECT
    d.department_name,
    COUNT(s.student_id) AS student_count
FROM departments d
JOIN students s
ON d.department_id = s.department_id
GROUP BY
    d.department_id,
    d.department_name
HAVING COUNT(s.student_id) > 2;



SELECT
    student_name,
    age
FROM students
WHERE age > (
    SELECT AVG(age)
    FROM students
);



SELECT
    student_id,
    course_id,
    marks
FROM enrollments
WHERE marks > (
    SELECT AVG(marks)
    FROM enrollments
);



SELECT student_name
FROM students
WHERE department_id IN (
    SELECT department_id
    FROM departments
    WHERE department_name = 'Computer Science'
);




SELECT department_name
FROM departments d
WHERE EXISTS (
    SELECT 1
    FROM students s
    WHERE s.department_id = d.department_id
);




SELECT student_name AS name
FROM students

UNION

SELECT department_name AS name
FROM departments;



SELECT student_name AS name
FROM students

UNION ALL

SELECT department_name AS name
FROM departments;


SELECT
    student_name,
    age,
    CASE
        WHEN age < 21 THEN 'Young'
        WHEN age BETWEEN 21 AND 23 THEN 'Adult'
        ELSE 'Senior'
    END AS category
FROM students;


SELECT
    student_id,
    marks,
    CASE
        WHEN marks >= 90 THEN 'Excellent'
        WHEN marks >= 75 THEN 'Good'
        WHEN marks >= 50 THEN 'Pass'
        ELSE 'Fail'
    END AS result
FROM enrollments;


SELECT *
FROM students
WHERE email IS NULL;

SELECT *
FROM students
WHERE email IS NOT NULL;


ALTER TABLE students
ADD phone VARCHAR(15);


ALTER TABLE students
MODIFY phone VARCHAR(20);


ALTER TABLE students
RENAME COLUMN phone TO phone_number;


ALTER TABLE students
DROP COLUMN phone_number;

