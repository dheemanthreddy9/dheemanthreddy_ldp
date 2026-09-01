import { useEffect, useState } from "react";

interface User {
  id: number;
  name: string;
}

const UserList = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadUsers = async () => {
      await new Promise((resolve) => setTimeout(resolve, 500));

      setUsers([
        { id: 1, name: "Dheemanth" },
        { id: 2, name: "Rahul" },
        { id: 3, name: "Priya" },
      ]);

      setLoading(false);
    };

    loadUsers();
  }, []);

  if (loading) {
    return <p>Loading users...</p>;
  }

  return (
    <section>
      <h2>Users</h2>

      <ul>
        {users.map((user) => (
          <li key={user.id}>{user.name}</li>
        ))}
      </ul>
    </section>
  );
};

export default UserList;