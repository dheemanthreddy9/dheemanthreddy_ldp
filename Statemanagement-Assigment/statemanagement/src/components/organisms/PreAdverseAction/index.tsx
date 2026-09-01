import React, { useState } from 'react';
import { ArrowBackIcon } from '../../../assets/icons';
import { Button } from '../../atoms/Button';
import { Typography } from '../../atoms/Typography';
import { Checkbox } from '../../atoms/Checkbox';
import { EmailHeader } from '../../molecules/EmailHeader';
import { EmailNoticeBody } from '../../molecules/EmailNoticeBody';
import {
  chargesList,
  DEFAULT_AUTO_SEND_DAYS,
  PRE_ADVERSE_NOTICE_TITLE,
  CHARGES_SELECT_TITLE,
  AUTO_SEND_LABEL_1,
  AUTO_SEND_LABEL_2,
  PREVIEW_NOTICE_BUTTON_LABEL,
} from '../../../data/constants';
import type { Candidate } from '../CandidatesTable';

interface PreAdverseActionProps {
  candidate: Candidate;
  onBack: () => void;
  onPreviewNotice: (selectedCharges: string[]) => void;
}

export const PreAdverseAction: React.FC<PreAdverseActionProps> = ({
  candidate,
  onBack,
  onPreviewNotice,
}) => {
  const [selectedCharges, setSelectedCharges] = useState<string[]>([]);

  const handleChargeToggle = (charge: string) => {
    setSelectedCharges((prev) =>
      prev.includes(charge)
        ? prev.filter((c) => c !== charge)
        : [...prev, charge]
    );
  };

  const chargesSelectionContent = (
    <>
      <Typography variant="subtitle1" className="charges-title">
        {CHARGES_SELECT_TITLE}
      </Typography>

      <div className="charges-list">
        {chargesList.map((charge) => (
          <label key={charge} className="charge-checkbox-label">
            <Checkbox
              checked={selectedCharges.includes(charge)}
              onChange={() => handleChargeToggle(charge)}
            />
            <Typography variant="body2">{charge}</Typography>
          </label>
        ))}
      </div>
    </>
  );

  return (
    <div className="main-content-container">
      <div className="page-header">
        <div className="header-title-with-back">
          <button type="button" className="back-button" onClick={onBack}>
            <ArrowBackIcon fontSize="small" />
          </button>
          <Typography variant="h5" className="header-title">
            {PRE_ADVERSE_NOTICE_TITLE}
          </Typography>
        </div>
      </div>

      <div className="pre-adverse-action-card">
        <EmailHeader candidateName={candidate.name} />

        <EmailNoticeBody
          candidateName={candidate.name}
          middleContent={chargesSelectionContent}
        />

        <div className="pre-adverse-footer">
          <div className="auto-send-info">
            <Typography variant="body2">{AUTO_SEND_LABEL_1}</Typography>
            <input type="number" value={DEFAULT_AUTO_SEND_DAYS} readOnly className="days-input" />
            <Typography variant="body2">{AUTO_SEND_LABEL_2}</Typography>
          </div>
          <Button
            variant="primary"
            onClick={() => onPreviewNotice(selectedCharges)}
            disabled={selectedCharges.length === 0}
          >
            {PREVIEW_NOTICE_BUTTON_LABEL}
          </Button>
        </div>
      </div>
    </div>
  );
};
