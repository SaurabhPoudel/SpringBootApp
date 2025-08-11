import React from "react";
import { Link } from "react-router-dom";
export default function HomePage(){
  return (
    <div className="grid gap-6">
      <section className="card p-8">
        <h1 className="text-2xl font-bold">Discover your next favorite book</h1>
        <p className="text-slate-600 mt-2">Browse, borrow, and pay fines securely.</p>
        <div className="mt-4 flex gap-3">
          <Link to="/browse" className="btn btn-primary">Start Browsing</Link>
          <Link to="/payments" className="btn btn-outline">View Payments</Link>
        </div>
      </section>
    </div>
  )
}
