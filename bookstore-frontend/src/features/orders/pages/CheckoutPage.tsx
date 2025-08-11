import React from "react";
import { useCartStore } from "@features/cart/store";
import { createOrder } from "@features/orders/api";

export default function CheckoutPage(){
  const { items, clear } = useCartStore();

  async function onCheckout(){
    try{
      const payload = { items: items.map(i=>({ bookId: i.book.id, quantity: i.quantity })) };
      const res = await createOrder(payload);
      alert(`Order placed. ID: ${res.orderId || "?"}`);
      clear();
    }catch(e:any){ alert(e?.response?.data?.message || "Checkout failed"); }
  }

  return (
    <div className="grid md:grid-cols-[1fr,320px] gap-6">
      <div className="card p-4">
        <h2 className="text-lg font-semibold">Order Summary</h2>
        {items.map(({book,quantity})=> (
          <div key={book.id} className="flex justify-between py-2 border-b last:border-0">
            <div>{book.title} × {quantity}</div>
          </div>
        ))}
      </div>
      <div className="card p-4 h-fit">
        <button className="btn btn-primary w-full mt-2" onClick={onCheckout}>Place Order</button>
      </div>
    </div>
  )
}
