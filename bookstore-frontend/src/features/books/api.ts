import { api } from "@lib/api-client";
import type { Book, PublisherDTO } from "./types";

export async function getAllBooks(): Promise<Book[]>{ const res = await api.get("/api/books"); return res.data || []; }
export async function getBook(id: number): Promise<Book>{ const { data } = await api.get(`/api/books/${id}`); return data; }
export async function addBook(book: Partial<Book>): Promise<Book>{ const { data } = await api.post(`/api/books/add`, book); return data; }
export async function updateBook(id: number, book: Partial<Book>): Promise<Book>{ const { data } = await api.put(`/api/books/${id}`, book); return data; }
export async function deleteBook(id: number): Promise<void>{ await api.delete(`/api/books/${id}`); }
export async function searchByPublisher(publisherName: string): Promise<Book | null>{ try { const { data } = await api.get(`/api/books/search-by-publisher`, { params: { publisherName } }); return data; } catch { return null; } }
export async function getPublishers(): Promise<PublisherDTO[]>{ const { data } = await api.get(`/api/publishers`); return data; }
