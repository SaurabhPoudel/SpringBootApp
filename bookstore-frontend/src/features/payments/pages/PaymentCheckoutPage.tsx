import React from "react";
import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";
import { STRIPE_PUBLISHABLE_KEY } from "@lib/config";
import { createPaymentIntent } from "@features/payments/api";
import CardCheckout from "@features/payments/components/CardCheckout";

const stripePromise = loadStripe(STRIPE_PUBLISHABLE_KEY);

export default function PaymentCheckoutPage(){
  const [amount,setAmount] = React.useState<number>(500);
  const [type,setType] = React.useState<"FINE"|"PURCHASE">("FINE");
  const [clientSecret,setClientSecret] = React.useState<string>("");
  const [loading,setLoading] = React.useState(false);
  const [error,setError] = React.useState<string>("");

  async function onCreateIntent(){
    try{
      setLoading(true); setError(""); setClientSecret("");
      const { clientSecret } = await createPaymentIntent({ amount, type });
      setClientSecret(clientSecret);
    }catch(e:any){ setError(e?.response?.data?.message || "Failed to create payment"); }
    finally{ setLoading(false); }
  }

  return (
    <div className="grid gap-4">
      <div className="card p-4 grid gap-3">
        <h2 className="text-lg font-semibold">Create Payment Intent</h2>
        <label className="text-sm">Amount (in minor units; e.g., 500 = £5.00)</label>
        <input className="input max-w-xs" type="number" min={1} value={amount} onChange={(e)=>setAmount(Number(e.target.value))} />
        <label className="text-sm">Type</label>
        <select className="input max-w-xs" value={type} onChange={(e)=>setType(e.target.value as any)}>
          <option value="FINE">FINE</option>
          <option value="PURCHASE">PURCHASE</option>
        </select>
        <button className="btn btn-primary w-fit" onClick={onCreateIntent} disabled={loading}>Create Intent</button>
        {error && <div className="text-sm text-red-600">{error}</div>}
      </div>

      {clientSecret && (
        <div className="card p-4">
          <h2 className="text-lg font-semibold mb-2">Pay</h2>
          <Elements stripe={stripePromise} options={{ clientSecret }}>
            <CardCheckout clientSecret={clientSecret} />
          </Elements>
        </div>
      )}
    </div>
  );
}
