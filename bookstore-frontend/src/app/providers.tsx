import React from "react";
import { useAuthStore } from "@features/auth/store";
import { meApi } from "@features/auth/api";

export const AppProviders: React.FC<React.PropsWithChildren> = ({ children }) => {
  const hydrate = useAuthStore((s) => s.hydrate);
  const token = useAuthStore((s) => s.token);
  const login = useAuthStore((s) => s.login);

  React.useEffect(() => { hydrate(); }, [hydrate]);
  React.useEffect(() => {
    const run = async () => {
      if (!token) return;
      try { const user = await meApi(); login({ token, user }); } catch {}
    };
    run();
  }, [token, login]);

  return <>{children}</>;
};
