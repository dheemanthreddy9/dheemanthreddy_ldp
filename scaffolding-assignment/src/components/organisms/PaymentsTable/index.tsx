import React, { useState, useEffect } from 'react';
import { TableRow, TableCell, Chip, Box } from '@mui/material';
import Typography from '../../atoms/Typography';
import DataTable from '../../molecules/DataTable';
import { mockData } from '../../../data/mockData';
import { getPayments } from '../../../services/apiService';
import type { Payment } from '../../../types/api';

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

export default PaymentsTable;
