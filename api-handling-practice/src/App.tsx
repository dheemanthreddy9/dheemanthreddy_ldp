import ApiPractice from "./components/ApiPractice";
import GetUsers from "./components/GetUsers";
import CrudUsers from "./components/Users";

const App = () => {
  return (
    <div className="app">
      <h1>API Handling Practice</h1>

      <ApiPractice />

      <GetUsers />

      <CrudUsers />
    </div>
  );
};

export default App;