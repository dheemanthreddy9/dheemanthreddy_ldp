/*import { useState } from "react";

const RegistrationForm = () => {
  return (
    <div>
      <h1>User Registration</h1>

      <form>
        <div>
          <label>Name</label>
          <input type="text" />
        </div>

        <div>
          <label>Email</label>
          <input type="email" />
        </div>

        <div>
          <label>Password</label>
          <input type="password" />
        </div>

        <button type="submit">
          Register
        </button>
      </form>
    </div>
  );
}

export default RegistrationForm;
*/




/*
import { useState } from "react";

const RegistrationForm = () => {
  return (
    <div>
      <h1>User Registration</h1>

      <form>
        <div>
          <label>Name</label>
          <input type="text" />
        </div>

        <div>
          <label>Email</label>
          <input type="email" />
        </div>

        <div>
          <label>Password</label>
          <input type="password" />
        </div>

        <button type="submit">
          Register
        </button>

        <button
          type="button"
          onClick={() => {
            console.log("Test button clicked");
          }}
        >
          Test Click
        </button>
      </form>
    </div>
  );
}

export default RegistrationForm;
/*





/*
import { useState } from "react";

const RegistrationForm = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <div>
      <h1>User Registration</h1>

      <form>
        <div>
          <label>Name</label>
          <input
            type="text"
            value={name}
          />
        </div>

        <div>
          <label>Email</label>
          <input
            type="email"
            value={email}
          />
        </div>

        <div>
          <label>Password</label>
          <input
            type="password"
            value={password}
          />
        </div>

        <button type="submit">
          Register
        </button>

        <button
          type="button"
          onClick={() => {
            console.log("Test button clicked");
          }}
        >
          Test Click
        </button>
      </form>
    </div>
  );
}

export default RegistrationForm;
*/






/*
import { useState } from "react";

const RegistrationForm = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <div>
      <h1>User Registration</h1>

      <form>
        <div>
          <label>Name</label>

          <input
            type="text"
            value={name}
            onChange={(event) => {
              setName(event.target.value);
            }}
          />
        </div>

        <div>
          <label>Email</label>

          <input
            type="email"
            value={email}
            onChange={(event) => {
              setEmail(event.target.value);
            }}
          />
        </div>

        <div>
          <label>Password</label>

          <input
            type="password"
            value={password}
            onChange={(event) => {
              setPassword(event.target.value);
            }}
          />
        </div>

        <button type="submit">
          Register
        </button>

        <button
          type="button"
          onClick={() => {
            console.log("Test button clicked");
          }}
        >
          Test Click
        </button>
      </form>
    </div>
  );
}

export default RegistrationForm;
*/






/*
import { useState } from "react";

const RegistrationForm = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (
    event: React.FormEvent<HTMLFormElement>
  ) => {
    event.preventDefault();

    console.log("Form submitted");
    console.log("Name:", name);
    console.log("Email:", email);
    console.log("Password:", password);
  };

  return (
    <div>
      <h1>User Registration</h1>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Name</label>

          <input
            type="text"
            value={name}
            onChange={(event) => {
              setName(event.target.value);
            }}
          />
        </div>

        <div>
          <label>Email</label>

          <input
            type="email"
            value={email}
            onChange={(event) => {
              setEmail(event.target.value);
            }}
          />
        </div>

        <div>
          <label>Password</label>

          <input
            type="password"
            value={password}
            onChange={(event) => {
              setPassword(event.target.value);
            }}
          />
        </div>

        <button type="submit">
          Register
        </button>

        <button
          type="button"
          onClick={() => {
            console.log("Test button clicked");
          }}
        >
          Test Click
        </button>
      </form>
    </div>
  );
}

export default RegistrationForm;
*/







import { useState } from "react";

const RegistrationForm = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [error, setError] = useState("");

  const handleSubmit = (
    event: React.FormEvent<HTMLFormElement>
  ) => {
    event.preventDefault();

    if (!name.trim()) {
      setError("Name is required");
      return;
    }

    if (!email.trim()) {
      setError("Email is required");
      return;
    }

    if (!email.includes("@")) {
      setError("Please enter a valid email");
      return;
    }

    if (!password.trim()) {
      setError("Password is required");
      return;
    }

    if (password.length < 8) {
      setError("Password must be at least 8 characters");
      return;
    }

    setError("");

    console.log("Form submitted successfully");
    console.log("Name:", name);
    console.log("Email:", email);
    console.log("Password:", password);
  };

  return (
    <div>
      <h1>User Registration</h1>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Name</label>

          <input
            type="text"
            value={name}
            onChange={(event) => {
              setName(event.target.value);
            }}
          />
        </div>

        <div>
          <label>Email</label>

          <input
            type="email"
            value={email}
            onChange={(event) => {
              setEmail(event.target.value);
            }}
          />
        </div>

        <div>
          <label>Password</label>

          <input
            type="password"
            value={password}
            onChange={(event) => {
              setPassword(event.target.value);
            }}
          />
        </div>

        {error && <p>{error}</p>}

        <button type="submit">
          Register
        </button>

        <button
          type="button"
          onClick={() => {
            console.log("Test button clicked");
          }}
        >
          Test Click
        </button>
      </form>
    </div>
  );
}

export default RegistrationForm;