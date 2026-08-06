import calendarIcon from "../../../assets/calendar.png";

type DatePickerProps = {
  id: string;
};

const DatePicker = ({ id }: DatePickerProps) => (
  <div className="date-container">
    <input type="date" id={id} />

    <label htmlFor={id}>
        <img src={calendarIcon} alt="calendar" className="calendar-icon" />
    </label>
  </div>
);

export default DatePicker;
