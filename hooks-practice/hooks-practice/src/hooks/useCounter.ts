import { useState } from "react";

function useCounter(initialValue: number = 0) {
  const [count, setCount] = useState(initialValue);

  const increment = () => {
    setCount((previousCount) => previousCount + 1);
  };

  const decrement = () => {
    setCount((previousCount) => previousCount - 1);
  };

  const reset = () => {
    setCount(initialValue);
  };

  return {
    count,
    increment,
    decrement,
    reset,
  };
}

export default useCounter;