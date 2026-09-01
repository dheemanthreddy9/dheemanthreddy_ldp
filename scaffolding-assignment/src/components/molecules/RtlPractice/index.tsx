import React, { useState, useEffect } from 'react';
import { Box, Typography, Button, TextField, List, ListItem, ListItemText, Alert } from '@mui/material';
import { RTL_PRACTICE_CONSTANTS } from '../../../constants/rtlPractice';

export interface RtlPracticeProps {
  initialCount?: number;
  asyncDelay?: number;
}

const RtlPractice: React.FC<RtlPracticeProps> = ({
  initialCount = RTL_PRACTICE_CONSTANTS.DEFAULT_INITIAL_COUNT,
  asyncDelay = RTL_PRACTICE_CONSTANTS.DEFAULT_ASYNC_DELAY,
}) => {
  const [count, setCount] = useState<number>(initialCount);
  const [username, setUsername] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [loginMessage, setLoginMessage] = useState<string | null>(null);
  const [users, setUsers] = useState<string[]>([]);
  const [loadingUsers, setLoadingUsers] = useState<boolean>(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setUsers(RTL_PRACTICE_CONSTANTS.DEFAULT_USERS);
      setLoadingUsers(false);
    }, asyncDelay);

    return () => clearTimeout(timer);
  }, [asyncDelay]);

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    if (!username.trim() || !password.trim()) {
      setLoginMessage(RTL_PRACTICE_CONSTANTS.EMPTY_FIELDS_ERROR);
    } else {
      setLoginMessage(RTL_PRACTICE_CONSTANTS.LOGIN_SUCCESS);
    }
  };

  return (
    <Box className="rtl-practice-container">
      <Typography variant="h4" component="h1" gutterBottom>
        {RTL_PRACTICE_CONSTANTS.MAIN_HEADING}
      </Typography>

      <Box className="rtl-practice-section">
        <Typography variant="h6" data-testid="counter-display">
          {RTL_PRACTICE_CONSTANTS.COUNTER_LABEL}{count}
        </Typography>
        <Box className="rtl-practice-counter-buttons">
          <Button variant="contained" color="primary" onClick={() => setCount((prev) => prev + 1)}>
            {RTL_PRACTICE_CONSTANTS.INCREMENT_BUTTON}
          </Button>
          <Button variant="outlined" color="secondary" onClick={() => setCount((prev) => prev - 1)}>
            {RTL_PRACTICE_CONSTANTS.DECREMENT_BUTTON}
          </Button>
        </Box>
      </Box>

      <Box component="form" onSubmit={handleLogin} className="rtl-practice-section">
        <Typography variant="h6" gutterBottom>
          {RTL_PRACTICE_CONSTANTS.LOGIN_FORM_TITLE}
        </Typography>
        {loginMessage && (
          <Alert
            severity={loginMessage === RTL_PRACTICE_CONSTANTS.LOGIN_SUCCESS ? 'success' : 'error'}
            className="rtl-practice-alert"
          >
            {loginMessage}
          </Alert>
        )}
        <Box className="rtl-practice-field-wrapper">
          <TextField
            fullWidth
            id="username-input"
            label={RTL_PRACTICE_CONSTANTS.USERNAME_LABEL}
            placeholder={RTL_PRACTICE_CONSTANTS.USERNAME_PLACEHOLDER}
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </Box>
        <Box className="rtl-practice-field-wrapper">
          <TextField
            fullWidth
            id="password-input"
            type="password"
            label={RTL_PRACTICE_CONSTANTS.PASSWORD_LABEL}
            placeholder={RTL_PRACTICE_CONSTANTS.PASSWORD_PLACEHOLDER}
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Box>
        <Button type="submit" variant="contained" color="primary">
          {RTL_PRACTICE_CONSTANTS.LOGIN_BUTTON}
        </Button>
      </Box>

      <Box className="rtl-practice-section">
        <Typography variant="h6" gutterBottom>
          {RTL_PRACTICE_CONSTANTS.USER_LIST_TITLE}
        </Typography>
        {loadingUsers ? (
          <Typography variant="body1">{RTL_PRACTICE_CONSTANTS.LOADING_USERS}</Typography>
        ) : (
          <List>
            {users.map((user) => (
              <ListItem key={user}>
                <ListItemText primary={user} />
              </ListItem>
            ))}
          </List>
        )}
      </Box>
    </Box>
  );
};

export default RtlPractice;
