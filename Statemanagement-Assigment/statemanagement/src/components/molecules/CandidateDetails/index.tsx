import React from 'react';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import { Button } from '../../atoms/Button';
import { Typography } from '../../atoms/Typography';
import type { Candidate } from '../CandidatesTable';

interface CandidateDetailsProps {
  candidate: Candidate;
  onBack: () => void;
  onPreAdverseAction: () => void;
}

export const CandidateDetails: React.FC<CandidateDetailsProps> = ({ candidate, onBack, onPreAdverseAction }) => {
  const courtSearches = [
    { id: '1', search: 'SSN Verification', status: 'CLEAR', date: '2/22/2022' },
    { id: '2', search: 'Sex Offender', status: 'CLEAR', date: '3/13/2022' },
    { id: '3', search: 'Global Watchlist', status: 'CONSIDER', date: '7/2/2022' },
    { id: '4', search: 'Federal Criminal', status: 'CLEAR', date: '2/20/2022' },
    { id: '5', search: 'County Criminal', status: 'CLEAR', date: '5/19/2022' },
  ];

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
          <Button variant="outline" onClick={onPreAdverseAction}>Pre-Adverse Action</Button>
          <Button variant="primary">Engage</Button>
        </div>
      </div>

      <div className="accordion-card">
        <div className="accordion-header">
          <Typography variant="subtitle1" className="accordion-title">
            Candidate Information
          </Typography>
          <KeyboardArrowDownIcon fontSize="small" className="accordion-icon" />
        </div>
      </div>

      <div className="accordion-card">
        <div className="accordion-header">
          <Typography variant="subtitle1" className="accordion-title">
            Report Information
          </Typography>
          <KeyboardArrowDownIcon fontSize="small" className="accordion-icon" />
        </div>
      </div>

      <div className="table-card details-table-card">
        <div className="card-header">
          <Typography variant="subtitle1" className="card-title">
            Court Searches
          </Typography>
        </div>

        <div className="table-responsive">
          <table className="candidates-table">
            <thead>
              <tr>
                <th>SEARCH</th>
                <th>STATUS</th>
                <th>DATE</th>
              </tr>
            </thead>
            <tbody>
              {courtSearches.map((item) => (
                <tr key={item.id}>
                  <td className="search-name">{item.search}</td>
                  <td>
                    <span className={`badge badge-${item.status.toLowerCase()}`}>
                      {item.status}
                    </span>
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
