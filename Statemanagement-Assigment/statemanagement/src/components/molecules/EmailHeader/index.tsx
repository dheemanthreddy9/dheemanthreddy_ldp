import React from 'react';
import { Typography } from '../../atoms/Typography';
import {
  SENDER_EMAIL,
  EMAIL_SUBJECT,
  formatCandidateEmail,
  FROM_LABEL,
  TO_LABEL,
  SUBJECT_LABEL,
} from '../../../data/constants';

interface EmailHeaderProps {
  candidateName: string;
  noBorder?: boolean;
}

export const EmailHeader: React.FC<EmailHeaderProps> = ({ candidateName, noBorder = false }) => {
  const candidateEmail = formatCandidateEmail(candidateName);

  return (
    <div className={`email-header-boxes ${noBorder ? 'no-border' : ''}`}>
      <div className="email-header-row">
        <Typography variant="body1">
          <span className="email-label">{FROM_LABEL}</span>{' '}
          <span className="email-value">{SENDER_EMAIL}</span>
        </Typography>
      </div>
      <div className="email-header-row">
        <Typography variant="body1">
          <span className="email-label">{TO_LABEL}</span>{' '}
          <span className="email-value">{candidateEmail}</span>
        </Typography>
      </div>
      <div className="email-header-row">
        <Typography variant="body1">
          <span className="email-label">{SUBJECT_LABEL}</span>{' '}
          <span className="email-value">{EMAIL_SUBJECT}</span>
        </Typography>
      </div>
    </div>
  );
};
