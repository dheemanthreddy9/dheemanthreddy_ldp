import React from 'react';
import MuiTypography from '@mui/material/Typography';

interface TypographyProps {
  children: React.ReactNode;
  variant?: 'h1' | 'h2' | 'h3' | 'h4' | 'h5' | 'h6' | 'subtitle1' | 'subtitle2' | 'body1' | 'body2' | 'caption';
  className?: string;
}

export const Typography: React.FC<TypographyProps> = ({
  children,
  variant = 'body1',
  className = '',
}) => {
  return (
    <MuiTypography variant={variant} className={`custom-typography ${className}`}>
      {children}
    </MuiTypography>
  );
};
