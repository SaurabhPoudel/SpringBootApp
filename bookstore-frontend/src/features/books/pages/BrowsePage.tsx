import React, { useEffect, useMemo, useState } from "react";
import { getAllBooks, getPublishers } from "@features/books/api";
import type { Book, PublisherDTO } from "@features/books/types";
import { authorDisplayName } from "@features/books/types";
import BookCard from "@features/books/components/BookCard";
import EmptyState from "@components/common/EmptyState";

export default function BrowsePage(){
  const [allBooks,setAllBooks] = useState<Book[]>([]);
  const [items,setItems] = useState<Book[]>([]);
  const [publishers,setPublishers] = useState<PublisherDTO[]>([]);
  const [publisher, setPublisher] = useState<string>("");
  const [author, setAuthor] = useState<string>("");
  const [loading,setLoading] = useState(true);

  useEffect(()=>{ getPublishers().then(setPublishers).catch(()=>{}); },[]);
  useEffect(()=>{ (async()=>{ try{ const all = await getAllBooks(); setAllBooks(all); setItems(all); } finally { setLoading(false); } })(); },[]);

  const authorOptions = useMemo(()=>{
    const names = new Set<string>();
    allBooks.forEach(b => (b.authors||[]).forEach(a => { const n = authorDisplayName(a); if (n) names.add(n); }));
    return Array.from(names).sort();
  },[allBooks]);

  function applyFilters(){
    let base = [...allBooks];
    if (publisher) base = base.filter(b => (b.publisher?.name||"") === publisher);
    if (author) base = base.filter(b => (b.authors||[]).some(a => authorDisplayName(a) === author));
    setItems(base);
  }

  if (loading) return <div>Loading…</div>;

  return (
    <div className="grid gap-4">
      <div className="card p-4 flex flex-wrap gap-2 items-center">
        <label className="text-sm">Publisher</label>
        <select className="input max-w-xs" value={publisher} onChange={(e)=>setPublisher(e.target.value)}>
          <option value="">All</option>
          {publishers.map(p=> <option key={p.id} value={p.name}>{p.name}</option>)}
        </select>
        <label className="text-sm ml-2">Author</label>
        <select className="input max-w-xs" value={author} onChange={(e)=>setAuthor(e.target.value)}>
          <option value="">All</option>
          {authorOptions.map(n=> <option key={n} value={n}>{n}</option>)}
        </select>
        <button className="btn btn-outline" onClick={applyFilters}>Apply</button>
      </div>

      {items.length === 0 ? (
        <EmptyState title="No books found" desc="Try different filters" />
      ) : (
        <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
          {items.map(b=> <BookCard key={b.id} book={b} />)}
        </div>
      )}
    </div>
  )
}
