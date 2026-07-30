/*let → Use when the value can change.
const → Use when the value should never change.
var → Older keyword. Avoid using it in new code.*/


let college = "SRM";      
let city = "Hyderabad";


console.log(college);
console.log(city);
const country = "India";
console.log(country);

// country = "USA";  This will throw an error because country is a constant and cannot be reassigned


let name = "Dheemanth";         //string
let age = 21;                  //Number
let isStudent = true;         //boolean
let phone;                   //undefined
let middleName = null;      //null

console.log(typeof name);
console.log(typeof age);
console.log(typeof isStudent);
console.log(typeof phone);
console.log(typeof middleName);