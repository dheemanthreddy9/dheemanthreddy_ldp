import React from 'react';
import { CalendarTodayIcon } from '../../../assets/icons';
import { DATE_PICKER_PLACEHOLDER } from '../../../data/constants';

interface DatePickerProps {
  value?: string;
  placeholder?: string;
}

export const DatePicker: React.FC<DatePickerProps> = ({
  value = '',
  placeholder = DATE_PICKER_PLACEHOLDER,
}) => {
  return (
    <div className="date-picker-container">
      <CalendarTodayIcon fontSize="small" />
      <input type="text" value={value} readOnly placeholder={placeholder} />
    </div>
  );
};
