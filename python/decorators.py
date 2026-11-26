
# Creating a decorator

def my_decorator(func):

    def wrapper():
        print("Before function execution")

        func()

        print("After function execution")

    return wrapper


# Applying the decorator

@my_decorator
def greet():
    print("Hello Nikhil, welcome to Python!")


#Calling the decorated function

greet()



def my_decorator(func):

    def wrapper(*args, **kwargs):
        print("Function execution started")

        result = func(*args, **kwargs)

        print("Function execution completed")

        return result

    return wrapper


@my_decorator
def add_numbers(a, b):
    return a + b


result = add_numbers(10, 20)

print("Result:", result)
