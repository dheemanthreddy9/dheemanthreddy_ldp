import Counter from "./components/Counter";
import LoginForm from "./components/LoginForm";
import UserList from "./components/UserList";

const App = () => {
  return (
    <main>
      <h1>React Testing Library Practice</h1>
      <Counter />
      <hr />
      <LoginForm />
      <hr />
      <UserList />
    </main>
  );
};
export default App;