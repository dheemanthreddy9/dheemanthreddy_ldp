-- data modeling
USE college_db;
SELECT
    s.student_id,
    s.student_name,
    s.email,
    s.age,
    d.department_name,
    d.location
FROM students s
JOIN departments d
ON s.department_id = d.department_id;

SELECT
    c.course_id,
    c.course_name,
    c.credits,
    d.department_name
FROM courses c
JOIN departments d
ON c.department_id = d.department_id;

SELECT
    s.student_name,
    c.course_name,
    e.enrollment_date,
    e.marks
FROM students s
JOIN enrollments e
ON s.student_id = e.student_id
JOIN courses c
ON e.course_id = c.course_id;

SELECT
    d.department_name,
    COUNT(s.student_id) AS total_students
FROM departments d
LEFT JOIN students s
ON d.department_id = s.department_id
GROUP BY d.department_id, d.department_name;

SELECT
    d.department_name,
    COUNT(c.course_id) AS total_courses
FROM departments d
LEFT JOIN courses c
ON d.department_id = c.department_id
GROUP BY d.department_id, d.department_name;

-- Schema Conversion
SELECT
    s.student_id,
    s.student_name,
    e.course_id,
    e.enrollment_date,
    e.marks
FROM students s
JOIN enrollments e
ON s.student_id = e.student_id;

SELECT
    e.student_id,
    e.course_id,
    s.student_name,
    c.course_name,
    e.marks
FROM enrollments e
JOIN students s
ON e.student_id = s.student_id
JOIN courses c
ON e.course_id = c.course_id;

SELECT
    s.student_id,
    s.student_name,
    d.department_name,
    c.course_name,
    e.enrollment_date,
    e.marks
FROM students s
JOIN departments d
ON s.department_id = d.department_id
JOIN enrollments e
ON s.student_id = e.student_id
JOIN courses c
ON e.course_id = c.course_id;

SELECT
    s.student_id,
    s.student_name,
    COUNT(e.course_id) AS total_courses
FROM students s
LEFT JOIN enrollments e
ON s.student_id = e.student_id
GROUP BY s.student_id, s.student_name;

SELECT
    c.course_id,
    c.course_name,
    COUNT(e.student_id) AS total_students
FROM courses c
LEFT JOIN enrollments e
ON c.course_id = e.course_id
GROUP BY c.course_id, c.course_name;



-- Normalization
CREATE TABLE student_course_unnormalized (
    student_id INT,
    student_name VARCHAR(50),
    department_name VARCHAR(50),
    course_name VARCHAR(100),
    marks INT
);

INSERT INTO student_course_unnormalized
VALUES
(101, 'Ravi', 'Computer Science', 'DBMS', 85),
(101, 'Ravi', 'Computer Science', 'Java', 90),
(102, 'Arun', 'Computer Science', 'DBMS', 78),
(102, 'Arun', 'Computer Science', 'Python', 88);

SELECT * FROM student_course_unnormalized;

CREATE TABLE student_1nf (
    student_id INT,
    student_name VARCHAR(50),
    department_name VARCHAR(50),
    course_name VARCHAR(100),
    marks INT
);

INSERT INTO student_1nf
VALUES
(101, 'Ravi', 'Computer Science', 'DBMS', 85),
(101, 'Ravi', 'Computer Science', 'Java', 90),
(102, 'Arun', 'Computer Science', 'DBMS', 78),
(102, 'Arun', 'Computer Science', 'Python', 88);

SELECT * FROM student_1nf;

CREATE TABLE student_2nf (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50),
    department_id INT
);

CREATE TABLE course_2nf (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(50)
);

CREATE TABLE enrollment_2nf (
    student_id INT,
    course_id INT,
    marks INT,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id)
        REFERENCES student_2nf(student_id),
    FOREIGN KEY (course_id)
        REFERENCES course_2nf(course_id)
);

CREATE TABLE department_3nf (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50),
    location VARCHAR(50)
);

