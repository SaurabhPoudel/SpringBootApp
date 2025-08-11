import React from "react";
import { createBrowserRouter } from "react-router-dom";
import Shell from "@components/layout/Shell";
import HomePage from "@pages/HomePage";
import BrowsePage from "@features/books/pages/BrowsePage";
import BookDetailPage from "@features/books/pages/BookDetailPage";
import CartPage from "@features/cart/pages/CartPage";
import CheckoutPage from "@features/orders/pages/CheckoutPage";
import LoginPage from "@features/auth/pages/LoginPage";
import RegisterPage from "@features/auth/pages/RegisterPage";
import DashboardPage from "@features/account/pages/DashboardPage";
import PaymentsPage from "@features/payments/pages/PaymentsPage";
import PaymentCheckoutPage from "@features/payments/pages/PaymentCheckoutPage";
import MyBorrowsPage from "@features/borrows/pages/MyBorrowsPage";
import AdminDashboard from "@features/admin/pages/AdminDashboard";
import SuperadminDashboard from "@features/superadmin/pages/SuperadminDashboard";
import NotFoundPage from "@pages/NotFoundPage";
import { RequireAuth, RequireRole } from "@features/auth/routeGuards";

export const router = createBrowserRouter([
  {
    element: <Shell />,
    children: [
      { path: "/", element: <HomePage /> },
      { path: "/browse", element: <RequireAuth><BrowsePage /></RequireAuth> },
      { path: "/book/:id", element: <RequireAuth><BookDetailPage /></RequireAuth> },
      { path: "/cart", element: <RequireAuth><CartPage /></RequireAuth> },
      { path: "/checkout", element: <RequireAuth><CheckoutPage /></RequireAuth> },
      { path: "/borrows", element: <RequireAuth><MyBorrowsPage /></RequireAuth> },
      { path: "/payments", element: <RequireAuth><PaymentsPage /></RequireAuth> },
      { path: "/payments/checkout", element: <RequireAuth><PaymentCheckoutPage /></RequireAuth> },
      { path: "/login", element: <LoginPage /> },
      { path: "/register", element: <RegisterPage /> },
      { path: "/dashboard", element: <RequireAuth><DashboardPage /></RequireAuth> },
      { path: "/admin", element: <RequireRole role="ADMIN"><AdminDashboard /></RequireRole> },
      { path: "/superadmin", element: <RequireRole role="SUPERADMIN"><SuperadminDashboard /></RequireRole> },
      { path: "*", element: <NotFoundPage /> },
    ],
  },
]);
