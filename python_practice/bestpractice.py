
import json
import logging
import unittest

# Constants
TAX_RATE = 0.08
DISCOUNT = 10

# Logging configuration
logging.basicConfig(level=logging.INFO)


# Sample order data
orders = [
    {"name": "Keyboard", "price": 120, "quantity": 1},
    {"name": "USB Cable", "price": 15.50, "quantity": 2},
    {"name": "Mouse", "price": 50, "quantity": 2}
]


def calculate_total(orders, discount=0):
    """Calculate the final order amount."""

    subtotal = 0

    for order in orders:
        subtotal += order["price"] * order["quantity"]

    total = subtotal - discount

    if total < 0:
        total = 0

    tax = total * TAX_RATE
    final_total = total + tax

    return round(final_total, 2)


def display_orders():
    """Display all order details."""

    print("\nOrder Details")
    print("-" * 30)

    for order in orders:
        print("Product:", order["name"])
        print("Price:", order["price"])
        print("Quantity:", order["quantity"])
        print("-" * 30)


def save_orders():
    """Save order details into a JSON file."""

    try:
        with open("orders.json", "w", encoding="utf-8") as file:
            json.dump(orders, file, indent=4)

        logging.info("Orders saved successfully.")

    except OSError as error:
        logging.error("Unable to save orders: %s", error)


def load_orders():
    """Read order details from the JSON file."""

    try:
        with open("orders.json", "r", encoding="utf-8") as file:
            data = json.load(file)

        print("\nLoaded Orders:", data)

    except FileNotFoundError:
        logging.error("Orders file not found.")

    except json.JSONDecodeError:
        logging.error("Invalid JSON data.")


def main():
    """Run the order management program."""

    display_orders()

    total = calculate_total(orders, DISCOUNT)

    print("\nDiscount:", DISCOUNT)
    print("Final Order Total:", total)

    save_orders()
    load_orders()


# Unit testing
class TestOrder(unittest.TestCase):

    def test_calculate_total(self):
        result = calculate_total(orders, 10)
        self.assertEqual(result, 248.94)

    def test_zero_discount(self):
        result = calculate_total(orders)
        self.assertEqual(result, 259.74)

    def test_large_discount(self):
        result = calculate_total(orders, 500)
        self.assertEqual(result, 0)


if __name__ == "__main__":
    main()
    unittest.main(argv=["first-arg"], exit=False)
