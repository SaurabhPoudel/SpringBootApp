export type PaymentStatus = "PENDING" | "SUCCEEDED" | "FAILED" | "UNPAID" | "PAID";
export type PaymentType = "FINE" | "PURCHASE";

export type PaymentDTO = {
  id: number;
  userId: number;
  amount: number;
  paymentDate?: string; // ISO
  paymentStatus?: PaymentStatus;
  paymentType?: PaymentType;
};
