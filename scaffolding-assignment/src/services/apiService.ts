import axios, { type AxiosInstance } from 'axios';
import type { Payment, Contract, CashKick } from '../types/api';

const BASE_URL = 'http://localhost:3001';
export const apiClient: AxiosInstance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 5000,
});

/**
 * Fetch payments list asynchronously from API using Axios.
 */
export const getPayments = async (): Promise<Payment[]> => {
  const response = await apiClient.get<Payment[]>('/payments');
  return response.data;
};

/**
 * Fetch contracts list asynchronously from API using Axios.
 */
export const getContracts = async (): Promise<Contract[]> => {
  const response = await apiClient.get<Contract[]>('/contracts');
  return response.data;
};

/**
 * Fetch cash kicks list asynchronously from API using Axios.
 */
export const getCashKicks = async (): Promise<CashKick[]> => {
  const response = await apiClient.get<CashKick[]>('/cashKicks');
  return response.data;
};

/**
 * Create a new cash kick entry via API using Axios.
 */
export const createCashKick = async (
  cashKickData: Omit<CashKick, 'id'>
): Promise<CashKick> => {
  const response = await apiClient.post<CashKick>('/cashKicks', cashKickData);
  return response.data;
};

/**
 * Create a new payment entry via API using Axios.
 */
export const createPayment = async (
  paymentData: Omit<Payment, 'id'>
): Promise<Payment> => {
  const response = await apiClient.post<Payment>('/payments', paymentData);
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
