function greet(name) {
    console.log("Hello " + name);
}
function processUser(callback) {
    let user = "Dheemanth";
    callback(user);
}
processUser(greet);

// 2. CALLBACK HELL

setTimeout(() => {
    console.log("\nStep 1 : Login");
    setTimeout(() => {
        console.log("Step 2 : Fetch Profile");
        setTimeout(() => {
            console.log("Step 3 : Open Dashboard");
        }, 1000);
    }, 1000);
}, 1000);

// 3. PROMISE

function login() {
    return new Promise((resolve, reject) => {
        let success = true;
        setTimeout(() => {
            if (success) {
                resolve("Login Successful");
            } else {
                reject("Login Failed");
            }
        }, 2000);
    });
}

// 4. PROMISE CHAINING

login()
.then((message) => {
    console.log("\n" + message);
    return "Fetching User Profile";
})

.then((message) => {
    console.log(message);
    return "Loading Dashboard";
})
.then((message) => {
    console.log(message);
})

// 5. ERROR HANDLING

.then(() => {
    let marks = 20;
    if (marks < 35) {
        throw new Error("Student Failed");
    }
    return "Student Passed";
})
.then((message) => {
    console.log(message);
})
.catch((error) => {
    console.log("Error :", error.message);
})

// 6. FINALLY

.finally(() => {
    console.log("Promise Finished\n");
});

// 7. Promise.resolve()

Promise.resolve("Promise.resolve Example")
.then(console.log);

// 8. Promise.reject()

Promise.reject("Promise.reject Example")
.catch(console.log);

// 9. Promise.all()

Promise.all([
    Promise.resolve("HTML"),
    Promise.resolve("CSS"),
    Promise.resolve("JavaScript")
])

.then((result) => {
    console.log("\nPromise.all");
    console.log(result);
});

// 10. Promise.allSettled()

Promise.allSettled([
    Promise.resolve("API Success"),
    Promise.reject("API Failed")

])
.then((result) => {
    console.log("\nPromise.allSettled");
    console.log(result);
});

// 11. Promise.race()

Promise.race([
    new Promise(resolve =>
        setTimeout(() => resolve("Fast Response"), 1000)
    ),
    new Promise(resolve =>
        setTimeout(() => resolve("Slow Response"), 3000)

    )

])

.then((result) => {
    console.log("\nPromise.race");
    console.log(result);
});

// 12. MICROTASK

console.log("\nMicrotask Example");
setTimeout(() => {
    console.log("setTimeout");
}, 0);
Promise.resolve()
.then(() => {
    console.log("Promise Microtask");
});
console.log("End");




// ===============================
// Async/Await Complete Practice
// ===============================

// Function that returns a Promise
function task(name, time, shouldFail = false) {
    return new Promise((resolve, reject) => {

        setTimeout(() => {

            if (shouldFail) {
                reject(`${name} Failed`);
            } else {
                resolve(`${name} Completed`);
            }

        }, time);

    });
}

// Async function always returns a Promise
async function welcome() {
    return "Welcome to Async/Await";
}
async function main() {
    console.log("Program Started\n");

    // async returns Promise

    const message = await welcome();
    console.log(message);

    // Sequential Execution

    console.log("\nSequential Execution");
    console.time("Sequential");
    const login = await task("Login", 1000);
    console.log(login);
    const profile = await task("Profile", 1000);
    console.log(profile);
    const orders = await task("Orders", 1000);
    console.log(orders);
    console.timeEnd("Sequential");

    // Parallel Execution

    console.log("\nParallel Execution");
    console.time("Parallel");
    const result = await Promise.all([
        task("Image", 2000),
        task("Video", 2000),
        task("Music", 2000)
    ]);
    console.log(result);
    console.timeEnd("Parallel");

    // try...catch

    console.log("\nError Handling");
    try {
        const data = await task("Database", 1000, true);
        console.log(data);
    }
    catch(error) {
        console.log("Caught Error:", error);
    }
    finally {
        console.log("Finally Block Executed");
    }

    // throw

    console.log("\nCustom Error");
    try {
        let age = 16;
        if (age < 18) {
            throw new Error("Age must be 18 or above");
        }
        console.log("Access Granted");
    }
    catch(error) {
        console.log(error.message);
    }
    console.log("\nProgram Finished");
}
main();