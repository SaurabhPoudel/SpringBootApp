import { api } from "@lib/api-client";
export async function createOrder(payload: { items: { bookId: number; quantity: number }[] }){
  const { data } = await api.post("/api/orders", payload);
  return data; // { orderId }
}
