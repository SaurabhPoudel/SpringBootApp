import React from "react";
import { Navigate, useLocation } from "react-router-dom";
import { useAuthStore } from "./store";
import type { Role } from "@lib/config";

export const RequireAuth: React.FC<React.PropsWithChildren> = ({ children }) => {
  const user = useAuthStore((s) => s.user);
  const loc = useLocation();
  if (!user) return <Navigate to="/login" state={{ from: loc }} replace />;
  return <>{children}</>;
};

export const RequireRole: React.FC<React.PropsWithChildren<{role:Role}>> = ({ children, role }) => {
  const user = useAuthStore((s) => s.user);
  const loc = useLocation();
  if (!user) return <Navigate to="/login" state={{ from: loc }} replace />;
  if (user.role !== role) return <Navigate to="/" replace />;
  return <>{children}</>;
};
