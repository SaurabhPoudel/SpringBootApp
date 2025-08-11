import React from "react";
import { Link } from "react-router-dom";
export default function NotFoundPage(){
  return (
    <div className="text-center py-24">
      <h1 className="text-3xl font-bold">404</h1>
      <p className="text-slate-600">Page not found</p>
      <Link to="/" className="btn btn-outline mt-6">Go Home</Link>
    </div>
  )
}
