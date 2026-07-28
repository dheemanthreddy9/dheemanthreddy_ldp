// Object Type

let student:{
    name:string;
    age:number;
}={
    name:"Dheemanth",
    age:22
};
console.log(student);

// Nested Object
let company={
    id:101,
    name:"Rahul",
    address:{
        city:"Hyderabad",
        pincode:500001
    }
};
console.log(company.address.city);

// Interface
interface Student{
    name:string;
    age:number;
}
let s1:Student={
    name:"Dheemanth",
    age:22
};
console.log(s1);

// Optional Property
interface User{
    name:string;
    age?:number;
}
let user1:User={
    name:"Ram"
};
console.log(user1);

// Readonly Property
interface Employee{
    readonly id:number;
    name:string;
}
let emp:Employee={
    id:101,
    name:"Ravi"
};
console.log(emp);

// Interface Extension
interface Person{
    name:string;
}
interface Developer extends Person{
    language:string;
}
let dev:Developer={
    name:"Dheemanth",
    language:"TypeScript"
};
console.log(dev);

// Type Alias

type Product={
    id:number;
    name:string;
};
let product:Product={
    id:1,
    name:"Laptop"
};
console.log(product);