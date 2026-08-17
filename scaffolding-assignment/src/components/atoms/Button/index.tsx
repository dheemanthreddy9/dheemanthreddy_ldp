import React from 'react';
import MuiButton from '@mui/material/Button';
import type { ButtonProps as MuiButtonProps } from '@mui/material/Button';

export interface ButtonProps extends MuiButtonProps {
  className?: string;
}

const Button: React.FC<ButtonProps> = ({ children, variant = 'contained', className, ...props }) => {
  return (
    <MuiButton variant={variant} disableElevation className={className} {...props}>
      {children}
    </MuiButton>
  );
};

export default Button;
