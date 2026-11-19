
# 1. Class and Object
class Product:
    def __init__(self, name, price, quantity):
        self.name = name
        self.price = price
        self.quantity = quantity

    def display_product(self):
        print("Product Name:", self.name)
        print("Price:", self.price)
        print("Quantity:", self.quantity)

    def calculate_total(self):
        return self.price * self.quantity


# Creating objects
product1 = Product("Laptop", 50000, 2)
product2 = Product("Mouse", 500, 5)

print("----- Product Details -----")
product1.display_product()
print("Total Price:", product1.calculate_total())

print()

product2.display_product()
print("Total Price:", product2.calculate_total())


# 2. Encapsulation
class BankAccount:
    def __init__(self, balance):
        self.__balance = balance

    def deposit(self, amount):
        if amount > 0:
            self.__balance += amount
            print("Amount deposited successfully")
        else:
            print("Invalid deposit amount")

    def get_balance(self):
        return self.__balance


print("\n----- Encapsulation -----")

account = BankAccount(5000)
account.deposit(1000)
print("Current Balance:", account.get_balance())


# 3. Inheritance
class Employee:
    def __init__(self, name):
        self.name = name

    def display(self):
        print("Employee Name:", self.name)


class Developer(Employee):
    def work(self):
        print(self.name, "is developing applications")


print("\n----- Inheritance -----")

developer = Developer("Nikhil")
developer.display()
developer.work()


# 4. Polymorphism
class UPI:
    def make_payment(self):
        print("Payment made through UPI")


class Card:
    def make_payment(self):
        print("Payment made through Card")


print("\n----- Polymorphism -----")

payments = [UPI(), Card()]

for payment in payments:
    payment.make_payment()


# 5. Abstraction
from abc import ABC, abstractmethod

class Payment(ABC):

    @abstractmethod
    def process_payment(self):
        pass


class CashPayment(Payment):
    def process_payment(self):
        print("Cash payment processed successfully")


print("\n----- Abstraction -----")

payment = CashPayment()
payment.process_payment()