CREATE TABLE student_3nf (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50),
    email VARCHAR(100),
    age INT,
    department_id INT,
    FOREIGN KEY (department_id)
        REFERENCES department_3nf(department_id)
);

SELECT
    s.student_id,
    s.student_name,
    d.department_name,
    d.location
FROM student_3nf s
JOIN department_3nf d
ON s.department_id = d.department_id;




-- Triggers,Index,query execution


USE college_db;

SHOW TRIGGERS;


CREATE TABLE IF NOT EXISTS student_audit (
    audit_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    student_name VARCHAR(100),
    action_type VARCHAR(20),
    old_age INT,
    new_age INT,
    action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


DROP TRIGGER IF EXISTS before_student_insert;

DELIMITER //

CREATE TRIGGER before_student_insert
BEFORE INSERT ON students
FOR EACH ROW
BEGIN
    SET NEW.student_name = UPPER(NEW.student_name);
END //

DELIMITER ;


INSERT INTO students
VALUES (109, 'rohan', 'rohan@gmail.com', 22, 1);

SELECT *
FROM students
WHERE student_id = 109;


DROP TRIGGER IF EXISTS before_student_update;

DELIMITER //

CREATE TRIGGER before_student_update
BEFORE UPDATE ON students
FOR EACH ROW
BEGIN
    SET NEW.student_name = UPPER(NEW.student_name);
END //

DELIMITER ;


-- Test BEFORE UPDATE trigger

UPDATE students
SET student_name = 'kiran reddy'
WHERE student_id = 103;

SELECT *
FROM students
WHERE student_id = 103;


DROP TRIGGER IF EXISTS after_student_insert;

DELIMITER //

CREATE TRIGGER after_student_insert
AFTER INSERT ON students
FOR EACH ROW
BEGIN
    INSERT INTO student_audit
    (student_id, student_name, action_type)
    VALUES
    (NEW.student_id, NEW.student_name, 'INSERT');
END //

DELIMITER ;


-- Test AFTER INSERT trigger

INSERT INTO students
VALUES (110, 'Suresh', 'suresh@gmail.com', 21, 2);

SELECT *
FROM student_audit
WHERE student_id = 110;



DROP TRIGGER IF EXISTS after_student_update;

DELIMITER //

CREATE TRIGGER after_student_update
AFTER UPDATE ON students
FOR EACH ROW
BEGIN
    INSERT INTO student_audit
    (student_id, student_name, action_type, old_age, new_age)
    VALUES
    (NEW.student_id,
     NEW.student_name,
     'UPDATE',
     OLD.age,
     NEW.age);
END //

DELIMITER ;



UPDATE students
SET age = 23
WHERE student_id = 110;

SELECT *
FROM student_audit
WHERE student_id = 110;



DROP TRIGGER IF EXISTS after_student_delete;

DELIMITER //

CREATE TRIGGER after_student_delete
AFTER DELETE ON students
FOR EACH ROW
BEGIN
    INSERT INTO student_audit
    (student_id, student_name, action_type)
    VALUES
    (OLD.student_id, OLD.student_name, 'DELETE');
END //

DELIMITER ;


-- Test AFTER DELETE trigger

DELETE FROM students
WHERE student_id = 110;

SELECT *
FROM student_audit
WHERE student_id = 110;


SELECT *
FROM student_audit;


SHOW TRIGGERS;

SHOW CREATE TRIGGER before_student_insert;

SHOW CREATE TRIGGER before_student_update;

SHOW CREATE TRIGGER after_student_insert;

SHOW CREATE TRIGGER after_student_update;

SHOW CREATE TRIGGER after_student_delete;


SHOW INDEX FROM students;

SHOW INDEX FROM courses;

SHOW INDEX FROM enrollments;

SHOW INDEX FROM departments;



CREATE INDEX idx_students_department
ON students(department_id);


-- Test query

SELECT *
FROM students
WHERE department_id = 1;




CREATE INDEX idx_courses_department
ON courses(department_id);


-- Test query

SELECT *
FROM courses
WHERE department_id = 1;



CREATE INDEX idx_enrollment_date
ON enrollments(enrollment_date);


-- Test query

SELECT *
FROM enrollments
WHERE enrollment_date = '2026-01-15';



CREATE INDEX idx_student_department_name
ON students(department_id, student_name);


SELECT *
FROM students
WHERE department_id = 1;


SELECT *
FROM students
WHERE department_id = 1
AND student_name = 'Ravi';



SELECT *
FROM students
WHERE student_name = 'Ravi';


CREATE INDEX idx_students_age
ON students(age);


-- Range query

SELECT *
FROM students
WHERE age BETWEEN 20 AND 23;



CREATE INDEX idx_department_age
ON students(department_id, age);


-- Query using composite index

SELECT *
FROM students
WHERE department_id = 1
AND age > 21;


EXPLAIN
SELECT *
FROM students
WHERE department_id = 1;


EXPLAIN
SELECT *
FROM courses
WHERE department_id = 1;


EXPLAIN
SELECT *
FROM enrollments
WHERE student_id = 101;


EXPLAIN
SELECT *
FROM students
WHERE department_id = 1
AND age > 21;


EXPLAIN
SELECT
    s.student_name,
    d.department_name
FROM students s
JOIN departments d
    ON s.department_id = d.department_id;


EXPLAIN
SELECT
    s.student_name,
    c.course_name,
    e.marks
FROM students s
JOIN enrollments e
    ON s.student_id = e.student_id
JOIN courses c
    ON e.course_id = c.course_id;


SELECT *
FROM students
WHERE department_id = 1;

-- Optimized version

SELECT
    student_id,
    student_name,
    email
FROM students
WHERE department_id = 1;



SELECT
    s.student_name,
    d.department_name
FROM students s
JOIN departments d
    ON s.department_id = d.department_id;


SELECT
    department_id,
    COUNT(*) AS total_students
FROM students
GROUP BY department_id;

EXPLAIN
SELECT
    department_id,
    COUNT(*) AS total_students
FROM students
GROUP BY department_id;

=================================================

SELECT *
FROM students
ORDER BY department_id;

EXPLAIN
SELECT *
FROM students
ORDER BY department_id;


SELECT *
FROM students
WHERE age BETWEEN 20 AND 23;

EXPLAIN
SELECT *
FROM students
WHERE age BETWEEN 20 AND 23;


SELECT *
FROM students
WHERE department_id = 1
AND age > 21;

EXPLAIN
SELECT *
FROM students
WHERE department_id = 1
AND age > 21;



SELECT *
FROM enrollments
WHERE student_id = 101
AND course_id = 201;

EXPLAIN
SELECT *
FROM enrollments
WHERE student_id = 101
AND course_id = 201;

SELECT
    s.student_name,
    c.course_name,
    e.marks
FROM students s
JOIN enrollments e
    ON s.student_id = e.student_id
JOIN courses c
    ON e.course_id = c.course_id
WHERE s.student_id = 101;

EXPLAIN
SELECT
    s.student_name,
    c.course_name,
    e.marks
FROM students s
JOIN enrollments e
    ON s.student_id = e.student_id
JOIN courses c
    ON e.course_id = c.course_id
WHERE s.student_id = 101;

SHOW INDEX FROM students;
SHOW INDEX FROM courses;
SHOW INDEX FROM enrollments;


DROP INDEX idx_students_department
ON students;
DROP INDEX idx_courses_department
ON courses;
DROP INDEX idx_enrollment_date
ON enrollments;
DROP INDEX idx_student_department_name
ON students;
DROP INDEX idx_students_age
ON students;
DROP INDEX idx_department_age
ON students;


DROP TRIGGER IF EXISTS before_student_insert;
DROP TRIGGER IF EXISTS before_student_update;
DROP TRIGGER IF EXISTS after_student_insert;
DROP TRIGGER IF EXISTS after_student_update;
DROP TRIGGER IF EXISTS after_student_delete;