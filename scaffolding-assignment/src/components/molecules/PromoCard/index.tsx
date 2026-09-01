import React from 'react';
import { Card, CardContent } from '@mui/material';
import Typography from '../../atoms/Typography';
import Button from '../../atoms/Button';
import { mockData } from '../../../data/mockData';

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

export default PromoCard;
