import { create } from "zustand";
import type { User } from "./types";

const TOKEN_KEY = "auth_token";
const USER_KEY = "auth_user";

type AuthState = {
  user: User | null;
  token: string | null;
  login: (payload: { user: User; token: string }) => void;
  logout: () => void;
  hydrate: () => void;
};

export const useAuthStore = create<AuthState>((set) => ({
  user: null,
  token: null,
  login: ({ user, token }) => {
    localStorage.setItem(TOKEN_KEY, token);
    localStorage.setItem(USER_KEY, JSON.stringify(user));
    set({ user, token });
  },
  logout: () => {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(USER_KEY);
    set({ user: null, token: null });
  },
  hydrate: () => {
    const token = localStorage.getItem(TOKEN_KEY);
    const raw = localStorage.getItem(USER_KEY);
    set({ token: token || null, user: raw ? JSON.parse(raw) : null });
  },
}));
