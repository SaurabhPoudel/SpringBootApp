import React from "react";
import { Link } from "react-router-dom";
import { useCartStore } from "@features/cart/store";

export default function CartPage(){
  const { items, remove, setQuantity } = useCartStore();
  if(items.length===0) return (
    <div className="text-center py-16">
      <p className="text-lg">Your cart is empty.</p>
      <Link to="/browse" className="btn btn-primary mt-4">Browse books</Link>
    </div>
  );
  return (
    <div className="grid md:grid-cols-[1fr,320px] gap-6">
      <div className="card p-4">
        {items.map(({book,quantity})=> (
          <div key={book.id} className="flex items-center gap-4 py-3 border-b last:border-0">
            <div className="w-16 h-24 bg-slate-100 rounded" />
            <div className="flex-1">
              <div className="font-semibold">{book.title}</div>
              <div className="text-sm text-slate-600">ISBN: {book.isbn}</div>
            </div>
            <input type="number" min={1} value={quantity} onChange={(e)=>setQuantity(book.id, Math.max(1, Number(e.target.value)))} className="input w-20"/>
            <button className="btn btn-outline" onClick={()=>remove(book.id)}>Remove</button>
          </div>
        ))}
      </div>
      <div className="card p-4 h-fit">
        <Link to="/checkout" className="btn btn-primary w-full mt-2">Proceed to Checkout</Link>
      </div>
    </div>
  )
}
