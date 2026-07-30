
// 1. let & const

let age = 22;
age = 23; 
const country = "India"; 
console.log("Age:", age);
console.log("Country:", country);

// 2. Arrow Function

const add = (a, b) => a + b;
console.log("Sum:", add(10, 20));


// 3. Template Literals

const name = "Dheemanth";
console.log(`Hello ${name}, Welcome to ES6!`);


// 4. Destructuring

const colors = ["Red", "Blue", "Green"];

const [firstColor, secondColor] = colors;

console.log(firstColor);
console.log(secondColor);
const person = {
    firstName: "John",
    city: "Hyderabad",
    experience: 2
};
const { firstName, city, experience } = person;
console.log(firstName);
console.log(city);
console.log(experience);


// 5. Spread Operator

const numbers1 = [1, 2, 3];
const numbers2 = [...numbers1, 4, 5];
console.log(numbers2);
const array1 = [10, 20];
const array2 = [30, 40];
const mergedArray = [...array1, ...array2];
console.log(mergedArray);


// 6. Rest Operator

function totalMarks(...marks) {
    let total = 0;
    for (let mark of marks) {
        total += mark;
    }
    return total;
}
console.log(totalMarks(80, 90, 70, 95));


// 7. Default Parameters

function greet(user = "Guest") {
    console.log(`Welcome ${user}`);
}
greet();
greet("Dheemanth");


// 8. Enhanced Object Literals


const employeeName = "Rahul";
const salary = 50000;

const employee = {
    employeeName,
    salary
};

console.log(employee);




