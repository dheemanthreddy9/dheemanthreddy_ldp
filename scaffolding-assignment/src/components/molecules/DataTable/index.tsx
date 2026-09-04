import React from 'react';
import {
  Table as MuiTable,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Box,
  CircularProgress,
} from '@mui/material';
import InfoOutlinedIcon from '@mui/icons-material/InfoOutlined';
import Typography from '../../atoms/Typography';

export interface DataTableProps {
  title?: string;
  titleIcon?: boolean;
  headers: string[];
  children: React.ReactNode;
  headerAction?: React.ReactNode;
  className?: string;
  loading?: boolean;
  error?: string | null;
}

export const DataTable: React.FC<DataTableProps> = ({
  title,
  titleIcon = true,
  headers,
  children,
  headerAction,
  className = '',
  loading = false,
}) => {
  return (
    <Box className={`payments-section ${className}`.trim()}>
      {title && (
        <Typography variant="h6" className="payments-title">
          {title} {titleIcon && <InfoOutlinedIcon className="payments-info-icon" />}
        </Typography>
      )}

      {headerAction}

      <TableContainer component={Paper} className="payments-table-paper">
        <MuiTable>
          <TableHead>
            <TableRow className="payments-table-header-row">
              {headers.map((header) => (
                <TableCell key={header} className="payments-table-header-cell">
                  <Typography variant="body2">{header}</Typography>
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {loading ? (
              <TableRow>
                <TableCell colSpan={headers.length} align="center" className="table-loading-cell">
                  <CircularProgress size={32} className="table-loading-spinner" />
                </TableCell>
              </TableRow>
            ) : (
              children
            )}
          </TableBody>
        </MuiTable>
      </TableContainer>
    </Box>
  );
};

export default DataTable;
