import { api } from "@lib/api-client";
import type { User } from "./types";

const normalizeRole = (r: string): User["role"] => {
  const v = r?.startsWith("ROLE_") ? r.slice(5) : r;
  if (v === "USER" || v === "ADMIN" || v === "SUPERADMIN") return v;
  return "USER";
};

export async function loginApi(body: { username: string; password: string }): Promise<{ token: string; user: User }>{ 
  const { data } = await api.post("/api/auth/login", body);
  return { token: data.token, user: { username: body.username, role: normalizeRole(data.role) } };
}

export async function registerApi(body: { username: string; password: string }): Promise<{ username: string; role: User["role"]; message: string }>{ 
  const { data } = await api.post("/api/auth/register", body);
  return { ...data, role: normalizeRole(data.role) };
}

export async function meApi(): Promise<User>{
  const { data } = await api.get("/api/auth/profile");
  return { username: data.username, role: normalizeRole(data.role) } as User;
}
