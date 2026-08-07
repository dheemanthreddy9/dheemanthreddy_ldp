import React from 'react';
import CloseIcon from '@mui/icons-material/Close';
import AttachmentIcon from '@mui/icons-material/Attachment';
import { Button } from '../../atoms/Button';
import { Typography } from '../../atoms/Typography';
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
  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <div className="modal-header">
          <Typography variant="h6">Pre-Adverse Action Notice</Typography>
          <button type="button" className="close-button" onClick={onClose}>
            <CloseIcon fontSize="small" />
          </button>
        </div>

        <div className="modal-body">
          <div className="email-header-boxes no-border">
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

          <div className="warning-alert">
            <ul>
              <li><Typography variant="body2">Please carefully review the list of charges (in bold) and your contact information.</Typography></li>
              <li><Typography variant="body2">Please note that we will send the corresponding post adverse action email automatically after 7 days.</Typography></li>
            </ul>
          </div>

          <div className="email-body">
            <Typography variant="body1">Dear {candidate.name},</Typography>
            <br />
            <Typography variant="body1" className="email-paragraph">
              You recently authorized checkr-bpo ("the company") to obtain consumer reports and/or investigate consumer reports about you from a consumer reporting agency. The Company is considering taking action in whole or in past on information in such report(s) including the following specific items identified in the report prepared by Checkr, Inc.
            </Typography>
            
            <ul className="charges-list-preview">
              {selectedCharges.map((charge) => (
                <li key={charge}>
                  <Typography variant="body2">{charge}</Typography>
                </li>
              ))}
            </ul>

            <br />
            <Typography variant="body1" className="email-paragraph">
              If you wish to dispute the accuracy of the information in the report directly with the consumer reporting agency (i.e., the source of the information contained in the report), you should contact the agency identified above directly.
            </Typography>
            <br />
            <Typography variant="body1">Sincerely,</Typography>
            <Typography variant="body1">Checkr-bpo</Typography>
          </div>
          
          <div className="attachments-section">
            <Typography variant="subtitle2" className="attachments-title">Attachments</Typography>
            <div className="attachment-item">
              <AttachmentIcon fontSize="small" className="attachment-icon" />
              <Typography variant="body2">Summary of right under the FCRA</Typography>
            </div>
            <div className="attachment-item">
              <AttachmentIcon fontSize="small" className="attachment-icon" />
              <Typography variant="body2">Copy of background report</Typography>
            </div>
          </div>
        </div>

        <div className="modal-footer">
          <Button variant="primary" onClick={onSubmit}>
            Submit Notice
          </Button>
        </div>
      </div>
    </div>
  );
};
