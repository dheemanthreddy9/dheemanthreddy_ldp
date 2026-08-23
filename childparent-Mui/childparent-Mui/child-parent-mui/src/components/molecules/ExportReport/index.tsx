import Box from "@mui/material/Box";
import Divider from "@mui/material/Divider";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";

import Typography from "../../atoms/Typography";
import DatePicker from "../../atoms/DatePicker";
import Button from "../../atoms/Button";

const ExportReport = () => {
  return (
    <Paper elevation={0} className="export-report-paper">
      <Box className="export-report-header">
        <Typography
          text="Export Candidate Reports CSV"
          variant="subtitle1"
          className="export-report-title"
        />
      </Box>

      <Divider />

      <Box className="export-report-date-section">
        <Stack direction="row" spacing={3} className="export-report-fields">
          <Box className="export-report-field-column">
            <Typography text="Reports From" variant="body2" className="export-report-label" />

            <Box className="export-report-picker-wrapper">
              <DatePicker className="date-picker-field" />
            </Box>
          </Box>

          <Box className="export-report-field-column">
            <Typography text="Reports To" variant="body2" className="export-report-label" />

            <DatePicker className="date-picker-field" />
          </Box>
        </Stack>
      </Box>

      <Divider />

      <Box className="export-report-actions">
        <Button text="Export Report" className="export-report-button" />
      </Box>
    </Paper>
  );
};

export default ExportReport;