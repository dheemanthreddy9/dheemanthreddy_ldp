# Import our user-defined module
import calculator

# Product details
product_name = "Laptop"
price = 50000
quantity = 2
discount = 10

print("----- Retail Store Billing -----")

# Calling module functions
calculator.display_product(product_name, price)

# Calculate total
total = calculator.calculate_total(price, quantity)

print("Quantity:", quantity)
print("Total Amount:", total)

# Calculate discount
final_price = calculator.calculate_discount(
    total, discount
)

print("Discount:", discount, "%")
print("Final Amount:", final_price)
