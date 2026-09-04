import React, { useState, useEffect } from 'react';
import { TableRow, TableCell, Chip, Box } from '@mui/material';
import Typography from '../../atoms/Typography';
import Button from '../../atoms/Button';
import DataTable from '../../molecules/DataTable';
import { mockData } from '../../../data/mockData';
import { getContracts, getCashKicks } from '../../../services/apiService';
import { API_CONSTANTS } from '../../../constants/apiConstants';
import type { Contract, CashKick } from '../../../types/api';

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
          console.error(err);
          setError(API_CONSTANTS.ERROR_MESSAGES.FETCH_CONTRACTS_FAILED);
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

export default ContractsTable;
