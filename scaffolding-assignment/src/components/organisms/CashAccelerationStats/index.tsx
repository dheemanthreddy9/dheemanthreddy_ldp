import React from 'react';
import { Card, CardContent, Box } from '@mui/material';
import InfoOutlinedIcon from '@mui/icons-material/InfoOutlined';
import Typography from '../../atoms/Typography';
import Icon from '../../atoms/Icon';
import { icons } from '../../../assets/icons';
import { mockData } from '../../../data/mockData';

const statIcons = {
  termCap: icons.calendar,
  availableCredit: icons.documentDownload,
  maxInterestRate: icons.percentageSquare,
};

export const CashAccelerationStats: React.FC = () => {
  const { stats } = mockData.cashAcceleration;

  return (
    <Card className="metric-card cash-acceleration-stats-card">
      <CardContent className="cash-acceleration-stats-content">
        {stats.map((stat) => (
          <Box key={stat.id} className="cash-acceleration-stat-item">
            <Box className="cash-acceleration-stat-icon-box">
              <Icon
                src={statIcons[stat.id as keyof typeof statIcons]}
                alt=""
                className="cash-acceleration-stat-icon-image"
              />
            </Box>
            <Box className="cash-acceleration-stat-text">
              <Typography variant="body2" className="cash-acceleration-stat-label">
                {stat.label} <InfoOutlinedIcon className="metric-card-info-icon" />
              </Typography>
              <Typography component="p" className="cash-acceleration-stat-value">
                {stat.value}
              </Typography>
            </Box>
          </Box>
        ))}
      </CardContent>
    </Card>
  );
};

export default CashAccelerationStats;
