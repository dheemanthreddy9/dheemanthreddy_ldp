// 1. Global Scope


let company = "Zemoso";
function globalExample() {
    console.log("Inside Function (Global Variable):", company);
}
console.log("Outside Function (Global Variable):", company);
globalExample();


// 2. Function Scope

function functionScopeExample() {
    let employee = "Dheemanth";

    console.log("Inside Function:", employee);
}
functionScopeExample();


// 3. Block Scope

if (true) {
    let department = "Engineering";
    const location = "Hyderabad";

    console.log("Inside Block (let):", department);
    console.log("Inside Block (const):", location);
}

if (true) {
    var project = "JavaScript Training";
}

console.log("Outside Block (var):", project);


//cloures
const outer = () => {
    let city = "Hyderabad";
    return () => {
        console.log(city);
    };
};
const showCity = outer();
showCity();



const createScore = () => {
    let score = 10;
    return () => {
        score += 5;
        console.log(score);
    };
};
const game = createScore();
game();
game();
game();


function Car(brand, model) {
    this.brand = brand;
    this.model = model;

    this.show = function () {
        console.log(`${this.brand} ${this.model}`);
    };
}
const car = new Car("Toyota", "Fortuner");
car.show();