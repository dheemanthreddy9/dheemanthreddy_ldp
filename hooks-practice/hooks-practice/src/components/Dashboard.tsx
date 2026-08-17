import UserInfo from "./UserInfo";
import Counter from "./Counter";
import SearchUsers from "./SearchUsers";
import useCounter from "../hooks/useCounter";

function Dashboard() {
  const {
    count,
    increment,
    decrement,
    reset,
  } = useCounter(10);

  return (
    <div>
      <h1>React Hooks Dashboard</h1>

      <UserInfo />

      <Counter />

      <SearchUsers />

      <section>
        <h2>Custom Hook Counter</h2>

        <h3>{count}</h3>

        <button onClick={increment}>
          +
        </button>

        <button onClick={decrement}>
          -
        </button>

        <button onClick={reset}>
          Reset
        </button>
      </section>
    </div>
  );
}

export default Dashboard;