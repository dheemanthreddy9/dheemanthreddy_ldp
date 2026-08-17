import { useContext } from "react";
import UserContext from "../context/UserContext";

function UserInfo() {
  const user = useContext(UserContext);

  if (!user) {
    return <p>User information not available.</p>;
  }

  return (
    <section>
      <h2>User Information</h2>

      <p>Name: {user.name}</p>
      <p>Age: {user.age}</p>
      <p>Role: {user.role}</p>
    </section>
  );
}

export default UserInfo;