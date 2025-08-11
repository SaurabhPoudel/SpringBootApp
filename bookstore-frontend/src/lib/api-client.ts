import axios from "axios";
import { API_BASE } from "@lib/config";
import { useAuthStore } from "@features/auth/store";

export const api = axios.create({ baseURL: API_BASE });

api.interceptors.request.use((config) => {
  const token = useAuthStore.getState().token;
  if (token) config.headers["Authorization"] = `Bearer ${token}`;
  return config;
});

api.interceptors.response.use(
  (r) => r,
  (err) => {
    const status = err?.response?.status;
    if (status === 401) useAuthStore.getState().logout();
    return Promise.reject(err);
  }
);
