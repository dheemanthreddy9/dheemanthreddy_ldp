export const COLORS = {
  WHITE: "#FFFFFF",
  PRIMARY: "#6C5CE7",
  SUMMARY_ICON: "#B794F4",
  MUTED: "#8D8D99",
};

export const TEXTS = {
  // Navigation & Brand
  LOGO: "Seeder",
  HOME: "Home",
  CASH_ACCELERATION: "Cash Acceleration",
  WATCH_HOW_TO: "Watch How To",

  // Header & User Profile
  HEADER_TITLE: "Cash acceleration",
  HEADER_SUBTITLE: "Place to create new cash kicks to run your business",
  USER_NAME: "Jessie Wilson",

  // Summary Cards
  TERM_CAP_TITLE: "Term cap",
  TERM_CAP_VALUE: "12 Months",
  AVAILABLE_CREDIT_TITLE: "Available Credit",
  AVAILABLE_CREDIT_VALUE: "$709.45k",
  MAX_INTEREST_RATE_TITLE: "Max Interest Rate",
  MAX_INTEREST_RATE_VALUE: "12.00%",

  // Action Card
  ACTION_CARD_TITLE: "Launch a new Cash Kick",
  ACTION_CARD_DESCRIPTION: "You have upto $709,546 available for a new cash advance.",
  ACTION_CARD_BUTTON: "New Cash Kick",

  // Contract Table Header & Tabs
  YOUR_FUNDING: "Your Funding",
  MY_CONTRACTS: "My Contracts",
  MY_CASH_KICKS: "My Cash Kicks",

  // Table Headers
  TABLE_HEADER_NAME: "Name",
  TABLE_HEADER_TYPE: "Type",
  TABLE_HEADER_PER_PAYMENT: "Per Payment",
  TABLE_HEADER_TERM_LENGTH: "Term Length",
  TABLE_HEADER_PAYMENT_REMAINING: "Payment Remaining",

  // Table Mock Data
  CONTRACT_1_NAME: "Contract 1",
  CONTRACT_1_TYPE: "Monthly",
  CONTRACT_1_PER_PAYMENT: "$12,000",
  CONTRACT_1_TERM_LENGTH: "12 months",
  CONTRACT_1_PAYMENT_REMAINING: "$126,000",

  CONTRACT_2_NAME: "Contract 2",
  CONTRACT_2_TYPE: "Monthly",
  CONTRACT_2_PER_PAYMENT: "$6,000",
  CONTRACT_2_TERM_LENGTH: "9 months",
  CONTRACT_2_PAYMENT_REMAINING: "$63,000",
};

export const CONTRACT_TABLE_HEADERS = [
  TEXTS.TABLE_HEADER_NAME,
  TEXTS.TABLE_HEADER_TYPE,
  TEXTS.TABLE_HEADER_PER_PAYMENT,
  TEXTS.TABLE_HEADER_TERM_LENGTH,
  TEXTS.TABLE_HEADER_PAYMENT_REMAINING,
];

export const MOCK_CONTRACTS = [
  {
    id: 1,
    name: TEXTS.CONTRACT_1_NAME,
    type: TEXTS.CONTRACT_1_TYPE,
    perPayment: TEXTS.CONTRACT_1_PER_PAYMENT,
    termLength: TEXTS.CONTRACT_1_TERM_LENGTH,
    paymentRemaining: TEXTS.CONTRACT_1_PAYMENT_REMAINING,
  },
  {
    id: 2,
    name: TEXTS.CONTRACT_2_NAME,
    type: TEXTS.CONTRACT_2_TYPE,
    perPayment: TEXTS.CONTRACT_2_PER_PAYMENT,
    termLength: TEXTS.CONTRACT_2_TERM_LENGTH,
    paymentRemaining: TEXTS.CONTRACT_2_PAYMENT_REMAINING,
  },
];

export default { COLORS, TEXTS, CONTRACT_TABLE_HEADERS, MOCK_CONTRACTS };
