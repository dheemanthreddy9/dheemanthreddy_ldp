import React from 'react';
import { ArrowBackIcon } from '../../../assets/icons';
import { Button } from '../../atoms/Button';
import { Typography } from '../../atoms/Typography';
import { Badge } from '../../atoms/Badge';
import { AccordionCard } from '../../molecules/AccordionCard';
import {
  courtSearches,
  PRE_ADVERSE_ACTION_BUTTON_LABEL,
  ENGAGE_BUTTON_LABEL,
  CANDIDATE_INFO_TITLE,
  REPORT_INFO_TITLE,
  COURT_SEARCHES_TITLE,
  COURT_SEARCHES_TABLE_HEADERS,
} from '../../../data/constants';
import type { Candidate } from '../CandidatesTable';

interface CandidateDetailsProps {
  candidate: Candidate;
  onBack: () => void;
  onPreAdverseAction: () => void;
}

export const CandidateDetails: React.FC<CandidateDetailsProps> = ({
  candidate,
  onBack,
  onPreAdverseAction,
}) => {
  return (
    <div className="main-content-container">
      <div className="page-header">
        <div className="header-title-with-back">
          <button type="button" className="back-button" onClick={onBack}>
            <ArrowBackIcon fontSize="small" />
          </button>
          <Typography variant="h5" className="header-title">
            {candidate.name}
          </Typography>
        </div>
        <div className="header-actions">
          <Button variant="outline" onClick={onPreAdverseAction}>
            {PRE_ADVERSE_ACTION_BUTTON_LABEL}
          </Button>
          <Button variant="primary">{ENGAGE_BUTTON_LABEL}</Button>
        </div>
      </div>

      <AccordionCard title={CANDIDATE_INFO_TITLE} />
      <AccordionCard title={REPORT_INFO_TITLE} />

      <div className="table-card details-table-card">
        <div className="card-header">
          <Typography variant="subtitle1" className="card-title">
            {COURT_SEARCHES_TITLE}
          </Typography>
        </div>

        <div className="table-responsive">
          <table className="candidates-table">
            <thead>
              <tr>
                {COURT_SEARCHES_TABLE_HEADERS.map((header) => (
                  <th key={header}>{header}</th>
                ))}
              </tr>
            </thead>
            <tbody>
              {courtSearches.map((item) => (
                <tr key={item.id}>
                  <td className="search-name">{item.search}</td>
                  <td>
                    <Badge label={item.status} />
                  </td>
                  <td>{item.date}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};
