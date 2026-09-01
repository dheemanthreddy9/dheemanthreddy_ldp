import React from 'react';

interface BadgeProps {
  label: string;
  variant?: 'clear' | 'consider' | 'adverse-action' | 'default';
  className?: string;
}

export const Badge: React.FC<BadgeProps> = ({ label, variant, className = '' }) => {
  if (!label || label === '-') {
    return <span className="text-muted">-</span>;
  }

  const normalizedVariant =
    variant ||
    (label.toLowerCase().includes('adverse')
      ? 'adverse-action'
      : label.toLowerCase() === 'clear'
      ? 'clear'
      : label.toLowerCase() === 'consider'
      ? 'consider'
      : 'default');

  return (
    <span className={`badge badge-${normalizedVariant} ${className}`}>
      {label}
    </span>
  );
};
