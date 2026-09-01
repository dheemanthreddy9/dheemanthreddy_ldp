import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { describe, it, expect } from 'vitest';
import RtlPractice from './index';

describe('React Testing Library Practice Test Suite', () => {
  // 1. App renders correctly — checks that the main heading “React Testing Library Practice” is displayed.
  it('App renders correctly — checks main heading', () => {
    render(<RtlPractice />);
    const mainHeading = screen.getByRole('heading', {
      name: /react testing library practice/i,
    });
    expect(mainHeading).toBeInTheDocument();
  });

  // 2. Counter initial value — checks that the counter initially displays Count: 0.
  it('Counter initial value — checks count displays 0 initially', () => {
    render(<RtlPractice />);
    const counterElement = screen.getByText('Count: 0');
    expect(counterElement).toBeInTheDocument();
  });

  // 3. Counter increment — clicks the Increment button and checks that the count changes from 0 to 1.
  it('Counter increment — increments count when Increment button is clicked', async () => {
    const user = userEvent.setup();
    render(<RtlPractice />);
    const incrementBtn = screen.getByRole('button', { name: /increment/i });
    await user.click(incrementBtn);
    expect(screen.getByText('Count: 1')).toBeInTheDocument();
  });

  // 4. Counter decrement — clicks the Decrement button and checks that the count changes from 0 to -1.
  it('Counter decrement — decrements count when Decrement button is clicked', async () => {
    const user = userEvent.setup();
    render(<RtlPractice />);
    const decrementBtn = screen.getByRole('button', { name: /decrement/i });
    await user.click(decrementBtn);
    expect(screen.getByText('Count: -1')).toBeInTheDocument();
  });

  // 5. Login form fields — checks that the Username and Password inputs are present using getByLabelText() and getByPlaceholderText().
  it('Login form fields — checks inputs using getByLabelText() and getByPlaceholderText()', () => {
    render(<RtlPractice />);
    // Using getByLabelText()
    const usernameInputByLabel = screen.getByLabelText(/username/i);
    const passwordInputByLabel = screen.getByLabelText(/password/i);
    expect(usernameInputByLabel).toBeInTheDocument();
    expect(passwordInputByLabel).toBeInTheDocument();

    // Using getByPlaceholderText()
    const usernameInputByPlaceholder = screen.getByPlaceholderText(/enter username/i);
    const passwordInputByPlaceholder = screen.getByPlaceholderText(/enter password/i);
    expect(usernameInputByPlaceholder).toBeInTheDocument();
    expect(passwordInputByPlaceholder).toBeInTheDocument();
  });

  // 6. Empty login validation — clicks Login without entering anything and checks that “Please fill all fields” appears.
  it('Empty login validation — shows error when Login clicked with empty fields', async () => {
    const user = userEvent.setup();
    render(<RtlPractice />);
    const loginBtn = screen.getByRole('button', { name: /^login$/i });
    await user.click(loginBtn);
    expect(screen.getByText('Please fill all fields')).toBeInTheDocument();
  });

  // 7. Successful login — enters username and password, clicks Login, and checks that “Login successful” appears.
  it('Successful login — shows success message when valid credentials entered', async () => {
    const user = userEvent.setup();
    render(<RtlPractice />);
    const usernameInput = screen.getByLabelText(/username/i);
    const passwordInput = screen.getByLabelText(/password/i);
    const loginBtn = screen.getByRole('button', { name: /^login$/i });

    await user.type(usernameInput, 'dheemanth');
    await user.type(passwordInput, 'password123');
    await user.click(loginBtn);

    expect(screen.getByText('Login successful')).toBeInTheDocument();
  });

  // 8. Login success should not initially exist — uses queryByText() to verify that “Login successful” is not present when the application first loads.
  it('Login success should not initially exist — verifies absence on initial load using queryByText()', () => {
    render(<RtlPractice />);
    expect(screen.queryByText('Login successful')).not.toBeInTheDocument();
  });

  // 9. queryByText() absence test — directly checks that queryByText("Login successful") returns null when the element doesn't exist.
  it('queryByText() absence test — directly checks queryByText("Login successful") returns null', () => {
    render(<RtlPractice />);
    const nonExistentElement = screen.queryByText('Login successful');
    expect(nonExistentElement).toBeNull();
  });

  // 10. queryByRole() absence test — searches for a nonexistent Logout button and verifies that queryByRole() returns null.
  it('queryByRole() absence test — verifies searching for nonexistent Logout button returns null', () => {
    render(<RtlPractice />);
    const nonexistentLogoutButton = screen.queryByRole('button', { name: /logout/i });
    expect(nonexistentLogoutButton).toBeNull();
  });

  // 11. Asynchronous UserList test — first checks “Loading users...”, then waits for the users Dheemanth, Rahul, and Priya to appear using findByText().
  it('Asynchronous UserList test — checks loading state then waits for users using findByText()', async () => {
    render(<RtlPractice asyncDelay={100} />);
    
    // First checks "Loading users..."
    expect(screen.getByText('Loading users...')).toBeInTheDocument();

    // Waits for the users Dheemanth, Rahul, and Priya to appear using findByText()
    const dheemanthUser = await screen.findByText('Dheemanth');
    const rahulUser = await screen.findByText('Rahul');
    const priyaUser = await screen.findByText('Priya');

    expect(dheemanthUser).toBeInTheDocument();
    expect(rahulUser).toBeInTheDocument();
    expect(priyaUser).toBeInTheDocument();

    // Loading indicator should no longer be present
    expect(screen.queryByText('Loading users...')).toBeNull();
  });
});
