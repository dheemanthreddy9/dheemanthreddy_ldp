import { useState } from "react";
import api from "../api";

interface User {
  id: number;
  name: string;
  username: string;
  email: string;
}

const GetUsers = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const getUsers = async () => {
    try {
      setLoading(true);
      setError("");

      const response = await api.get<User[]>("/users");

      setUsers(response.data);
    } catch (error) {
      console.log(error);
      setError("Failed to load users");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h2>GET Users - Axios</h2>

      <button onClick={getUsers}>
        Get Users
      </button>

      {loading && <p>Loading users...</p>}

      {error && <p>{error}</p>}

      {!loading && !error && users.length > 0 && (
        <div>
          <h3>Users</h3>

          {users.map((user) => (
            <div key={user.id} className="user-card">
              <h3>{user.name}</h3>
              <p>Username: {user.username}</p>
              <p>Email: {user.email}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default GetUsers;