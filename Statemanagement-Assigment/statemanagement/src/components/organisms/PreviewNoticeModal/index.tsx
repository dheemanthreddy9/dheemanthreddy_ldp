import React from 'react';
import { CloseIcon, AttachmentIcon } from '../../../assets/icons';
import { Button } from '../../atoms/Button';
import { Typography } from '../../atoms/Typography';
import { EmailHeader } from '../../molecules/EmailHeader';
import { EmailNoticeBody } from '../../molecules/EmailNoticeBody';
import {
  WARNING_ALERTS,
  FCRA_ATTACHMENTS,
  PRE_ADVERSE_NOTICE_TITLE,
  ATTACHMENTS_TITLE,
  SUBMIT_NOTICE_BUTTON_LABEL,
} from '../../../data/constants';
import type { Candidate } from '../CandidatesTable';

interface PreviewNoticeModalProps {
  candidate: Candidate;
  selectedCharges: string[];
  onClose: () => void;
  onSubmit: () => void;
}

export const PreviewNoticeModal: React.FC<PreviewNoticeModalProps> = ({
  candidate,
  selectedCharges,
  onClose,
  onSubmit,
}) => {
  const selectedChargesPreview = (
    <ul className="charges-list-preview">
      {selectedCharges.map((charge) => (
        <li key={charge}>
          <Typography variant="body2">{charge}</Typography>
        </li>
      ))}
    </ul>
  );

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <div className="modal-header">
          <Typography variant="h6">{PRE_ADVERSE_NOTICE_TITLE}</Typography>
          <button type="button" className="close-button" onClick={onClose}>
            <CloseIcon fontSize="small" />
          </button>
        </div>

        <div className="modal-body">
          <EmailHeader candidateName={candidate.name} noBorder />

          <div className="warning-alert">
            <ul>
              {WARNING_ALERTS.map((alertText) => (
                <li key={alertText}>
                  <Typography variant="body2">{alertText}</Typography>
                </li>
              ))}
            </ul>
          </div>

          <EmailNoticeBody
            candidateName={candidate.name}
            middleContent={selectedChargesPreview}
          />

          <div className="attachments-section">
            <Typography variant="subtitle2" className="attachments-title">
              {ATTACHMENTS_TITLE}
            </Typography>
            {FCRA_ATTACHMENTS.map((attachment) => (
              <div key={attachment} className="attachment-item">
                <AttachmentIcon fontSize="small" className="attachment-icon" />
                <Typography variant="body2">{attachment}</Typography>
              </div>
            ))}
          </div>
        </div>

        <div className="modal-footer">
          <Button variant="primary" onClick={onSubmit}>
            {SUBMIT_NOTICE_BUTTON_LABEL}
          </Button>
        </div>
      </div>
    </div>
  );
};
