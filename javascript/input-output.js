let name = prompt("Enter your name");
let age = prompt("Enter your age");

console.log("Name:", name);
console.log("Age:", age);

alert("Welcome " + name);





//type conversion


// String to Number
let a = "10";
let b = "20";

console.log(a + b);

a = Number(a);
b = Number(b);

console.log(a + b);

// Number to String
let age = 21;

console.log(typeof age);

age = String(age);

console.log(typeof age);

// Boolean
console.log(Boolean(1));
console.log(Boolean(0));
console.log(Boolean(""));
console.log(Boolean("Hello"));

// Implicit Conversion
console.log("5" * 2);
console.log("10" - "3");
console.log("10" + 3);