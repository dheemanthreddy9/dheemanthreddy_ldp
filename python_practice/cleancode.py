
#Retail Store Inventory Management System

STORE_NAME = "My Retail Store"
LOW_STOCK_LIMIT = 6

inventory = {
    "Laptop": {"price": 50000, "quantity": 10},
    "Mouse": {"price": 500, "quantity": 3},
    "Keyboard": {"price": 1500, "quantity": 8},
    "Monitor": {"price": 12000, "quantity": 4},
}


def calculate_product_value(price, quantity):
    #Calculate the total value of a product.
    return price * quantity


def display_inventory():
    #Display all products and their stock values.
    print(f"\nWelcome to {STORE_NAME}")
    print("-" * 40)

    for product_name, product_details in inventory.items():
        price = product_details["price"]
        quantity = product_details["quantity"]

        product_value = calculate_product_value(price, quantity)

        print(f"Product: {product_name}")
        print(f"Price: Rs. {price}")
        print(f"Quantity: {quantity}")
        print(f"Stock Value: Rs. {product_value}")
        print("-" * 40)


def check_low_stock():
    #Display products with low stock.
    print("\nLow Stock Report")
    print("-" * 40)

    for product_name, product_details in inventory.items():
        quantity = product_details["quantity"]

        if quantity < LOW_STOCK_LIMIT:
            print(f"Warning: {product_name} has only {quantity} units left.")


def display_total_products():
   #Display the total number of units in stock.
    total_quantity = 0

    for product_details in inventory.values():
        total_quantity += product_details["quantity"]

    print("\nTotal Products in Stock:", total_quantity)


def calculate_total_inventory_value():
    #Calculate the total value of the inventory.
    total_inventory_value = 0

    for product_details in inventory.values():
        price = product_details["price"]
        quantity = product_details["quantity"]

        total_inventory_value += calculate_product_value(price, quantity)

    return total_inventory_value


def main():
    #Run inventory management operations.
    display_inventory()
    check_low_stock()
    display_total_products()

    total_value = calculate_total_inventory_value()
    print("\nTotal Inventory Value:", total_value)


if __name__ == "__main__":
    main()
