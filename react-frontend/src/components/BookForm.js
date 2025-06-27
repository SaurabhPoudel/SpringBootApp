import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const BookForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [book, setBook] = useState({
    title: '',
    author: '',
    isbn: '',
    publicationYear: ''
  });

  // Configure axios with Basic Auth
  const axiosInstance = axios.create({
    baseURL: '/api/books',
    headers: {
      Authorization: 'Basic ' + btoa('admin:password'), // Encode username:password
    },
  });

  useEffect(() => {
    if (id) {
      axiosInstance.get(`/${id}`)
        .then(response => setBook(response.data))
        .catch(error => console.error('Error fetching book:', error));
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook({ ...book, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const request = id
      ? axiosInstance.put(`/${id}`, book)
      : axiosInstance.post('/', book);

    request
      .then(() => navigate('/'))
      .catch(error => console.error('Error saving book:', error));
  };

  return (
    <div>
      <h2>{id ? 'Edit Book' : 'Add Book'}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Title:</label>
          <input
            type="text"
            name="title"
            value={book.title}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Author:</label>
          <input
            type="text"
            name="author"
            value={book.author}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>ISBN:</label>
          <input
            type="text"
            name="isbn"
            value={book.isbn}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Publication Year:</label>
          <input
            type="number"
            name="publicationYear"
            value={book.publicationYear}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Save</button>
      </form>
    </div>
  );
};

export default BookForm;