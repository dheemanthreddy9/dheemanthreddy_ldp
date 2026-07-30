let students = ["Rahul","Ravi","Dheemanth"];

console.log(students);

console.log(students[0]);

console.log(students[2]);

students.push("Kiran");

students.unshift("Ajay");

students.pop();

students.shift();

console.log(students);

console.log(students.length);

for(let i=0;i<students.length;i++){

    console.log(students[i]);

}



//objects

let student = {
    name: "Dheemanth",
    age: 21,
    college: "SRM",
    city: "Hyderabad"
};

console.log(student);

console.log(student.name);

console.log(student.age);

console.log(student.college);

student.city = "Chennai";

console.log(student.city);

student.marks = 95;

console.log(student);

delete student.college;

console.log(student);