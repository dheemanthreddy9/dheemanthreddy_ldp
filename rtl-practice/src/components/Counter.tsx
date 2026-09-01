import { useState } from "react";

const Counter = () => {
  const [count, setCount] = useState(0);

  const increment = () => {
    setCount((previousCount) => previousCount + 1);
  };

  const decrement = () => {
    setCount((previousCount) => previousCount - 1);
  };

  return (
    <section>
      <h2>Counter</h2>

      <p>Count: {count}</p>

      <button onClick={increment}>Increment</button>

      <button onClick={decrement}>Decrement</button>
    </section>
  );
};

export default Counter;