import React from "react";
import { myBorrows, returnBook } from "@features/borrows/api";

export default function MyBorrowsPage(){
  const [rows,setRows] = React.useState<any[]>([]);
  const [loading,setLoading] = React.useState(false);

  async function load(){
    setLoading(true);
    try{ setRows(await myBorrows()); } finally { setLoading(false); }
  }
  React.useEffect(()=>{ load(); },[]);

  async function onReturn(bookId: number){
    await returnBook(bookId);
    await load();
  }

  return (
    <div className="grid gap-4">
      <div className="card p-4 flex items-center justify-between">
        <div className="text-lg font-semibold">My Borrows</div>
        <button className="btn btn-outline" onClick={load} disabled={loading}>Refresh</button>
      </div>
      <div className="card overflow-x-auto">
        <table className="w-full text-sm">
          <thead className="bg-slate-50"><tr>
            <th className="p-2 text-left">Book</th>
            <th className="p-2 text-left">Borrowed</th>
            <th className="p-2 text-left">Returned</th>
            <th className="p-2 text-left">Return Date</th>
            <th className="p-2 text-left">Action</th>
          </tr></thead>
          <tbody>
            {rows.map(r=> (
              <tr key={r.id} className="border-t">
                <td className="p-2">{r.bookTitle || r.bookId}</td>
                <td className="p-2">{new Date(r.borrowDate).toLocaleString()}</td>
                <td className="p-2">{r.returned ? "Yes" : "No"}</td>
                <td className="p-2">{r.returnDate ? new Date(r.returnDate).toLocaleString() : "-"}</td>
                <td className="p-2">
                  {!r.returned ? (
                    <button className="btn btn-primary" onClick={()=>onReturn(r.bookId)}>Return</button>
                  ) : (
                    <span className="text-slate-500">—</span>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {rows.length===0 && <div className="p-4 text-sm text-slate-600">No borrow records</div>}
      </div>
      <div className="text-sm text-slate-600">Note: If a fine is created on late return, you'll see it on the Payments page.</div>
    </div>
  )
}
