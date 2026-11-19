# Assignment Statements
name = "Harsha"
age = 23
marks = 85

print("Name:", name)
print("Age:", age)
print("Marks:", marks)
# Multiple Assignment
a, b, c = 10, 20, 30

print("\nA:", a)
print("B:", b)
print("C:", c)

#  If Statement
quantity = 3
if quantity < 5:
    print("\nLow Stock")

#  If-Else Statement
marks = 65
if marks >= 40:
    print("Pass")
else:
    print("Fail")


# If-Elif-Else Statement
marks = 85
if marks >= 90:
    print("Grade A")
elif marks >= 75:
    print("Grade B")
elif marks >= 50:
    print("Grade C")
else:
    print("Fail")
# For Loop
products = ["Laptop", "Mouse", "Keyboard"]
print("\nProduct List:")

for product in products:
    print(product)


# Range Function
for number in range(1, 6):
    print("Number:", number)


# While Loop
count = 1
print("\nWhile Loop:")

while count <= 5:
    print(count)
    count += 1
# Break Statement
print("\nBreak Example:")
for number in range(1, 6):
    if number == 3:
        break
    print(number)


# Continue Statement
print("\nContinue Example:")
for number in range(1, 6):
    if number == 3:
        continue
    print(number)


# Pass Statement

print("\nPass Example:")

for number in range(1, 4):

    if number == 2:
        pass

    print(number)


#  Import Statement

import math

print("\nSquare Root:", math.sqrt(25))
print("Power:", math.pow(2, 3))


# Exception Handling

try:

    number = int(input("\nEnter a number: "))

    result = 100 / number

except ValueError:
    print("Please enter a valid integer")

except ZeroDivisionError:
    print("Cannot divide by zero")

else:
    print("Result:", result)

finally:
    print("Execution Completed")


# Nested If Statements

age = 22
has_id = True

if age >= 18:

    if has_id:
        print("Entry Allowed")

    else:
        print("ID Required")

else:
    print("Entry Not Allowed")
