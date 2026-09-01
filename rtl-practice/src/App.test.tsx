import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import App from "./App";

describe("App", () => {
  test("renders the application title", () => {
    render(<App />);

    expect(
      screen.getByRole("heading", {
        name: "React Testing Library Practice",
      })
    ).toBeInTheDocument();
  });

  test("displays the initial counter value", () => {
    render(<App />);

    expect(screen.getByText("Count: 0")).toBeInTheDocument();
  });

  test("increments the counter when button is clicked", async () => {
    const user = userEvent.setup();

    render(<App />);

    const incrementButton = screen.getByRole("button", {
      name: "Increment",
    });

    await user.click(incrementButton);

    expect(screen.getByText("Count: 1")).toBeInTheDocument();
  });

  test("decrements the counter when button is clicked", async () => {
    const user = userEvent.setup();

    render(<App />);

    const decrementButton = screen.getByRole("button", {
      name: "Decrement",
    });

    await user.click(decrementButton);

    expect(screen.getByText("Count: -1")).toBeInTheDocument();
  });

  test("renders login form fields", () => {
    render(<App />);

    expect(
      screen.getByLabelText("Username")
    ).toBeInTheDocument();

    expect(
      screen.getByLabelText("Password")
    ).toBeInTheDocument();

    expect(
      screen.getByPlaceholderText("Enter username")
    ).toBeInTheDocument();

    expect(
      screen.getByPlaceholderText("Enter password")
    ).toBeInTheDocument();
  });

  test("shows validation message when login form is empty", async () => {
    const user = userEvent.setup();

    render(<App />);

    await user.click(
      screen.getByRole("button", {
        name: "Login",
      })
    );

    expect(
      screen.getByText("Please fill all fields")
    ).toBeInTheDocument();
  });

  test("logs in successfully with valid credentials", async () => {
    const user = userEvent.setup();

    render(<App />);

    const usernameInput = screen.getByLabelText("Username");
    const passwordInput = screen.getByLabelText("Password");

    await user.type(usernameInput, "Dheemanth");
    await user.type(passwordInput, "123456");

    await user.click(
      screen.getByRole("button", {
        name: "Login",
      })
    );

    expect(
      screen.getByRole("status")
    ).toHaveTextContent("Login successful");
  });

  test("does not show login success initially", () => {
    render(<App />);

    expect(
      screen.queryByText("Login successful")
    ).not.toBeInTheDocument();
  });

  test("queryByText returns null when element does not exist", () => {
    render(<App />);

    const successMessage = screen.queryByText("Login successful");

    expect(successMessage).toBeNull();
  });

  test("queryByRole returns null when button does not exist", () => {
    render(<App />);

    const logoutButton = screen.queryByRole("button", {
      name: "Logout",
    });

    expect(logoutButton).toBeNull();
  });

  test("loads users asynchronously", async () => {
    render(<App />);

    expect(
      screen.getByText("Loading users...")
    ).toBeInTheDocument();

    const user = await screen.findByText("Dheemanth");

    expect(user).toBeInTheDocument();

    expect(
      screen.getByText("Rahul")
    ).toBeInTheDocument();

    expect(
      screen.getByText("Priya")
    ).toBeInTheDocument();
  });
});