import React from 'react';
import { SearchIcon } from '../../../assets/icons';
import { SEARCH_PLACEHOLDER } from '../../../data/constants';

interface SearchInputProps {
  value?: string;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
  placeholder?: string;
}

export const SearchInput: React.FC<SearchInputProps> = ({
  value = '',
  onChange,
  placeholder = SEARCH_PLACEHOLDER,
}) => {
  return (
    <div className="search-input-wrapper">
      <SearchIcon className="search-icon" fontSize="small" />
      <input
        type="text"
        placeholder={placeholder}
        className="search-input"
        value={value}
        onChange={onChange}
        readOnly={!onChange}
      />
    </div>
  );
};
