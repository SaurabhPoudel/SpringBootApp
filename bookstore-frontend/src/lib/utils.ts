export const money = (n: number, code = "GBP") => new Intl.NumberFormat(undefined, { style: "currency", currency: code }).format(n);
