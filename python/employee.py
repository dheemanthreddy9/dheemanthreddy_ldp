
from collections import Counter, defaultdict, deque


# Custom Class
class Employee:

    def __init__(self, emp_id, name, department, salary):
        self.emp_id = emp_id
        self.name = name
        self.department = department
        self.salary = salary

    def display(self):
        print(
            f"ID: {self.emp_id}, "
            f"Name: {self.name}, "
            f"Department: {self.department}, "
            f"Salary: {self.salary}"
        )


# Employee Management
class EmployeeManagement:

    def __init__(self):
        self.employees = []
        self.department_count = defaultdict(int)
        self.tasks = deque()

    # Add employee
    def add_employee(self, employee):
        self.employees.append(employee)
        self.department_count[employee.department] += 1

    # Display employees
    def display_employees(self):
        for employee in self.employees:
            employee.display()

    # Count employees by department
    def count_departments(self):
        return Counter(
            employee.department
            for employee in self.employees
        )

    # Display unique departments
    def unique_departments(self):
        return {
            employee.department
            for employee in self.employees
        }

    # Add tasks
    def add_task(self, task):
        self.tasks.append(task)

    # Process tasks
    def process_tasks(self):
        while self.tasks:
            print("Processing:", self.tasks.popleft())

    # Generator
    def employee_names(self):
        for employee in self.employees:
            yield employee.name
