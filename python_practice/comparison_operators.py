a = 20
b = 10

print("Equal:", a == b)
print("Not Equal:", a != b)
print("Greater Than:", a > b)
print("Less Than:", a < b)
print("Greater Than or Equal:", a >= b)
print("Less Than or Equal:", a <= b)


# Student Marks Comparison

marks = 75

print("\nStudent Marks:", marks)

print("Passed:", marks >= 40)
print("Distinction:", marks >= 75)
print("Failed:", marks < 40)


# Comparison Using If-Else

if marks >= 40:
    print("Student Passed")
else:
    print("Student Failed")


# Multiple Conditions

age = 22

if age >= 18 and age <= 60:
    print("Eligible")
else:
    print("Not Eligible")


# Chained Comparison

temperature = 25

if 20 <= temperature <= 35:
    print("Temperature is Normal")
else:
    print("Temperature is Outside the Range")


# String Comparison

name1 = "Nikhil"
name2 = "Nikhil"
name3 = "Harsha"

print("\nName Comparison:")
print(name1 == name2)
print(name1 != name3)
print("Apple" < "Banana")


# Inventory Comparison

quantity = 4
minimum_stock = 5

if quantity < minimum_stock:
    print("\nLow Stock - Reorder Required")
else:
    print("\nStock Available")


# Price Comparison

laptop_price = 55000
budget = 60000

if laptop_price <= budget:
    print("Product is within budget")
else:
    print("Product exceeds budget")


# Multiple Student Grade Conditions

student_marks = 82

if student_marks >= 90:
    print("Grade A")
elif student_marks >= 75:
    print("Grade B")
elif student_marks >= 50:
    print("Grade C")
elif student_marks >= 40:
    print("Grade D")
else:
    print("Fail")


# Boolean Comparison

is_active = True

print("\nIs Active:", is_active == True)
print("Is Inactive:", is_active == False)
