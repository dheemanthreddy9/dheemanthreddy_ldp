# Function to calculate total price
def calculate_total(price, quantity):
    return price * quantity


# Function to calculate discount
def calculate_discount(total, discount):
    return total - (total * discount / 100)


# Function to display product details
def display_product(name, price):
    print("Product Name:", name)
    print("Product Price:", price)
