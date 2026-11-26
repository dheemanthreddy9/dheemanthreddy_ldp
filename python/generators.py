
# 1. Creating a generator

def generate_numbers():

    yield 10
    yield 20
    yield 30
    yield 40
    yield 50


# 2. Creating a generator object

numbers = generate_numbers()


# 3. Retrieving values using next()

print(next(numbers))
print(next(numbers))
print(next(numbers))


# 4. Iterating through remaining values

for number in numbers:
    print(number)



def generate_even_numbers(limit):

    for number in range(1, limit + 1):

        if number % 2 == 0:
            yield number


even_numbers = generate_even_numbers(10)

for number in even_numbers:
    print(number)
