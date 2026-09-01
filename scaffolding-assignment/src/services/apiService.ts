import axios, { type AxiosInstance } from 'axios';
import type { Payment, Contract, CashKick } from '../types/api';
import { API_CONSTANTS } from '../constants/apiConstants';

export const apiClient: AxiosInstance = axios.create({
  baseURL: API_CONSTANTS.BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: API_CONSTANTS.TIMEOUT_MS,
});

export const getPayments = async (): Promise<Payment[]> => {
  const response = await apiClient.get<Payment[]>(API_CONSTANTS.ENDPOINTS.PAYMENTS);
  return response.data;
};

export const getContracts = async (): Promise<Contract[]> => {
  const response = await apiClient.get<Contract[]>(API_CONSTANTS.ENDPOINTS.CONTRACTS);
  return response.data;
};

export const getCashKicks = async (): Promise<CashKick[]> => {
  const response = await apiClient.get<CashKick[]>(API_CONSTANTS.ENDPOINTS.CASH_KICKS);
  return response.data;
};

export const createCashKick = async (
  cashKickData: Omit<CashKick, 'id'>
): Promise<CashKick> => {
  const response = await apiClient.post<CashKick>(API_CONSTANTS.ENDPOINTS.CASH_KICKS, cashKickData);
  return response.data;
};

export const createPayment = async (
  paymentData: Omit<Payment, 'id'>
): Promise<Payment> => {
  const response = await apiClient.post<Payment>(API_CONSTANTS.ENDPOINTS.PAYMENTS, paymentData);
  return response.data;
};

export const apiService = {
  getPayments,
  getContracts,
  getCashKicks,
  createCashKick,
  createPayment,
};

export default apiService;
