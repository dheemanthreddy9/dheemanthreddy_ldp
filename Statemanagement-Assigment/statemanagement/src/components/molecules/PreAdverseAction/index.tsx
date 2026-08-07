import React, { useState } from 'react';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { Button } from '../../atoms/Button';
import { Typography } from '../../atoms/Typography';
import type { Candidate } from '../CandidatesTable';

interface PreAdverseActionProps {
  candidate: Candidate;
  onBack: () => void;
  onPreviewNotice: (selectedCharges: string[]) => void;
}

export const PreAdverseAction: React.FC<PreAdverseActionProps> = ({ candidate, onBack, onPreviewNotice }) => {
  const [selectedCharges, setSelectedCharges] = useState<string[]>([]);

  const handleChargeToggle = (charge: string) => {
    setSelectedCharges((prev) =>
      prev.includes(charge)
        ? prev.filter((c) => c !== charge)
        : [...prev, charge]
    );
  };

  const chargesList = [
    'Driving while license suspended',
    'Assault Domestic Violence',
    'Unable to verify employment history at Dunder Mifflin'
  ];

  return (
    <div className="main-content-container">
      <div className="page-header">
        <div className="header-title-with-back">
          <button type="button" className="back-button" onClick={onBack}>
            <ArrowBackIcon fontSize="small" />
          </button>
          <Typography variant="h5" className="header-title">
            Pre-Adverse Action Notice
          </Typography>
        </div>
      </div>

      <div className="pre-adverse-action-card">
        <div className="email-header-boxes">
          <div className="email-header-row">
            <Typography variant="body1"><span className="email-label">From:</span> <span className="email-value">Kyle@Checkr.com</span></Typography>
          </div>
          <div className="email-header-row">
            <Typography variant="body1"><span className="email-label">To:</span> <span className="email-value">{candidate.name.split(' ').map(n => n.charAt(0).toUpperCase() + n.slice(1).toLowerCase()).join('.')}@Checkr.com</span></Typography>
          </div>
          <div className="email-header-row">
            <Typography variant="body1"><span className="email-label">Subject:</span> <span className="email-value">Pre-Adverse Action Notice - Checkr-Bpo</span></Typography>
          </div>
        </div>

        <div className="email-body">
          <Typography variant="body1">Dear {candidate.name},</Typography>
          <br />
          <Typography variant="body1" className="email-paragraph">
            You recently authorized checkr-bpo ("the company") to obtain consumer reports and/or investigate consumer reports about you from a consumer reporting agency. The Company is considering taking action in whole or in past on information in such report(s) including the following specific items identified in the report prepared by Checkr, Inc.
          </Typography>
          
          <Typography variant="subtitle1" className="charges-title">
            Select The Charges For The Pre adverse Action
          </Typography>

          <div className="charges-list">
            {chargesList.map((charge) => (
              <label key={charge} className="charge-checkbox-label">
                <input
                  type="checkbox"
                  checked={selectedCharges.includes(charge)}
                  onChange={() => handleChargeToggle(charge)}
                  className="custom-checkbox"
                />
                <Typography variant="body2">{charge}</Typography>
              </label>
            ))}
          </div>

          <br />
          <Typography variant="body1" className="email-paragraph">
            If you wish to dispute the accuracy of the information in the report directly with the consumer reporting agency (i.e., the source of the information contained in the report), you should contact the agency identified above directly.
          </Typography>
          <br />
          <Typography variant="body1">Sincerely,</Typography>
          <Typography variant="body1">Checkr-bpo</Typography>
        </div>

        <div className="pre-adverse-footer">
          <div className="auto-send-info">
            <Typography variant="body2">Auto send post adverse action</Typography>
            <input type="number" value={7} readOnly className="days-input" />
            <Typography variant="body2">Days</Typography>
          </div>
          <Button 
            variant="primary" 
            onClick={() => onPreviewNotice(selectedCharges)}
            disabled={selectedCharges.length === 0}
          >
            Preview Notice
          </Button>
        </div>
      </div>
    </div>
  );
};
