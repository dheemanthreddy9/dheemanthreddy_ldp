import React from 'react';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';

interface DatePickerProps {
  value?: string;
}

export const DatePicker: React.FC<DatePickerProps> = ({ value = '' }) => {
  return (
    <div className="date-picker-container">
      <CalendarTodayIcon fontSize="small" />
      <input type="text" value={value} readOnly placeholder="Select date" />
    </div>
  );
};
