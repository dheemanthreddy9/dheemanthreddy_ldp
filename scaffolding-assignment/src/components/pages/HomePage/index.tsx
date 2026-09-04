import React from 'react';
import { Box } from '@mui/material';
import Header from '../../organisms/Header';
import { TermCard, OutstandingCard } from '../../molecules/StatMetricCard';
import PromoCard from '../../molecules/PromoCard';
import PaymentsTable from '../../organisms/PaymentsTable';

const HomePage: React.FC = () => {
  return (
    <>
      <Header />
      <Box className="dashboard-body">
        <Box className="dashboard-metrics-grid">
          <TermCard />
          <OutstandingCard />
          <PromoCard />
        </Box>
        <PaymentsTable />
      </Box>
    </>
  );
};

export default HomePage;
