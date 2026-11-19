name = "Nikhil"
age = 22
height = 5.9
is_student = True

print("Name:", name)
print("Age:", age)
print("Height:", height)
print("Is Student:", is_student)

# Checking object types
print(type(name))
print(type(age))
print(type(height))
print(type(is_student))

# Checking object identity
print("Object ID:", id(name))


# Mutable List
products = ["Laptop", "Mouse", "Keyboard"]

products.append("Monitor")

print("\nUpdated Products:", products)

# Immutable String
language = "Python"

language = language + " Programming"

print("Updated Language:", language)


# List Operations

students = ["Nikhil", "Kalyan", "Harsha"]

print("\nStudents:", students)

print("First Student:", students[0])

students.append("Rahul")
students.insert(1, "Arjun")

students[0] = "Vikram"
students.remove("Harsha")

print("First Two Students:", students[0:2])

print("Updated Students:", students)


#Tuple Operations

coordinates = (10, 20, 30)

print("\nCoordinates:", coordinates)
print("First Coordinate:", coordinates[0])
print("Tuple Length:", len(coordinates))


# Set Operations

numbers = {10, 20, 30, 10, 20}

print("\nUnique Numbers:", numbers)

numbers.add(40)
numbers.discard(20)

print("Updated Set:", numbers)

set_a = {1, 2, 3, 4}
set_b = {3, 4, 5, 6}

print("Union:", set_a.union(set_b))
print("Intersection:", set_a.intersection(set_b))
print("Difference:", set_a.difference(set_b))


#Dictionary Operations

product = {
    "name": "Laptop",
    "price": 50000,
    "quantity": 10
}

print("\nProduct Name:", product["name"])
print("Product Price:", product.get("price"))

# Adding a new key
product["brand"] = "Dell"

# Updating a value
product["price"] = 55000

# Accessing keys and values
print("Keys:", product.keys())
print("Values:", product.values())
print("Items:", product.items())

# Removing an element
product.pop("quantity")

print("Updated Product:", product)


# Type Conversion

number = "100"

converted_number = int(number)

print("\nOriginal Type:", type(number))
print("Converted Value:", converted_number)
print("Converted Type:", type(converted_number))

price = 250
price_string = str(price)

print("Price as String:", price_string)
print("String Type:", type(price_string))


#Multiple Data Types

student = {
    "name": "Kalyan",
    "age": 21,
    "marks": [85, 90, 78],
    "is_passed": True
}

print("\nStudent Details:")
print(student)

print("Student Name:", student["name"])
print("Student Marks:", student["marks"])
print("First Mark:", student["marks"][0])
