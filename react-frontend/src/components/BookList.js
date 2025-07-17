import React from 'react';
import { Card, CardContent, Typography, Grid } from '@mui/material';

const BookList = ({ books }) => {
  return (
    <div>
      <Typography variant="h6" gutterBottom>Book List</Typography>
      <Grid container spacing={2}>
        {books.map(book => (
          <Grid item xs={12} sm={6} key={book.id}>
            <Card>
              <CardContent>
                <Typography variant="h6">{book.title}</Typography>
                <Typography><strong>ISBN:</strong> {book.isbn}</Typography>
                <Typography><strong>Publisher:</strong> {book.publisher?.name}</Typography>
                <Typography><strong>Year:</strong> {book.publicationYear}</Typography>
                <Typography><strong>Authors:</strong> {book.authors.map(a => a.name).join(', ')}</Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </div>
  );
};

export default BookList;
