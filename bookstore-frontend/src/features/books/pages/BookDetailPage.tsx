import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getBook } from "@features/books/api";
import { authorDisplayName } from "@features/books/types";

export default function BookDetailPage(){
  const { id } = useParams();
  const [book,setBook] = useState<any|null>(null);
  useEffect(()=>{ if(id) getBook(Number(id)).then(setBook).catch(()=>{}); },[id]);
  if(!book) return <div>Loading…</div>;
  const authors = (book.authors||[]).map(authorDisplayName).filter(Boolean).join(", ");
  return (
    <div className="grid md:grid-cols-2 gap-6">
      <img src={"https://via.placeholder.com/600x900?text=Book"} className="w-full rounded-2xl border"/>
      <div className="grid gap-3">
        <h1 className="text-2xl font-bold">{book.title}</h1>
        {authors && <div className="text-slate-600">{authors}</div>}
        {book.publisher?.name && <div className="text-slate-500">{book.publisher.name}</div>}
        <div className="text-sm text-slate-600">ISBN: {book.isbn}</div>
        <div className="text-sm text-slate-600">Publication year: {book.publicationYear}</div>
        {book.description && <p className="text-slate-700">{book.description}</p>}
      </div>
    </div>
  )
}
