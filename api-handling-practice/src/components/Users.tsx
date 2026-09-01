import { useState } from "react";
import api from "../api";

interface User {
  id: number;
  name: string;
  username: string;
  email: string;
}

const CrudUsers = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [editingId, setEditingId] = useState<number | null>(null);
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

  const createUser = async () => {
    try {
      if (!name || !username || !email) {
        setError("Please fill all fields");
        return;
      }
      setLoading(true);
      setError("");
      const response = await api.post<User>("/users", {
        name,
        username,
        email,
      });
      setUsers((previousUsers) => [
        ...previousUsers,
        response.data,
      ]);

      setName("");
      setUsername("");
      setEmail("");
    } catch (error) {
      console.log(error);
      setError("Failed to create user");
    } finally {
      setLoading(false);
    }
  };

  const updateUser = async () => {
    try {
      if (editingId === null) {
        return;
      }
      if (!name || !username || !email) {
        setError("Please fill all fields");
        return;
      }
      setLoading(true);
      setError("");
      const response = await api.put<User>(
        `/users/${editingId}`,
        {
          id: editingId,
          name,
          username,
          email,
        }
      );

      setUsers((previousUsers) =>
        previousUsers.map((user) =>
          user.id === editingId
            ? response.data
            : user
        )
      );

      setEditingId(null);
      setName("");
      setUsername("");
      setEmail("");
    } catch (error) {
      console.log(error);
      setError("Failed to update user");
    } finally {
      setLoading(false);
    }
  };

  const patchUser = async (id: number) => {
    try {
      setLoading(true);
      setError("");
      const response = await api.patch<User>(
        `/users/${id}`,
        {
          name: "Updated Name",
        }
      );
      setUsers((previousUsers) =>
        previousUsers.map((user) =>
          user.id === id
            ? response.data
            : user
        )
      );
    } catch (error) {
      console.log(error);
      setError("Failed to partially update user");
    } finally {
      setLoading(false);
    }
  };

  const deleteUser = async (id: number) => {
    try {
      setLoading(true);
      setError("");
      await api.delete(`/users/${id}`);
      setUsers((previousUsers) =>
        previousUsers.filter(
          (user) => user.id !== id
        )
      );
    } catch (error) {
      console.log(error);
      setError("Failed to delete user");
    } finally {
      setLoading(false);
    }
  };

  const startEdit = (user: User) => {
    setEditingId(user.id);
    setName(user.name);
    setUsername(user.username);
    setEmail(user.email);
    setError("");
  };

  const cancelEdit = () => {
    setEditingId(null);
    setName("");
    setUsername("");
    setEmail("");
    setError("");
  };

  return (
    <div className="api-container">
      <h2>Axios CRUD Users</h2>

      {/* GET */}
      <section>
        <h3>1. GET - Read Users</h3>

        <button onClick={getUsers}>
          Get Users
        </button>
      </section>

      {/* POST / PUT */}
      <section>
        <h3>
          {editingId !== null
            ? "2. PUT - Update User"
            : "2. POST - Create User"}
        </h3>

        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={(event) =>
            setName(event.target.value)
          }
        />

        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(event) =>
            setUsername(event.target.value)
          }
        />

        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(event) =>
            setEmail(event.target.value)
          }
        />

        {editingId !== null ? (
          <>
            <button onClick={updateUser}>
              Update User
            </button>

            <button onClick={cancelEdit}>
              Cancel
            </button>
          </>
        ) : (
          <button onClick={createUser}>
            Create User
          </button>
        )}
      </section>

      {/* PATCH */}
      <section>
        <h3>3. PATCH - Partial Update</h3>

      </section>

      {/* Loading */}
      {loading && (
        <p>Loading...</p>
      )}

      {/* Error */}
      {error && (
        <p>{error}</p>
      )}

      {/* Users */}
      <section>
        <h3>Users</h3>

        {users.length === 0 && !loading && (
          <p>No users available.</p>
        )}

        {users.map((user) => (
          <div
            key={user.id}
            className="user-card"
          >
            <h3>{user.name}</h3>

            <p>
              Username: {user.username}
            </p>

            <p>
              Email: {user.email}
            </p>

            <button
              onClick={() => startEdit(user)}
            >
              Edit
            </button>

            <button
              onClick={() => patchUser(user.id)}
            >
              PATCH Name
            </button>

            <button
              onClick={() => deleteUser(user.id)}
            >
              Delete
            </button>
          </div>
        ))}
      </section>
    </div>
  );
};

export default CrudUsers;