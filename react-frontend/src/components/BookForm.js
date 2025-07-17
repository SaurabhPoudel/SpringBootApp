import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Paper, Typography, Box, IconButton } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';

const BookForm = ({ onBookAdded }) => {
  const [title, setTitle] = useState('');
  const [isbn, setIsbn] = useState('');
  const [publicationYear, setPublicationYear] = useState('');
  const [publisherName, setPublisherName] = useState('');
  const [authors, setAuthors] = useState([{ name: '' }]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const bookData = {
      title,
      isbn,
      publicationYear: parseInt(publicationYear),
      publisher: { name: publisherName },
      authors
    };

    try {
      const token = localStorage.getItem('token');
      await axios.post('http://localhost:8080/api/books/add', bookData, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      onBookAdded();
      setTitle('');
      setIsbn('');
      setPublicationYear('');
      setPublisherName('');
      setAuthors([{ name: '' }]);
    } catch (error) {
      alert('Error adding book');
    }
  };

  const handleAuthorChange = (index, value) => {
    const updatedAuthors = [...authors];
    updatedAuthors[index].name = value;
    setAuthors(updatedAuthors);
  };

  const addAuthor = () => {
    setAuthors([...authors, { name: '' }]);
  };

  return (
    <Paper elevation={2} sx={{ padding: 3, marginBottom: 4 }}>
      <Typography variant="h6">Add Book</Typography>
      <Box component="form" onSubmit={handleSubmit}>
        <TextField fullWidth label="Title" margin="normal" value={title} onChange={e => setTitle(e.target.value)} required />
        <TextField fullWidth label="ISBN" margin="normal" value={isbn} onChange={e => setIsbn(e.target.value)} required />
        <TextField fullWidth label="Publication Year" margin="normal" value={publicationYear} onChange={e => setPublicationYear(e.target.value)} required />
        <TextField fullWidth label="Publisher Name" margin="normal" value={publisherName} onChange={e => setPublisherName(e.target.value)} required />

        <Typography variant="subtitle1" sx={{ mt: 2 }}>Authors</Typography>
        {authors.map((author, index) => (
          <TextField
            key={index}
            fullWidth
            label={`Author ${index + 1}`}
            margin="normal"
            value={author.name}
            onChange={e => handleAuthorChange(index, e.target.value)}
            required
          />
        ))}
        <IconButton onClick={addAuthor} color="primary">
          <AddIcon />
        </IconButton>

        <Button variant="contained" color="secondary" type="submit" sx={{ mt: 2 }}>Save Book</Button>
      </Box>
    </Paper>
  );
};

export default BookForm;
