function studentDetails(name, age, college) {

    console.log("Name:", name);
    console.log("Age:", age);
    console.log("College:", college);

}

studentDetails("Dheemanth", 21, "SRM");

function welcome(name){
    console.log("welcome" + name);

}
welcome("Dheemanth");
welcome("nick");


function add(a, b){
    return a + b;
}
add(629, 589);


function iseven(n){
    return n % 2 === 0;
}
console.log(iseven(94));
console.log(iseven(1894));

function largest(a, b){
    if(a>b){
        return a;
    }else{
        return b;
    }
}
console.log(largest(85, 75.2));




//function expression

let multiply = function(a, b) {
    return a * b;
};

console.log(multiply(5, 6));



let student = function(name, age) {
    console.log("Name:", name);
    console.log("Age:", age);
};

student("Dheemanth", 21);


//arrow function

let cube = number => number * number * number;
console.log(cube(3));

let isAdult = age => age >= 18;
console.log(isAdult(20));
console.log(isAdult(15));

