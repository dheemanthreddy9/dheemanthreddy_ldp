const heading = document.getElementById("heading");
const paragraph = document.getElementById("paragraph");
const image = document.getElementById("image");
const courses = document.getElementsByClassName("course");
const buttons = document.getElementsByTagName("button");

console.log(heading);
console.log(paragraph);
console.log(buttons);
console.log(image);
console.log(heading.textContent);
console.log(paragraph.innerText);
console.log(heading.innerHTML);


heading.textContent = "Learning DOM";
paragraph.innerText = "DOM Manipulation using JavaScript";
heading.innerHTML = "<i>Learning JavaScript DOM</i>";


console.log(image.getAttribute("src"));
image.setAttribute("src", "https://picsum.photos/300");

const link = document.getElementById("link");

link.setAttribute("href", "https://www.google.com");

link.setAttribute("target", "_blank");

heading.style.color = "blue";
heading.style.backgroundColor = "yellow";
heading.style.fontSize = "40px";
heading.style.border = "2px solid black";
heading.style.padding = "10px";

heading.classList.add("title");
heading.classList.remove("title");
heading.classList.toggle("active");
console.log(heading.classList.contains("active"));

const firstHeading = document.createElement("h2");

firstHeading.textContent = "This is Prepended";
container.prepend(firstHeading);
const secondPara = document.createElement("p");
secondPara.textContent = "Appended using appendChild";

container.appendChild(secondPara);
const newHeading = document.createElement("h3");
newHeading.textContent = "Heading Replaced";

console.log(paragraph.parentElement);
console.log(container.children);
console.log(container.firstElementChild);
console.log(container.lastElementChild);
console.log(paragraph.nextElementSibling);
console.log(image.previousElementSibling);
const input = document.getElementById("name");
console.log(input.value);



input.value = "Dheemanth";
input.placeholder = "Enter Full Name";



const firstFruit = document.querySelector("#fruits li");
console.log(firstFruit.dataset.id);
firstFruit.dataset.id = "500";
console.log(firstFruit.dataset.id);



Array.from(courses).forEach(function(course){
    console.log(course.textContent);
});
for(let i = 0; i < courses.length; i++){
    console.log(courses[i].textContent);
}


document.getElementById("btn1").addEventListener("click", function(){
    heading.textContent = input.value;
});



document.getElementById("btn2").addEventListener("click", function(){
    paragraph.style.color = "red";
});


document.getElementById("btn3").addEventListener("click", function(){
    image.setAttribute("src","https://picsum.photos/400");
});



document.getElementById("btn4").addEventListener("click", function(){
    const p = document.createElement("p");
    p.textContent = "New Paragraph Added";
    container.append(p);
});



document.getElementById("btn5").addEventListener("click", function(){
    if(container.lastElementChild){
        container.lastElementChild.remove();
    }
});



