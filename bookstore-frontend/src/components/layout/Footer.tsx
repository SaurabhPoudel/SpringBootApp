import React from "react";
export default function Footer(){
  return (
    <footer className="border-t mt-12">
      <div className="container py-8 text-sm text-slate-600">
        © {new Date().getFullYear()} Bookstore. All rights reserved.
      </div>
    </footer>
  );
}
