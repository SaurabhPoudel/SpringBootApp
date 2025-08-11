import React from "react";
import { Link } from "react-router-dom";
import type { Book } from "@features/books/types";
import { authorDisplayName } from "@features/books/types";

export default function BookCard({book}:{book:Book}){
  const authors = (book.authors || []).map(authorDisplayName).filter(Boolean).join(", ");
  const publisher = book.publisher?.name || "";
  return (
    <div className="card overflow-hidden">
      <Link to={`/book/${book.id}`}>
        <img src={"https://via.placeholder.com/400x600?text=Book"} alt={book.title} className="w-full aspect-[3/4] object-cover"/>
      </Link>
      <div className="p-4 grid gap-2">
        <Link to={`/book/${book.id}`} className="font-semibold line-clamp-2">{book.title}</Link>
        {authors && <div className="text-sm text-slate-600">{authors}</div>}
        {publisher && <div className="text-sm text-slate-500">{publisher}</div>}
        <div className="text-xs text-slate-500">ISBN: {book.isbn} • {book.publicationYear}</div>
        <div className="flex gap-2">
          <Link to={`/book/${book.id}`} className="btn btn-primary">View</Link>
        </div>
      </div>
    </div>
  )
}
