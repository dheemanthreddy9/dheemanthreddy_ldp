import React, { useState } from 'react';
import { Box } from '@mui/material';
import Sidebar from '../Sidebar';
import HomePage from '../HomePage';
import CashAccelerationPage from '../CashAccelerationPage';
import type { AppPage } from '../../../types/navigation';

export interface DashboardProps {
  initialPage?: AppPage;
}

const Dashboard: React.FC<DashboardProps> = ({ initialPage = 'home' }) => {
  const [activePage, setActivePage] = useState<AppPage>(initialPage);

  return (
    <Box className="dashboard-container">
      <Sidebar activePage={activePage} onNavigate={setActivePage} />
      <Box className="dashboard-main-content">
        {activePage === 'home' ? <HomePage /> : <CashAccelerationPage />}
      </Box>
    </Box>
  );
};

export default Dashboard;
