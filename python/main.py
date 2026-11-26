
# Import dotenv
from dotenv import load_dotenv
import os

# Import employee management
from employee import Employee, EmployeeManagement

# Import design patterns
from patterns import (
    ConfigManager,
    EmployeeFactory,
    EmployeeObserver,
    NotificationService
)


# DOTENV PACKAGE

load_dotenv()

app_name = os.getenv("APP_NAME")
database_name = os.getenv("DATABASE_NAME")
debug = os.getenv("DEBUG")

print("Application:", app_name)
print("Database:", database_name)
print("Debug Mode:", debug)


# ADVANCED DATA STRUCTURES

management = EmployeeManagement()

# Create employees using Factory Pattern
emp1 = EmployeeFactory.create_employee(
    1, "Nikhil", "IT", 50000
)

emp2 = EmployeeFactory.create_employee(
    2, "Kalyan", "HR", 40000
)

emp3 = EmployeeFactory.create_employee(
    3, "Harsha", "IT", 60000
)

# Add employees
management.add_employee(emp1)
management.add_employee(emp2)
management.add_employee(emp3)


# Display employee details
print("\nEmployee Details:")
management.display_employees()


# Counter
print("\nDepartment Count:")
print(management.count_departments())


# defaultdict
print("\nDepartment Dictionary:")
print(dict(management.department_count))


# Set
print("\nUnique Departments:")
print(management.unique_departments())


# Queue using deque
print("\nTask Processing:")

management.add_task("Update Employee")
management.add_task("Generate Salary Report")
management.add_task("Send Employee Details")

management.process_tasks()


# Generator
print("\nEmployee Names:")

for name in management.employee_names():
    print(name)


# DESIGN PATTERNS

# Singleton
config1 = ConfigManager()
config2 = ConfigManager()

print("\nSingleton Pattern:")
print("Same Instance:", config1 is config2)


# Observer
print("\nObserver Pattern:")

notification = NotificationService()

observer1 = EmployeeObserver()
observer2 = EmployeeObserver()

notification.subscribe(observer1)
notification.subscribe(observer2)

notification.notify("New Employee Added Successfully")
