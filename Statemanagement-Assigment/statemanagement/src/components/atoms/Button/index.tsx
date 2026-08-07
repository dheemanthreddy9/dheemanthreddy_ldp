import React from 'react';
import MuiButton from '@mui/material/Button';

interface ButtonProps {
  children: React.ReactNode;
  onClick?: () => void;
  variant?: 'primary' | 'secondary' | 'outline' | 'text';
  startIcon?: React.ReactNode;
  disabled?: boolean;
}

export const Button: React.FC<ButtonProps> = ({
  children,
  onClick,
  variant = 'primary',
  startIcon,
  disabled,
}) => {
  const muiVariant = variant === 'primary' ? 'contained' : variant === 'outline' ? 'outlined' : 'text';

  return (
    <MuiButton
      variant={muiVariant}
      onClick={onClick}
      startIcon={startIcon}
      disabled={disabled}
      disableElevation
      className={`custom-button custom-button-${variant}`}
    >
      {children}
    </MuiButton>
  );
};
