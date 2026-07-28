//1.  Write a program to demonstrate how a function can be passed as a parameter to another function.

function greet() {
    console.log("Hello, Welcome!");
}
function executeFunction(myFunction) {
    console.log("Executing function...");
    myFunction();
}
executeFunction(greet);



/*2. An arrow function takes two arguments firstName and lastName and returns a 2 letter string that represents the first letter of both the arguments. For the arguments Roger and Waters, the function returns ‘RW’. Write this function.
Submit the github link to the code*/


let getInitials = (firstName, lastName) => {
    return firstName[0] + lastName[0];
};

console.log(getInitials("Roger", "Waters")); 

