# 1. Basic try and except
try:
    price = int(input("Enter product price: "))
    print("Product Price:", price)

except ValueError:
    print("Error: Please enter a valid number.")


# 2. Handling Multiple Exceptions
print("\n----- Multiple Exceptions -----")

try:
    a = int(input("Enter first number: "))
    b = int(input("Enter second number: "))

    result = a / b
    print("Result:", result)

except ValueError:
    print("Error: Enter numbers only.")

except ZeroDivisionError:
    print("Error: Cannot divide by zero.")


# 3. Using else
print("\n----- Else Block -----")

try:
    quantity = int(input("Enter product quantity: "))

except ValueError:
    print("Invalid quantity.")

else:
    print("Valid quantity:", quantity)


# 4. Using finally
print("\n----- Finally Block -----")

try:
    number = int(input("Enter a number: "))
    print("Number:", number)

except ValueError:
    print("Invalid input.")

finally:
    print("Program execution completed.")


# 5. Using raise
print("\n----- Raise Exception -----")

try:
    stock = 5
    requested_quantity = int(
        input("Enter requested quantity: ")
    )

    if requested_quantity <= 0:
        raise ValueError("Quantity must be positive.")

    if requested_quantity > stock:
        raise ValueError("Insufficient stock available.")

    print("Order placed successfully.")

except ValueError as error:
    print("Error:", error)


# 6. Custom Exception
print("\n----- Custom Exception -----")

class InsufficientStockError(Exception):
    pass


try:
    available_stock = 10
    order_quantity = int(
        input("Enter order quantity: ")
    )

    if order_quantity > available_stock:
        raise InsufficientStockError(
            "Not enough products in stock."
        )

    print("Order successful!")

except ValueError:
    print("Please enter a valid quantity.")

except InsufficientStockError as error:
    print("Stock Error:", error)
