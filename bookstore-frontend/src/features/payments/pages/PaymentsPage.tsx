import React from "react";
import { getPaymentsMe } from "@features/payments/api";
import type { PaymentDTO } from "@features/payments/types";

export default function PaymentsPage(){
  const [rows,setRows] = React.useState<PaymentDTO[]>([]);
  const [loading,setLoading] = React.useState(false);

  async function load(){
    setLoading(true);
    try{ setRows(await getPaymentsMe()); }
    finally{ setLoading(false); }
  }
  React.useEffect(()=>{ load(); },[]);

  return (
    <div className="grid gap-4">
      <div className="card p-4 flex items-center justify-between">
        <div className="text-lg font-semibold">My Payments</div>
        <button className="btn btn-outline" onClick={load} disabled={loading}>Refresh</button>
      </div>
      <div className="card overflow-x-auto">
        <table className="w-full text-sm">
          <thead className="bg-slate-50"><tr>
            <th className="p-2 text-left">ID</th>
            <th className="p-2 text-left">Amount</th>
            <th className="p-2 text-left">Type</th>
            <th className="p-2 text-left">Status</th>
            <th className="p-2 text-left">Date</th>
          </tr></thead>
          <tbody>
            {rows.map(r=> (
              <tr key={r.id} className="border-t">
                <td className="p-2">{r.id}</td>
                <td className="p-2">£{Number(r.amount||0).toFixed(2)}</td>
                <td className="p-2">{r.paymentType||"-"}</td>
                <td className="p-2">{r.paymentStatus||"-"}</td>
                <td className="p-2">{r.paymentDate ? new Date(r.paymentDate).toLocaleString() : "-"}</td>
              </tr>
            ))}
          </tbody>
        </table>
        {rows.length===0 && <div className="p-4 text-sm text-slate-600">No payments</div>}
      </div>
      <div className="card p-4">
        <div className="text-sm text-slate-600">To pay a fine or purchase, go to <b>Payment Checkout</b>.</div>
        <a href="/payments/checkout" className="btn btn-primary mt-3 inline-block">Payment Checkout</a>
      </div>
    </div>
  )
}
