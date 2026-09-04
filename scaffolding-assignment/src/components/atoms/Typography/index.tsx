import React from 'react';
import MuiTypography from '@mui/material/Typography';
import type { TypographyProps as MuiTypographyProps } from '@mui/material/Typography';

export interface TypographyProps extends MuiTypographyProps {
  className?: string;
}

const Typography: React.FC<TypographyProps> = ({ children, className, ...props }) => {
  return (
    <MuiTypography className={className} {...props}>
      {children}
    </MuiTypography>
  );
};

export default Typography;
