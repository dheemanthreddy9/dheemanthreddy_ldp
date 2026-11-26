
# 1. Singleton Pattern

class ConfigManager:

    _instance = None

    def __new__(cls):

        if cls._instance is None:
            cls._instance = super().__new__(cls)

        return cls._instance


# 2. Factory Pattern

class EmployeeFactory:

    @staticmethod
    def create_employee(emp_id, name, department, salary):

        return Employee(emp_id, name, department, salary)


# 3. Observer Pattern

class EmployeeObserver:

    def update(self, message):
        print("Notification:", message)


class NotificationService:

    def __init__(self):
        self.observers = []

    def subscribe(self, observer):
        self.observers.append(observer)

    def notify(self, message):

        for observer in self.observers:
            observer.update(message)


# Import Employee class
from employee import Employee
