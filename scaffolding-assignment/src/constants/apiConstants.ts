export const API_CONSTANTS = {
  BASE_URL: 'http://localhost:3001',
  ENDPOINTS: {
    PAYMENTS: '/payments',
    CONTRACTS: '/contracts',
    CASH_KICKS: '/cashKicks',
  },
  TIMEOUT_MS: 5000,
  ERROR_MESSAGES: {
    FETCH_PAYMENTS_FAILED: 'Failed to load payments data from server. Displaying mock data.',
    FETCH_CONTRACTS_FAILED: 'Failed to load contracts data from server. Displaying mock data.',
  },
};
