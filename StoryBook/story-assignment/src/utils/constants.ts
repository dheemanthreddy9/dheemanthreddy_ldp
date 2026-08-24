// Contract Table & Cash Acceleration Constants
export const CASH_ACCELERATION_TITLE = "Your Funding";
export const TAB_MY_CONTRACTS = "My Contracts";
export const TAB_MY_CASH_KICKS = "My Cash Kicks";

export const CONTRACT_TABLE_HEADERS = [
  "Name",
  "Type",
  "Per Payment",
  "Term Length",
  "Payment Remaining",
] as const;

export interface Contract {
  id: string;
  name: string;
  type: string;
  perPayment: string;
  termLength: string;
  paymentRemaining: string;
}

export const MOCK_CONTRACTS: Contract[] = [
  {
    id: "1",
    name: "Contract 1",
    type: "Monthly",
    perPayment: "$12,000",
    termLength: "12 months",
    paymentRemaining: "$126,000",
  },
  {
    id: "2",
    name: "Contract 2",
    type: "Monthly",
    perPayment: "$6,000",
    termLength: "9 months",
    paymentRemaining: "$63,000",
  },
];

// Header & Navigation Constants
export const APP_NAME = "Seeder";
export const HEADER_TITLE = "Cash acceleration";
export const HEADER_SUBTITLE = "Place to create new cash kicks to run your business";
export const USER_NAME = "Jessie Wilson";

export const NAV_ITEMS = {
  HOME: "Home",
  CASH_ACCELERATION: "Cash Acceleration",
  WATCH_HOW_TO: "Watch How To",
} as const;

// Action Card Constants
export const ACTION_CARD_CONSTANTS = {
  TITLE: "Launch a new Cash Kick",
  DESCRIPTION: "You have upto $709,546 available for a new cash advance.",
  BUTTON_TEXT: "New Cash Kick",
} as const;

// Summary Card Constants
export const SUMMARY_CARD_CONSTANTS = {
  TERM_CAP: {
    TITLE: "Term cap",
    VALUE: "12 Months",
  },
  AVAILABLE_CREDIT: {
    TITLE: "Available Credit",
    VALUE: "$709.45k",
  },
  MAX_INTEREST_RATE: {
    TITLE: "Max Interest Rate",
    VALUE: "12.00%",
  },
} as const;
