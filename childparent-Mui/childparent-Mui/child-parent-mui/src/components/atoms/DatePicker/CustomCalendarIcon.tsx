import CalendarImage from '../../../assets/Calendar.png';

const CustomCalendarIcon = (props: any) => (
  <img
    src={CalendarImage}
    alt="calendar"
    className="calendar-icon"
    {...props}
  />
);

export default CustomCalendarIcon;
