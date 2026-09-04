import React from 'react';
import { Card, CardContent, Box } from '@mui/material';
import Typography from '../../atoms/Typography';
import Icon from '../../atoms/Icon';
import InfoOutlinedIcon from '@mui/icons-material/InfoOutlined';
import receiptTextIcon from '../../../assets/receipt-text.svg';
import circularProgressIcon from '../../../assets/circular-progress.svg';
import { mockData } from '../../../data/mockData';

export interface StatMetricCardProps {
  iconSrc: string;
  badgeText?: string;
  label: string;
  value: string;
}

export const StatMetricCard: React.FC<StatMetricCardProps> = ({
  iconSrc,
  badgeText,
  label,
  value,
}) => {
  return (
    <Card className="metric-card">
      <CardContent className="metric-card-content">
        <Box className="metric-card-header-row">
          <Box className="metric-card-icon-box">
            <Icon src={iconSrc} alt="" className="metric-card-icon-image" />
          </Box>
          {badgeText && <Box className="metric-card-due-badge">{badgeText}</Box>}
        </Box>
        <Box className="metric-card-bottom">
          <Typography variant="body2" className="metric-card-label">
            {label} <InfoOutlinedIcon className="metric-card-info-icon" />
          </Typography>
          <Typography component="p" className="metric-card-value">
            {value}
          </Typography>
        </Box>
      </CardContent>
    </Card>
  );
};

export const TermCard: React.FC = () => {
  const { metrics } = mockData;
  return (
    <StatMetricCard
      iconSrc={receiptTextIcon}
      badgeText={metrics.term.dueLabel}
      label={`${metrics.term.labelPrefix}${metrics.term.date}`}
      value={metrics.term.amount}
    />
  );
};

export const OutstandingCard: React.FC = () => {
  const { metrics } = mockData;
  return (
    <StatMetricCard
      iconSrc={circularProgressIcon}
      label={metrics.outstanding.label}
      value={metrics.outstanding.amount}
    />
  );
};

export default StatMetricCard;
