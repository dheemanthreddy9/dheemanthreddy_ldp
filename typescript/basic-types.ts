// Type Annotations
let studentName: string = "Dheemanth";
let age: number = 22;
let isIntern: boolean = true;
console.log(studentName);
console.log(age);
console.log(isIntern);

// Type Inference
let city = "Hyderabad";
let salary = 45000;
console.log(city);
console.log(salary);

// Null and Undefined
let data: null = null;
let value: undefined = undefined;
console.log(data);
console.log(value);

// Any
let anything: any = "Hello";
anything = 100;
anything = true;
console.log(anything);

// Unknown

let unknownValue: unknown = "TypeScript";
if (typeof unknownValue === "string") {
    console.log(unknownValue.toUpperCase());
}

// Void
function printMessage(): void {
    console.log("Learning TypeScript");
}
printMessage();

// Arrays
let numbers: number[] = [10, 20, 30];
let fruits: string[] = ["Apple", "Banana", "Mango"];
console.log(numbers);
console.log(fruits);

// Tuple
let employee: [number, string] = [101, "Dheemanth"];
console.log(employee);

