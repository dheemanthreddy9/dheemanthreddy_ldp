import { useState } from "react";

interface User {
  id: number;
  name: string;
  username: string;
  email: string;
}
const ApiPractice = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const runAsyncTask = async () => {
    console.log("Async task started");
    setTimeout(() => {
      console.log("Async task completed");
    }, 2000);
    console.log("Other work is running");
  };

  const createPromise = () => {
    return new Promise<string>((resolve, reject) => {
      const success = true;
      if (success) {
        resolve("Promise completed successfully");
      } else {
        reject("Promise failed");
      }
    });
  };
  
  const handlePromise = async () => {
    const result = await createPromise();
    console.log(result);
  };
  
  const getUsers = async () => {
    try {
      setLoading(true);
      setError("");
      const response = await fetch(
        "https://jsonplaceholder.typicode.com/users"
      );
      if (!response.ok) {
        throw new Error("Failed to fetch users");
      }
      const data: User[] = await response.json();
      setUsers(data);
    } catch (error) {
      console.log(error);
      setError("Something went wrong while fetching users");
    } finally {
      setLoading(false);
    }
  };
  return (
    <div className="api-container">
      <h2>API Handling Practice</h2>
      {/* Async */}
      <section>
        <h3>1. Async</h3>
        <button onClick={runAsyncTask}>
          Run Async Task
        </button>
      </section>
      {/* Promise */}
      <section>
        <h3>2. Promise</h3>
        <button
          onClick={() => {
            createPromise()
              .then((result) => {
                console.log(result);
              })
              .catch((error) => {
                console.log(error);
              });
          }}
        >
          Run Promise
        </button>
      </section>
      {/* Async/Await */}
      <section>
        <h3>3. Async / Await</h3>
        <button onClick={handlePromise}>
          Run Async/Await
        </button>
      </section>
      {/* GET API */}
      <section>
        <h3>4. GET Users API</h3>
        <button onClick={getUsers}>
          Get Users
        </button>
        {/* Loading */}
        {loading && <p>Loading users...</p>}
        {/* Error */}
        {error && <p>{error}</p>}
        {/* Display Data */}
        {!loading && !error && users.length > 0 && (
          <div>
            <h3>Users</h3>
            {users.map((user) => (
              <div key={user.id} className="user-card">
                <h4>{user.name}</h4>
                <p>Username: {user.username}</p>
                <p>Email: {user.email}</p>
              </div>
            ))}
          </div>
        )}
      </section>
    </div>
  );
};

export default ApiPractice;