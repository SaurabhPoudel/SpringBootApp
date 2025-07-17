// src/components/Login.js
import React, { useState } from 'react';
import axios from 'axios';
import {
  TextField,
  Button,
  Container,
  Typography,
  Paper,
  Box,
  Tabs,
  Tab
} from '@mui/material';

const Login = ({ onLogin }) => {
  const [tab, setTab] = useState(0);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleTabChange = (event, newValue) => {
    setTab(newValue);
    setUsername('');
    setPassword('');
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/auth/login', { username, password });
      localStorage.setItem('token', response.data);
      onLogin(response.data);
    } catch (err) {
      alert('Login failed');
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/auth/register', { username, password });
      alert('User registered successfully. You can now login.');
      setTab(0);
    } catch (err) {
      alert('Registration failed');
    }
  };

  return (
    <Container maxWidth="sm">
      <Paper elevation={3} sx={{ padding: 4, marginTop: 10 }}>
        <Tabs value={tab} onChange={handleTabChange} centered>
          <Tab label="Login" />
          <Tab label="Sign Up" />
        </Tabs>

        {tab === 0 && (
          <Box component="form" onSubmit={handleLogin} sx={{ mt: 3 }}>
            <Typography variant="h5" gutterBottom>🔐 Login</Typography>
            <TextField fullWidth label="Username" margin="normal" value={username} onChange={e => setUsername(e.target.value)} required />
            <TextField fullWidth label="Password" type="password" margin="normal" value={password} onChange={e => setPassword(e.target.value)} required />
            <Button variant="contained" color="primary" type="submit" fullWidth sx={{ mt: 2 }}>
              Login
            </Button>
          </Box>
        )}

        {tab === 1 && (
          <Box component="form" onSubmit={handleRegister} sx={{ mt: 3 }}>
            <Typography variant="h5" gutterBottom>📝 Sign Up</Typography>
            <TextField fullWidth label="Username" margin="normal" value={username} onChange={e => setUsername(e.target.value)} required />
            <TextField fullWidth label="Password" type="password" margin="normal" value={password} onChange={e => setPassword(e.target.value)} required />
            <Button variant="contained" color="secondary" type="submit" fullWidth sx={{ mt: 2 }}>
              Sign Up
            </Button>
          </Box>
        )}
      </Paper>
    </Container>
  );
};

export default Login;
