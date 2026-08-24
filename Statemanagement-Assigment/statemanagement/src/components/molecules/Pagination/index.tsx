import React from 'react';
import { KeyboardArrowDownIcon, ChevronLeftIcon, ChevronRightIcon } from '../../../assets/icons';
import {
  DEFAULT_PER_PAGE,
  TOTAL_RESULTS_COUNT,
  PAGINATION_OUT_OF_LABEL,
  PAGINATION_RESULTS_LABEL,
  PAGINATION_PER_PAGE_LABEL,
} from '../../../data/constants';

interface PaginationProps {
  currentPage?: number;
  totalPages?: number;
  perPage?: number;
  totalResults?: number;
  onPageChange?: (page: number) => void;
}

export const Pagination: React.FC<PaginationProps> = ({
  currentPage = 1,
  totalPages = 3,
  perPage = DEFAULT_PER_PAGE,
  totalResults = TOTAL_RESULTS_COUNT,
  onPageChange,
}) => {
  return (
    <div className="card-footer">
      <div className="pagination-info">
        <span>
          {perPage} {PAGINATION_OUT_OF_LABEL} {totalResults} {PAGINATION_RESULTS_LABEL}
        </span>
        <div className="per-page-select">
          <span>
            {perPage} {PAGINATION_PER_PAGE_LABEL}
          </span>
          <KeyboardArrowDownIcon fontSize="small" />
        </div>
      </div>
      <div className="pagination-controls">
        <button
          type="button"
          className="page-nav-btn"
          disabled={currentPage === 1}
          onClick={() => onPageChange?.(currentPage - 1)}
        >
          <ChevronLeftIcon fontSize="small" />
        </button>
        {Array.from({ length: totalPages }, (_, i) => i + 1).map((page) => (
          <button
            key={page}
            type="button"
            className={`page-number-btn ${currentPage === page ? 'active' : ''}`}
            onClick={() => onPageChange?.(page)}
          >
            {page}
          </button>
        ))}
        <button
          type="button"
          className="page-nav-btn"
          disabled={currentPage === totalPages}
          onClick={() => onPageChange?.(currentPage + 1)}
        >
          <ChevronRightIcon fontSize="small" />
        </button>
      </div>
    </div>
  );
};
