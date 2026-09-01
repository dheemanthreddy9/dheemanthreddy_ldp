import React from 'react';
import {
  FileDownloadOutlinedIcon,
  AddIcon,
  FilterListIcon,
  MoreVertIcon,
} from '../../../assets/icons';
import { Button } from '../../atoms/Button';
import { Typography } from '../../atoms/Typography';
import { Badge } from '../../atoms/Badge';
import { SearchInput } from '../../molecules/SearchInput';
import { Pagination } from '../../molecules/Pagination';
import {
  CANDIDATES_PAGE_TITLE,
  EXPORT_BUTTON_LABEL,
  MANUAL_ORDER_BUTTON_LABEL,
  CANDIDATE_INFO_TITLE,
  FILTER_BUTTON_LABEL,
  CANDIDATES_TABLE_HEADERS,
} from '../../../data/constants';

export interface Candidate {
  id: string;
  name: string;
  adjudication: string;
  status: string;
  location: string;
  date: string;
}

interface CandidatesTableProps {
  candidates: Candidate[];
  onSelectCandidate: (candidate: Candidate) => void;
}

export const CandidatesTable: React.FC<CandidatesTableProps> = ({
  candidates,
  onSelectCandidate,
}) => {
  return (
    <div className="main-content-container">
      <div className="page-header">
        <Typography variant="h5" className="header-title">
          {CANDIDATES_PAGE_TITLE}
        </Typography>
        <div className="header-actions">
          <Button variant="outline" startIcon={<FileDownloadOutlinedIcon fontSize="small" />}>
            {EXPORT_BUTTON_LABEL}
          </Button>
          <Button variant="primary" startIcon={<AddIcon fontSize="small" />}>
            {MANUAL_ORDER_BUTTON_LABEL}
          </Button>
        </div>
      </div>

      <div className="table-card">
        <div className="card-header">
          <Typography variant="subtitle1" className="card-title">
            {CANDIDATE_INFO_TITLE}
          </Typography>
          <div className="card-actions">
            <SearchInput />
            <button type="button" className="filter-button">
              <FilterListIcon fontSize="small" />
              <span>{FILTER_BUTTON_LABEL}</span>
            </button>
            <button type="button" className="icon-only-button">
              <MoreVertIcon fontSize="small" />
            </button>
          </div>
        </div>

        <div className="table-responsive">
          <table className="candidates-table">
            <thead>
              <tr>
                {CANDIDATES_TABLE_HEADERS.map((header) => (
                  <th key={header}>{header}</th>
                ))}
              </tr>
            </thead>
            <tbody>
              {candidates.map((item) => (
                <tr key={item.id}>
                  <td>
                    <button
                      type="button"
                      className="candidate-name-btn"
                      onClick={() => onSelectCandidate(item)}
                    >
                      {item.name}
                    </button>
                  </td>
                  <td>
                    <Badge label={item.adjudication} />
                  </td>
                  <td>
                    <Badge label={item.status} />
                  </td>
                  <td>{item.location}</td>
                  <td>{item.date}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        <Pagination currentPage={1} />
      </div>
    </div>
  );
};
