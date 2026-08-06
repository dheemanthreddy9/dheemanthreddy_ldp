import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DatePicker as MuiDatePicker } from "@mui/x-date-pickers/DatePicker";
import CustomCalendarIcon from "./CustomCalendarIcon";

type DatePickerProps = {
  className?: string;
};

const DatePicker = ({ className }: DatePickerProps) => {
  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <MuiDatePicker
        className={className}
        views={["day"]}
        format="DD/MM/YYYY"
        slots={{
          openPickerIcon: CustomCalendarIcon,
        }}
        slotProps={{
          textField: {
            size: "small",
          },
          calendarHeader: {
            format: "MMMM",
          },
        }}
      />
    </LocalizationProvider>
  );
};

export default DatePicker;