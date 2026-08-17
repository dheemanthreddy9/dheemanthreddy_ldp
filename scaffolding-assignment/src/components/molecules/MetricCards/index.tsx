import React from 'react';
import { Card, CardContent, Box } from '@mui/material';
import Typography from '../../atoms/Typography';
import Button from '../../atoms/Button';
import Icon from '../../atoms/Icon';
import InfoOutlinedIcon from '@mui/icons-material/InfoOutlined';
import { icons } from '../../../assets/icons';
import { mockData } from '../../../data/mockData';

// --- Base Stat Metric Card ---
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

// --- Term Card Molecule ---
export const TermCard: React.FC = () => {
  const { metrics } = mockData;
  return (
    <StatMetricCard
      iconSrc={icons.receiptText}
      badgeText={metrics.term.dueLabel}
      label={`${metrics.term.labelPrefix}${metrics.term.date}`}
      value={metrics.term.amount}
    />
  );
};

// --- Outstanding Card Molecule ---
export const OutstandingCard: React.FC = () => {
  const { metrics } = mockData;
  return (
    <StatMetricCard
      iconSrc={icons.circularProgress}
      label={metrics.outstanding.label}
      value={metrics.outstanding.amount}
    />
  );
};

// --- Promo Card Molecule ---
export const PromoCard: React.FC = () => {
  const { metrics } = mockData;

  return (
    <Card className="metric-card">
      <CardContent className="metric-card-content">
        <Typography variant="h6" className="metric-card-promo-title">
          {metrics.cashKick.title}
        </Typography>
        <Typography variant="body2" className="metric-card-promo-desc">
          {metrics.cashKick.descriptionPrefix}
          <span className="metric-card-promo-highlight">{metrics.cashKick.amountHighlight}</span>
          {metrics.cashKick.descriptionSuffix}
        </Typography>
        <Button variant="contained" className="metric-card-promo-btn">
          {metrics.cashKick.buttonLabel}
        </Button>
      </CardContent>
    </Card>
  );
};

// --- Cash Acceleration Stats Molecule ---
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
