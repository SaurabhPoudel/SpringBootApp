import { api } from "@lib/api-client";
import type { PaymentDTO } from "./types";

export async function makePayment(payload: PaymentDTO): Promise<PaymentDTO>{ const { data } = await api.post(`/api/payments`, payload); return data; }
export async function getPaymentsMe(): Promise<PaymentDTO[]>{ const { data } = await api.get(`/api/payments/me`); return data || []; }
export async function getPaymentById(paymentId: number): Promise<PaymentDTO>{ const { data } = await api.get(`/api/payments/${paymentId}`); return data; }
export async function createPaymentIntent(payload: { amount: number; type: "FINE" | "PURCHASE" }){ const { data } = await api.post(`/api/payments/intent`, payload); return data as { clientSecret: string }; }
