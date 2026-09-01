export interface Payment {
  id: number;
  dueDate: string;
  daysFromNow: string;
  status: string;
  expectedAmount: string;
  outstanding: string;
}

export interface Contract {
  id: number;
  name: string;
  type: string;
  perPayment: string;
  termLength: string;
  termFee: string;
  paymentAmount: string;
  secondaryPaymentAmount: string | null;
}

export interface CashKick {
  id: number;
  name: string;
  status: string;
  maturity: string;
  totalReceived: string;
  totalReceivedFee: string;
  totalFinanced: string;
}
