import {
  useCallback,
  useMemo,
  useRef,
  useState,
} from "react";

const users = [
  "Dheemanth",
  "Nikhil",
  "Rahul",
  "Arjun",
  "Kiran",
];

function SearchUsers() {
  const [search, setSearch] = useState("");

  const inputRef = useRef<HTMLInputElement>(null);

  const filteredUsers = useMemo(() => {
    console.log("Filtering users...");

    return users.filter((user) =>
      user.toLowerCase().includes(search.toLowerCase())
    );
  }, [search]);

  const clearSearch = useCallback(() => {
    setSearch("");
    inputRef.current?.focus();
  }, []);

  return (
    <section>
      <h2>Search Users</h2>

      <input
        ref={inputRef}
        value={search}
        onChange={(event) => setSearch(event.target.value)}
        placeholder="Search user"
      />

      <button onClick={clearSearch}>
        Clear
      </button>

      <ul>
        {filteredUsers.map((user) => (
          <li key={user}>{user}</li>
        ))}
      </ul>
    </section>
  );
}

export default SearchUsers;