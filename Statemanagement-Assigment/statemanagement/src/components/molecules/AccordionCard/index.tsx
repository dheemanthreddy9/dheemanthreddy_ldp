import React from 'react';
import { KeyboardArrowDownIcon } from '../../../assets/icons';
import { Typography } from '../../atoms/Typography';

interface AccordionCardProps {
  title: string;
}

export const AccordionCard: React.FC<AccordionCardProps> = ({ title }) => {
  return (
    <div className="accordion-card">
      <div className="accordion-header">
        <Typography variant="subtitle1" className="accordion-title">
          {title}
        </Typography>
        <KeyboardArrowDownIcon fontSize="small" className="accordion-icon" />
      </div>
    </div>
  );
};
