import React, { useState, useEffect } from 'react';
import {
  Table as MuiTable,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Box,
  Chip,
  CircularProgress,
} from '@mui/material';
import InfoOutlinedIcon from '@mui/icons-material/InfoOutlined';
import Typography from '../../atoms/Typography';
import Button from '../../atoms/Button';
import { mockData } from '../../../data/mockData';
import { getPayments, getContracts, getCashKicks } from '../../../services/apiService';
import type { Payment, Contract, CashKick } from '../../../types/api';

// --- Base DataTable Component ---
export interface DataTableProps {
  title?: string;
  titleIcon?: boolean;
  headers: string[];
  children: React.ReactNode;
  headerAction?: React.ReactNode;
  className?: string;
  loading?: boolean;
  error?: string | null;
}

export const DataTable: React.FC<DataTableProps> = ({
  title,
  titleIcon = true,
  headers,
  children,
  headerAction,
  className = '',
  loading = false,
}) => {
  return (
    <Box className={`payments-section ${className}`.trim()}>
      {title && (
        <Typography variant="h6" className="payments-title">
          {title} {titleIcon && <InfoOutlinedIcon className="payments-info-icon" />}
        </Typography>
      )}

      {headerAction}

      <TableContainer component={Paper} className="payments-table-paper">
        <MuiTable sx={{ width: '100%' }}>
          <TableHead>
            <TableRow className="payments-table-header-row">
              {headers.map((header) => (
                <TableCell key={header} className="payments-table-header-cell">
                  <Typography variant="body2">{header}</Typography>
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {loading ? (
              <TableRow>
                <TableCell colSpan={headers.length} align="center" sx={{ py: 4 }}>
                  <CircularProgress size={32} sx={{ color: '#A5A5A6' }} />
                </TableCell>
              </TableRow>
            ) : (
              children
            )}
          </TableBody>
        </MuiTable>
      </TableContainer>
    </Box>
  );
};

// --- Payments Table Molecule ---
export const PaymentsTable: React.FC = () => {
  const { tableHeaders, paymentsTitle } = mockData;
  const [payments, setPayments] = useState<Payment[]>(mockData.payments);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    let isMounted = true;
    const fetchPaymentsData = async () => {
      try {
        setLoading(true);
        const data = await getPayments();
        if (isMounted) {
          setPayments(data);
          setError(null);
        }
      } catch (err) {
        if (isMounted) {
          console.error('Failed to fetch payments from API:', err);
          setError('Failed to load data from server. Displaying mock data.');
        }
      } finally {
        if (isMounted) {
          setLoading(false);
        }
      }
    };

    fetchPaymentsData();
    return () => {
      isMounted = false;
    };
  }, []);

  return (
    <DataTable title={paymentsTitle} headers={tableHeaders} loading={loading} error={error}>
      {payments.map((row, index) => {
        const cellClass =
          index === payments.length - 1 ? 'payments-table-row-cell-last' : 'payments-table-row-cell';
        return (
          <TableRow key={row.id}>
            <TableCell className={cellClass}>
              <Box className="payments-due-date-cell">
                <Typography variant="body2" className="payments-due-date">
                  {row.dueDate}
                </Typography>
                <Typography variant="caption" className="payments-days-away">
                  {row.daysFromNow}
                </Typography>
              </Box>
            </TableCell>
            <TableCell className={cellClass}>
              <Chip label={row.status} size="small" className="payments-status-chip" />
            </TableCell>
            <TableCell className={cellClass}>
              <Typography variant="body2" className="payments-amount-text">
                {row.expectedAmount}
              </Typography>
            </TableCell>
            <TableCell className={cellClass}>
              <Typography variant="body2" className="payments-amount-text">
                {row.outstanding}
              </Typography>
            </TableCell>
          </TableRow>
        );
      })}
    </DataTable>
  );
};

// --- Contracts Table Molecule ---
type ContractsTab = 'contracts' | 'cashKicks';

export const ContractsTable: React.FC = () => {
  const { sectionTitle, tabs, contractHeaders, cashKickTableHeaders } =
    mockData.cashAcceleration;
  const [activeTab, setActiveTab] = useState<ContractsTab>('contracts');
  const [contracts, setContracts] = useState<Contract[]>(mockData.cashAcceleration.contracts);
  const [cashKicks, setCashKicks] = useState<CashKick[]>(mockData.cashAcceleration.cashKicks);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    let isMounted = true;
    const fetchContractsAndCashKicks = async () => {
      try {
        setLoading(true);
        // Using Promises & async/await concurrently with Promise.all
        const [contractsData, cashKicksData] = await Promise.all([
          getContracts(),
          getCashKicks(),
        ]);
        if (isMounted) {
          setContracts(contractsData);
          setCashKicks(cashKicksData);
          setError(null);
        }
      } catch (err) {
        if (isMounted) {
          console.error('Failed to fetch contracts or cash kicks from API:', err);
          setError('Failed to load data from server. Displaying mock data.');
        }
      } finally {
        if (isMounted) {
          setLoading(false);
        }
      }
    };

    fetchContractsAndCashKicks();
    return () => {
      isMounted = false;
    };
  }, []);

  const isContractsTab = activeTab === 'contracts';
  const headers = isContractsTab ? contractHeaders : cashKickTableHeaders;

  const headerAction = (
    <Box className="contracts-tabs">
      <Button
        variant="outlined"
        className={isContractsTab ? 'contracts-tab contracts-tab-active' : 'contracts-tab'}
        onClick={() => setActiveTab('contracts')}
      >
        {tabs.myContracts}
      </Button>
      <Button
        variant="outlined"
        className={!isContractsTab ? 'contracts-tab contracts-tab-active' : 'contracts-tab'}
        onClick={() => setActiveTab('cashKicks')}
      >
        {tabs.myCashKicks}
      </Button>
    </Box>
  );

  return (
    <DataTable
      title={sectionTitle}
      headers={headers}
      headerAction={headerAction}
      className="contracts-section"
      loading={loading}
      error={error}
    >
      {isContractsTab
        ? contracts.map((row, index) => {
            const cellClass =
              index === contracts.length - 1 ? 'payments-table-row-cell-last' : 'payments-table-row-cell';
            return (
              <TableRow key={row.id}>
                <TableCell className={cellClass}>
                  <Typography variant="body2" className="payments-amount-text">
                    {row.name}
                  </Typography>
                </TableCell>
                <TableCell className={cellClass}>
                  <Typography variant="body2" className="payments-amount-text">
                    {row.type}
                  </Typography>
                </TableCell>
                <TableCell className={cellClass}>
                  <Typography variant="body2" className="payments-amount-text">
                    {row.perPayment}
                  </Typography>
                </TableCell>
                <TableCell className={cellClass}>
                  <Box className="payments-due-date-cell">
                    <Typography variant="body2" className="payments-due-date">
                      {row.termLength}
                    </Typography>
                    <Typography variant="caption" className="payments-days-away">
                      {row.termFee}
                    </Typography>
                  </Box>
                </TableCell>
                <TableCell className={cellClass}>
                  <Box className="payments-due-date-cell">
                    <Typography variant="body2" className="payments-due-date">
                      {row.paymentAmount}
                    </Typography>
                    {row.secondaryPaymentAmount && (
                      <Typography variant="caption" className="payments-days-away">
                        {row.secondaryPaymentAmount}
                      </Typography>
                    )}
                  </Box>
                </TableCell>
              </TableRow>
            );
          })
        : cashKicks.map((row, index) => {
            const cellClass =
              index === cashKicks.length - 1 ? 'payments-table-row-cell-last' : 'payments-table-row-cell';
            return (
              <TableRow key={row.id}>
                <TableCell className={cellClass}>
                  <Typography variant="body2" className="payments-amount-text">
                    {row.name}
                  </Typography>
                </TableCell>
                <TableCell className={cellClass}>
                  <Chip label={row.status} size="small" className="payments-status-chip" />
                </TableCell>
                <TableCell className={cellClass}>
                  <Typography variant="body2" className="payments-amount-text">
                    {row.maturity}
                  </Typography>
                </TableCell>
                <TableCell className={cellClass}>
                  <Box className="payments-due-date-cell">
                    <Typography variant="body2" className="payments-due-date">
                      {row.totalReceived}
                    </Typography>
                    <Typography variant="caption" className="payments-days-away">
                      {row.totalReceivedFee}
                    </Typography>
                  </Box>
                </TableCell>
                <TableCell className={cellClass}>
                  <Typography variant="body2" className="payments-amount-text">
                    {row.totalFinanced}
                  </Typography>
                </TableCell>
              </TableRow>
            );
          })}
    </DataTable>
  );
};

export default DataTable;
