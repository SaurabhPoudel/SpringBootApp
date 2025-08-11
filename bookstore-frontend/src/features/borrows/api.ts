import { api } from "@lib/api-client";
import type { BorrowRecordDTO } from "./types";

export async function borrowBook(bookId: number): Promise<BorrowRecordDTO>{ const { data } = await api.post(`/api/borrows`, { bookId }); return data; }
export async function returnBook(bookId: number): Promise<BorrowRecordDTO>{ const { data } = await api.post(`/api/borrows/${bookId}/return`); return data; }
export async function myBorrows(): Promise<BorrowRecordDTO[]>{ const { data } = await api.get(`/api/users/me/borrows`); return data || []; }
