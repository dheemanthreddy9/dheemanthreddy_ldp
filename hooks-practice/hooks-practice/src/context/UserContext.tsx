import { createContext } from "react";

export type User = {
  name: string;
  role: string;
  age: number;
};

const UserContext = createContext<User | null>(null);

export default UserContext;