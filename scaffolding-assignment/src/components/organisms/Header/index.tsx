import React, { useState } from 'react';
import { Box } from '@mui/material';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import Typography from '../../atoms/Typography';
import Avatar from '../../atoms/Avatar';
import ProfileDropdown from '../../molecules/ProfileDropdown';
import { mockData } from '../../../data/mockData';

export interface HeaderProps {
  title?: string;
  subtitle?: string;
}

const Header: React.FC<HeaderProps> = ({ title, subtitle }) => {
  const { user } = mockData;
  const [anchorEl, setAnchorEl] = useState<HTMLElement | null>(null);

  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const open = Boolean(anchorEl);
  const isHomeHeader = !title;

  return (
    <Box className="header-container">
      <Box>
        <Typography
          variant="h5"
          className={isHomeHeader ? 'header-greeting' : 'header-page-title'}
        >
          {title ?? user.greeting}
        </Typography>
        <Typography
          variant="body2"
          className={isHomeHeader ? 'header-date' : 'header-page-subtitle'}
        >
          {subtitle ?? user.date}
        </Typography>
      </Box>

      <Box className="header-user-trigger" onClick={handleClick}>
        <Avatar src={user.avatarUrl} alt={user.avatarAlt} size={36} />
        <KeyboardArrowDownIcon className="header-arrow-icon" />
      </Box>

      <ProfileDropdown anchorEl={anchorEl} open={open} onClose={handleClose} />
    </Box>
  );
};

export default Header;
