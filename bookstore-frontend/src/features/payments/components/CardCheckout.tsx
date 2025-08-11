import React from "react";
import { CardElement, useElements, useStripe } from "@stripe/react-stripe-js";

export default function CardCheckout({ clientSecret }: { clientSecret: string }){
  const stripe = useStripe();
  const elements = useElements();
  const [msg,setMsg] = React.useState<string>("");
  const [loading,setLoading] = React.useState(false);

  async function onPay(){
    if (!stripe || !elements) return;
    setLoading(true); setMsg("");
    const result = await stripe.confirmCardPayment(clientSecret, { payment_method: { card: elements.getElement(CardElement)! } });
    if (result.error) setMsg(result.error.message || "Payment failed");
    else if (result.paymentIntent?.status === "succeeded") setMsg("Payment succeeded!");
    setLoading(false);
  }

  return (
    <div className="grid gap-3">
      <div className="border rounded-xl p-3"><CardElement /></div>
      <button className="btn btn-primary" onClick={onPay} disabled={loading || !stripe}>Pay</button>
      {msg && <div className="text-sm">{msg}</div>}
    </div>
  )
}
