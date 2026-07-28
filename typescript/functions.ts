// Function Types
function greet(name: string): string {
    return `Hello ${name}`;
}
console.log(greet("Dheemanth"));

// Parameter Types
function add(a: number, b: number): number {
    return a + b;
}
console.log(add(10, 20));

// Optional Parameters
function display(name: string, city?: string): void {
    console.log(name);
    if (city) {
        console.log(city);
    }
}
display("Dheemanth");
display("Dheemanth", "Hyderabad");

// Default Parameters
function welcome(name: string = "Guest") {
    console.log(`Welcome ${name}`);
}
welcome();
welcome("Dheemanth");

// Rest Parameters
function total(...numbers: number[]): number {
    let sum = 0;
    for (let num of numbers) {
        sum += num;
    }
    return sum;
}
console.log(total(10,20,30,40));

// Arrow Function
const multiply = (a:number,b:number):number=>{
    return a*b;
}
console.log(multiply(5,6));

// Function Overloading
function show(value:string):void;
function show(value:number):void;
function show(value:any):void{
    console.log(value);
}
show("Hello");
show(100);

// Function Type Variable
let calculate:(a:number,b:number)=>number;
calculate=(x,y)=>x+y;
console.log(calculate(50,20));