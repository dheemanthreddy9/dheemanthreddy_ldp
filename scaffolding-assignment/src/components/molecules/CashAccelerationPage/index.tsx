import React from 'react';
import { Box } from '@mui/material';
import Header from '../Header';
import CashAccelerationStats from '../CashAccelerationStats';
import { PromoCard } from '../MetricCards';
import ContractsTable from '../ContractsTable';
import { mockData } from '../../../data/mockData';

const CashAccelerationPage: React.FC = () => {
  const { title, subtitle } = mockData.cashAcceleration;

  return (
    <>
      <Header title={title} subtitle={subtitle} />
      <Box className="dashboard-body">
        <Box className="dashboard-metrics-grid cash-acceleration-metrics-grid">
          <CashAccelerationStats />
          <PromoCard />
        </Box>
        <ContractsTable />
      </Box>
    </>
  );
};

export default CashAccelerationPage;
