import Typography from "../../atoms/Typography";
import DatePicker from "../../atoms/DatePicker";
import Button from "../../atoms/Button";

const ExportReport = () => {
  return (
    <div className="container">
      <Typography text="Export Candidate Reports CSV" />

      <hr className="divider" />

      <div className="date-section">
        <div className="field">
          <Typography text="Reports From" />
          <DatePicker id="reports-from" />
        </div>

        <div className="field">
          <Typography text="Reports To" />
          <DatePicker id="reports-to" />
        </div>
      </div>

      <hr className="divider-bottom" />

      <div className="button-section">
        <Button text="Export Report" />
      </div>
    </div>
  );
};

export default ExportReport;
