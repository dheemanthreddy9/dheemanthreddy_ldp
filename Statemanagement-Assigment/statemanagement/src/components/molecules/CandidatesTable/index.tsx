import React from 'react';
import FileDownloadOutlinedIcon from '@mui/icons-material/FileDownloadOutlined';
import AddIcon from '@mui/icons-material/Add';
import SearchIcon from '@mui/icons-material/Search';
import FilterListIcon from '@mui/icons-material/FilterList';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import { Button } from '../../atoms/Button';
import { Typography } from '../../atoms/Typography';

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
          Candidates
        </Typography>
        <div className="header-actions">
          <Button variant="outline" startIcon={<FileDownloadOutlinedIcon fontSize="small" />}>
            Export
          </Button>
          <Button variant="primary" startIcon={<AddIcon fontSize="small" />}>
            Manual Order
          </Button>
        </div>
      </div>

      <div className="table-card">
        <div className="card-header">
          <Typography variant="subtitle1" className="card-title">
            Candidate Information
          </Typography>
          <div className="card-actions">
            <div className="search-input-wrapper">
              <SearchIcon className="search-icon" fontSize="small" />
              <input type="text" placeholder="Search any candidate" className="search-input" readOnly />
            </div>
            <button type="button" className="filter-button">
              <FilterListIcon fontSize="small" />
              <span>Filter</span>
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
                <th>NAME</th>
                <th>ADJUDICATION</th>
                <th>STATUS</th>
                <th>LOCATION</th>
                <th>DATE</th>
              </tr>
            </thead>
            <tbody>
              {candidates.map((item) => (
                <tr key={item.id}>
                  <td>
                    <button type="button" className="candidate-name-btn" onClick={() => onSelectCandidate(item)}>
                      {item.name}
                    </button>
                  </td>
                  <td>
                    {item.adjudication === '-' ? (
                      <span className="text-muted">-</span>
                    ) : (
                      <span className={`badge badge-${item.adjudication.toLowerCase().replace(/\s+/g, '-')}`}>
                        {item.adjudication}
                      </span>
                    )}
                  </td>
                  <td>
                    <span className={`badge badge-${item.status.toLowerCase()}`}>
                      {item.status}
                    </span>
                  </td>
                  <td>{item.location}</td>
                  <td>{item.date}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        <div className="card-footer">
          <div className="pagination-info">
            <span>10 out of 84 results</span>
            <div className="per-page-select">
              <span>10 per page</span>
              <KeyboardArrowDownIcon fontSize="small" />
            </div>
          </div>
          <div className="pagination-controls">
            <button type="button" className="page-nav-btn" disabled>
              <ChevronLeftIcon fontSize="small" />
            </button>
            <button type="button" className="page-number-btn active">1</button>
            <button type="button" className="page-number-btn">2</button>
            <button type="button" className="page-number-btn">3</button>
            <button type="button" className="page-nav-btn">
              <ChevronRightIcon fontSize="small" />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
