
/*1 Refactored into a one-liner
With ES6 arrow functions, you can omit the curly braces {} and the return keyword for implicit returns:*/

const printName = (name) => "Hi " + name;
console.log(printName("Nick"));


/*2 Rewritten using Template Literals
Replace string concatenation (+) with backticks (`) and ${} string interpolation:*/

const printBill = (name, bill) => `Hi ${name}, please pay: ${bill}`;
console.log(printBill("Nick", 100));

/*3 Modified using Object Destructuring
Extract name and age directly from the person object in a single line:*/

const person = {
  name: "Noam Chomsky",
  age: 92
};

const printPerson = ({ name, age }) => {
  console.log(name);
  console.log(age);
};

printPerson(person);