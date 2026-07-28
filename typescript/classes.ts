// Class
class Student{
    constructor(
        public name:string,
        public age:number
    ){}
}
const student1=new Student("Dheemanth",22);
console.log(student1);

// Access Modifiers
class BankAccount{
    private balance:number = 0;

    constructor(initialBalance?: number){
        if(typeof initialBalance === 'number') this.balance = initialBalance;
    }

    getBalance(){
        return this.balance;
    }
}
const account=new BankAccount();
console.log(account.getBalance());

// Readonly
class Employee{
    constructor(
        readonly id:number,
        public name:string
    ){}
}
const emp1=new Employee(101,"Rahul");
console.log(emp1);

// Getter & Setter
class Person{
    private _age:number=22;
    get age(){
        return this._age;
    }
    set age(value:number){
        this._age=value;
    }
}
const person=new Person();
console.log(person.age);
person.age=25;
console.log(person.age);

// Static Member
class MathHelper{
    static pi=3.14;
}
console.log(MathHelper.pi);

// Inheritance
class Animal{
    constructor(public name:string){}
    speak(){
        console.log("Animal Sound");
    }
}
class Dog extends Animal{
    speak(){
        console.log("Bark");
    }
}
const dog=new Dog("Tommy");
dog.speak();

// Abstract Class
abstract class Shape{
    abstract area():number;
}
class Circle extends Shape{
    area():number{
        return 3.14*10*10;
    }
}
const circle=new Circle();
console.log(circle.area());