
# Basic lambda function

square = lambda x: x * x

print("Square:", square(5))


# Lambda with multiple arguments

add = lambda a, b: a + b

print("Addition:", add(10, 20))


# Using map()

numbers = [1, 2, 3, 4, 5]

squares = list(map(lambda x: x * x, numbers))

print("Squares:", squares)


#Using filter()

even_numbers = list(
    filter(lambda x: x % 2 == 0, numbers)
)

print("Even Numbers:", even_numbers)


# Using sorted()

employees = [
    ("Nikhil", 30000),
    ("Kalyan", 45000),
    ("Harsha", 25000)
]

sorted_employees = sorted(
    employees,
    key=lambda employee: employee[1]
)

print("Sorted Employees:", sorted_employees)
