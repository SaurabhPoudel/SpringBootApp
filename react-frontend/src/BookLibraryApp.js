import React, { useState, useEffect } from 'react';
import axios from 'axios';
import BookForm from './components/BookForm';
import BookList from './components/BookList';
import Login from './components/Login';
import { Container, Typography, AppBar, Toolbar, Button } from '@mui/material';

const BookLibraryApp = () => {
  const [books, setBooks] = useState([]);
  const [token, setToken] = useState(localStorage.getItem('token'));

  const fetchBooks = async () => {

    try {
    const token = localStorage.getItem('token');
        if (!token) {
          console.error('No token found, cannot fetch books');
          return;
        }
      const response = await axios.get('http://localhost:8080/api/books', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      console.log("Fetched books:", response.data); // 👀 Inspect this
        if (!Array.isArray(response.data)) {
            throw new Error('Expected an array of books');
        }
        console.log("Books data:", response.data); // 👀 Inspect this
      setBooks(response.data);
    } catch (error) {
      console.error('Failed to fetch books', error);
    }
  };

  useEffect(() => {
    if (token) fetchBooks();
  }, [token]);

  const handleLogout = () => {
    localStorage.removeItem('token');
    setToken(null);
  };

  if (!token) {
    return <Login onLogin={setToken} />;
  }

  return (
    <Container maxWidth="md">
      <AppBar position="static" color="primary">
        <Toolbar>
          <Typography variant="h6" sx={{ flexGrow: 1 }}>📚 Book Library</Typography>
          <Button color="inherit" onClick={handleLogout}>Logout</Button>
        </Toolbar>
      </AppBar>

      <Typography variant="h4" align="center" sx={{ mt: 4, mb: 2 }}>
        Manage Your Book Collection
      </Typography>

      <BookForm onBookAdded={fetchBooks} />
      <BookList books={books} />
    </Container>
  );
};

export default BookLibraryApp;
