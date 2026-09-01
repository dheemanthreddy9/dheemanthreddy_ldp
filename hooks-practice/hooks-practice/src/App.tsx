import UserContext, {
  type User,
} from "./context/UserContext";

import Dashboard from "./components/Dashboard";

const App = () => {
  const user: User = {
    name: "Nikhil",
    age: 25,
    role: "senior ana",
  };

  return (
    <UserContext.Provider value={user}>
      <Dashboard />
    </UserContext.Provider>
  );
};

export default App;