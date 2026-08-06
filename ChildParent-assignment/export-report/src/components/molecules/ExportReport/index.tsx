import Typography from "../../atoms/Typography";
import DatePicker from "../../atoms/DatePicker";
import Button from "../../atoms/Button";
import STRINGS from "../../../constants/strings";

const ExportReport = () => {
  return (
    <div className="container">
      <Typography text={STRINGS.TITLE} />

      <hr className="divider" />

      <div className="date-section">
        <div className="field">
          <Typography text={STRINGS.REPORTS_FROM_LABEL} />
          <DatePicker id={STRINGS.REPORTS_FROM_ID} />
        </div>

        <div className="field">
          <Typography text={STRINGS.REPORTS_TO_LABEL} />
          <DatePicker id={STRINGS.REPORTS_TO_ID} />
        </div>
      </div>

      <hr className="divider-bottom" />

      <div className="button-section">
        <Button text={STRINGS.EXPORT_BUTTON_LABEL} />
      </div>
    </div>
  );
};

export default ExportReport;
