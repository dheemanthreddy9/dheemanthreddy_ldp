def greet():
    print("Welcome to Python Programming")

greet()

#Function with Parameters
def student_details(name, age):
    print("Name:", name)
    print("Age:", age)

student_details("Nikhil", 22)


# Positional Arguments
def add_numbers(a, b):
    print("Addition:", a + b)
add_numbers(10, 20)
# Keyword Arguments
def employee_details(name, salary):
    print("Employee:", name)
    print("Salary:", salary)

employee_details(salary=30000, name="Kalyan")

#Default Arguments

def greet_student(name="Student"):
    print("Hello", name)
greet_student()
greet_student("Harsha")
# Variable-Length Arguments (*args)
def calculate_sum(*numbers):
    total = sum(numbers)
    print("Total:", total)

calculate_sum(10, 20, 30)
calculate_sum(10, 20, 30, 40, 50)

#Keyword Variable-Length Arguments (**kwargs)

def display_details(**details):

    for key, value in details.items():
        print(key, ":", value)


display_details(
    name="Nikhil",
    age=22,
    city="Hyderabad"
)


#Return Statement

def calculate_total(price, quantity):

    total = price * quantity

    return total


result = calculate_total(500, 5)

print("Total Price:", result)


# Multiple Return Values

def calculate(a, b):

    addition = a + b
    subtraction = a - b

    return addition, subtraction


add, subtract = calculate(20, 10)

print("Addition:", add)
print("Subtraction:", subtract)


# Built-in Functions

numbers = [10, 20, 30, 40, 50]

print("\nLength:", len(numbers))
print("Sum:", sum(numbers))
print("Maximum:", max(numbers))
print("Minimum:", min(numbers))
print("Sorted:", sorted(numbers))
print("Type:", type(numbers))


# Lambda Function

square = lambda number: number * number

print("\nSquare:", square(5))


# String Methods

message = "python programming"

print("\nUppercase:", message.upper())
print("Lowercase:", message.lower())
print("Title:", message.title())
print("Replace:", message.replace("python", "java"))
print("Split:", message.split())


# List Methods

products = ["Laptop", "Mouse", "Keyboard"]

products.append("Monitor")
products.insert(1, "Printer")
products.remove("Mouse")

print("\nProducts:", products)

products.sort()

print("Sorted Products:", products)

products.reverse()

print("Reversed Products:", products)


# Dictionary Methods

student = {
    "name": "Harsha",
    "age": 23,
    "marks": 85
}

print("\nKeys:", student.keys())
print("Values:", student.values())
print("Items:", student.items())
print("Name:", student.get("name"))

student.update({"marks": 90})

print("Updated Student:", student)


# Set Methods

set1 = {10, 20, 30}
set2 = {20, 30, 40}

print("\nUnion:", set1.union(set2))
print("Intersection:", set1.intersection(set2))
print("Difference:", set1.difference(set2))

set1.add(50)

print("Updated Set:", set1)


# User-Defined Class and Methods

class Product:

    def __init__(self, name, price):
        self.name = name
        self.price = price

    def display_details(self):
        print("Product:", self.name)
        print("Price:", self.price)


product1 = Product("Laptop", 50000)

print("\nProduct Details:")

product1.display_details()


# Class Method

class Employee:

    company = "ABC Technologies"

    @classmethod
    def display_company(cls):
        print("Company:", cls.company)


Employee.display_company()


# Static Method

class Calculator:

    @staticmethod
    def multiply(a, b):
        return a * b


result = Calculator.multiply(10, 5)

print("\nMultiplication:", result)
