import React from 'react';
import { Box } from '@mui/material';
import Header from '../Header';
import { TermCard, OutstandingCard, PromoCard } from '../MetricCards';
import PaymentsTable from '../PaymentsTable';

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
