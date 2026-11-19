# File Handling Practice
with open("products.txt", "w") as file:
    file.write("Laptop - 50000\n")
    file.write("Mouse - 500\n")
    file.write("Keyboard - 1500\n")

print("Product details saved successfully.")


# 2. Reading the complete file
print("\n----- Reading File -----")

with open("products.txt", "r") as file:
    content = file.read()
    print(content)


# 3. Appending new data
print("----- Appending Data -----")

with open("products.txt", "a") as file:
    file.write("Monitor - 12000\n")

print("New product added successfully.")


# 4. Reading line by line
print("\n----- Reading Line by Line -----")

with open("products.txt", "r") as file:
    for line in file:
        print(line.strip())


# 5. Checking whether a file exists
print("\n----- Checking File -----")

from pathlib import Path

file_path = Path("products.txt")

if file_path.exists():
    print("Product file exists.")
else:
    print("Product file does not exist.")


# 6. Writing CSV File
print("\n----- CSV File -----")

import csv

with open("products.csv", "w", newline="") as file:
    writer = csv.writer(file)

    writer.writerow(["Product", "Price", "Quantity"])
    writer.writerow(["Laptop", 50000, 5])
    writer.writerow(["Mouse", 500, 10])
    writer.writerow(["Keyboard", 1500, 8])

print("CSV file created successfully.")


# 7. Reading CSV File
print("\n----- Reading CSV File -----")

with open("products.csv", "r") as file:
    reader = csv.reader(file)

    for row in reader:
        print(row)


# 8. Writing JSON File
print("\n----- JSON File -----")

import json

product = {
    "name": "Laptop",
    "price": 50000,
    "quantity": 5
}

with open("product.json", "w") as file:
    json.dump(product, file, indent=4)

print("JSON file created successfully.")


# 9. Reading JSON File
print("\n----- Reading JSON File -----")

with open("product.json", "r") as file:
    data = json.load(file)

    print("Product:", data["name"])
    print("Price:", data["price"])
    print("Quantity:", data["quantity"])
