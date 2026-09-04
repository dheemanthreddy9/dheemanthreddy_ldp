import React from 'react';
import { Box } from '@mui/material';
import Header from '../../organisms/Header';
import CashAccelerationStats from '../../organisms/CashAccelerationStats';
import PromoCard from '../../molecules/PromoCard';
import ContractsTable from '../../organisms/ContractsTable';
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
