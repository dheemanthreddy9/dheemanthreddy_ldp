import { useAuth0 } from '@auth0/auth0-react';

const App = () => {
  const { loginWithRedirect, logout, user, isAuthenticated, isLoading } = useAuth0();

  const handleLogin = () => {
    loginWithRedirect();
  };

  const handleLogout = () => {
    logout({ logoutParams: { returnTo: window.location.origin } });
  };

  if (isLoading) {
    return <div className="container">Loading...</div>;
  }

  return (
    <div className="container">
      <h1>Auth0 Practice App</h1>

      {!isAuthenticated ? (
        <div className="card">
          <p>Welcome! Click below to log in with Auth0.</p>
          <button className="btn login-btn" onClick={handleLogin}>
            Log In
          </button>
        </div>
      ) : (
        <div className="card">
          {user?.picture && <img src={user.picture} alt={user.name} className="avatar" />}
          <h2>Welcome, {user?.name}!</h2>
          <p><strong>Email:</strong> {user?.email}</p>

          <button className="btn logout-btn" onClick={handleLogout}>
            Log Out
          </button>
        </div>
      )}
    </div>
  );
};

export default App;
