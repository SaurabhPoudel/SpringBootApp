import React from "react";
import { Link, NavLink } from "react-router-dom";
import { ShoppingCart, BookOpen, User, BookMarked, CreditCard } from "lucide-react";
import { useCartStore } from "@features/cart/store";
import { useAuthStore } from "@features/auth/store";

export default function Header(){
  const count = useCartStore((s) => s.totalItems);
  const user = useAuthStore((s) => s.user);
  const logout = useAuthStore((s) => s.logout);
  return (
    <header className="sticky top-0 z-40 bg-white border-b">
      <div className="container flex items-center justify-between py-3 gap-4">
        <Link to="/" className="flex items-center gap-2 font-semibold">
          <BookOpen /> <span>Bookstore</span>
        </Link>
        <nav className="flex items-center gap-4">
          <NavLink to="/browse" className={({isActive})=> isActive?"font-semibold":""}>Browse</NavLink>
          <NavLink to="/borrows" className={({isActive})=> isActive?"font-semibold":""}><BookMarked className="inline w-4 h-4 mr-1"/>My Borrows</NavLink>
          <NavLink to="/payments" className={({isActive})=> isActive?"font-semibold":""}><CreditCard className="inline w-4 h-4 mr-1"/>Payments</NavLink>
          <NavLink to="/admin" className={({isActive})=> isActive?"font-semibold":""}>Admin</NavLink>
          <NavLink to="/superadmin" className={({isActive})=> isActive?"font-semibold":""}>Superadmin</NavLink>
        </nav>
        <div className="flex items-center gap-3">
          {user ? (
            <>
              <span className="btn btn-outline"><User className="w-4 h-4"/> {user.username}</span>
              <button onClick={logout} className="btn btn-outline">Logout</button>
            </>
          ) : (
            <Link to="/login" className="btn btn-outline">Sign in</Link>
          )}
          <Link to="/cart" className="btn btn-primary"><ShoppingCart className="w-4 h-4"/> Cart ({count})</Link>
        </div>
      </div>
    </header>
  );
}
