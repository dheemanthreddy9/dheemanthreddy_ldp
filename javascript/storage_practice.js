// 1. localStorage

localStorage.setItem("name", "Dheemanth");
localStorage.setItem("city", "Hyderabad");
console.log("Name:", localStorage.getItem("name"));
console.log("City:", localStorage.getItem("city"));
console.log("Total Items:", localStorage.length);
console.log("First Key:", localStorage.key(0));
localStorage.removeItem("city");
console.log("City After Remove:", localStorage.getItem("city"));


// 2. Store Object using JSON

const employee = {
    id: 101,
    name: "John",
    department: "Developer"
};

localStorage.setItem("employee", JSON.stringify(employee));
const emp = JSON.parse(localStorage.getItem("employee"));
console.log(emp);


// 3. sessionStorage


sessionStorage.setItem("course", "JavaScript");
sessionStorage.setItem("trainer", "Kavya");

console.log("Course:", sessionStorage.getItem("course"));
console.log("Trainer:", sessionStorage.getItem("trainer"));

sessionStorage.removeItem("trainer");
console.log("Trainer After Remove:",
    sessionStorage.getItem("trainer"));


// 4. Cookies

document.cookie = "username=Dheemanth";
document.cookie = "country=India";
console.log("Cookies:");
console.log(document.cookie);

