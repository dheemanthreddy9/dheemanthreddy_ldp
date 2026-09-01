import React from 'react';
import { Typography } from '../../atoms/Typography';
import { EMAIL_PARAGRAPH_1, EMAIL_PARAGRAPH_2, COMPANY_NAME, SINCERELY_LABEL } from '../../../data/constants';

interface EmailNoticeBodyProps {
  candidateName: string;
  middleContent?: React.ReactNode;
}

export const EmailNoticeBody: React.FC<EmailNoticeBodyProps> = ({ candidateName, middleContent }) => {
  return (
    <div className="email-body">
      <Typography variant="body1">Dear {candidateName},</Typography>
      <br />
      <Typography variant="body1" className="email-paragraph">
        {EMAIL_PARAGRAPH_1}
      </Typography>

      {middleContent}

      <br />
      <Typography variant="body1" className="email-paragraph">
        {EMAIL_PARAGRAPH_2}
      </Typography>
      <br />
      <Typography variant="body1">{SINCERELY_LABEL}</Typography>
      <Typography variant="body1">{COMPANY_NAME}</Typography>
    </div>
  );
};
