import { create } from "zustand";
import type { Book } from "@features/books/types";

type Item = { book: Book; quantity: number };

type CartState = {
  items: Item[];
  add: (book: Book, quantity: number) => void;
  remove: (id: number) => void;
  setQuantity: (id: number, q: number) => void;
  clear: () => void;
  totalItems: number;
};

export const useCartStore = create<CartState>((set, get) => ({
  items: [],
  add: (book, quantity) => {
    const exists = get().items.find(i=>i.book.id===book.id);
    if (exists) set({ items: get().items.map(i=> i.book.id===book.id ? ({...i, quantity: i.quantity+quantity}) : i) });
    else set({ items: [...get().items, { book, quantity }] });
  },
  remove: (id) => set({ items: get().items.filter(i=>i.book.id!==id) }),
  setQuantity: (id, q) => set({ items: get().items.map(i=> i.book.id===id ? ({...i, quantity: q}) : i) }),
  clear: () => set({ items: [] }),
  get totalItems(){ return get().items.reduce((s,i)=>s+i.quantity,0); },
}));
