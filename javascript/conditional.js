let marks = 68;

if (marks >= 90) {
    console.log("Grade A");
} else if (marks >= 75) {
    console.log("Grade B");
} else if (marks >= 35) {
    console.log("Grade C");
} else {
    console.log("Fail");
}

let age = 20;

if (age >= 18) {
    console.log("Eligible to Vote");
} else {
    console.log("Not Eligible");
}

let number = 15;

if (number % 2 === 0) {
    console.log("Even");
} else {
    console.log("Odd");
}



//switch statement

let choice = 4;

switch(choice){

    case 1:
        console.log("Add");
        break;

    case 2:
        console.log("Subtract");
        break;

    case 3:
        console.log("Multiply");
        break;

    case 4:
        console.log("Divide");
        break;

    default:
        console.log("Invalid Choice");
}



//nested ternary operator


let examsheet = 82;

let grade =
    examsheet >= 90 ? "A" :
    examsheet >= 75 ? "B" :
    examsheet >= 35 ? "C" :
    "Fail";

console.log(grade);



//for loop

for (let i = 1; i <= 5; i++) {
    console.log(i);
}



// while loop
let i = 1;

while (i <= 5) {
    console.log("While:", i);
    i++;
}

// do...while loop
let j = 1;

do {
    console.log("Do While:", j);
    j++;
} while (j <= 5);

// break
for (let k = 1; k <= 10; k++) {

    if (k === 6) {
        break;
    }

    console.log("Break:", k);
}

// continue
for (let m = 1; m <= 10; m++) {

    if (m % 2 === 0) {
        continue;
    }

    console.log("Continue:", m);
}