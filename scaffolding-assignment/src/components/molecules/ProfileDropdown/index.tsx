import React from 'react';
import { Box, Menu, MenuItem, ListItemIcon, Divider } from '@mui/material';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import SettingsOutlinedIcon from '@mui/icons-material/SettingsOutlined';
import LogoutOutlinedIcon from '@mui/icons-material/LogoutOutlined';
import Typography from '../../atoms/Typography';
import Avatar from '../../atoms/Avatar';
import Button from '../../atoms/Button';
import { mockData } from '../../../data/mockData';

export interface ProfileDropdownProps {
  anchorEl: HTMLElement | null;
  open: boolean;
  onClose: () => void;
}

export const ProfileDropdown: React.FC<ProfileDropdownProps> = ({ anchorEl, open, onClose }) => {
  const { user, profileMenu } = mockData;

  return (
    <Menu
      anchorEl={anchorEl}
      open={open}
      onClose={onClose}
      onClick={onClose}
      transformOrigin={{ horizontal: 'right', vertical: 'top' }}
      anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
      slotProps={{
        paper: {
          elevation: 0,
          className: 'profile-menu-paper',
        },
      }}
    >
      <Box className="profile-menu-header">
        <Avatar src={user.avatarUrl} alt={user.avatarAlt} size={44} />
        <Box>
          <Typography variant="subtitle1" className="profile-menu-user-name">
            {user.name}
          </Typography>
          <Button variant="text" className="profile-menu-edit-btn">
            {profileMenu.editProfile}
          </Button>
        </Box>
      </Box>

      <Divider className="profile-menu-divider" />

      <MenuItem onClick={onClose} className="profile-menu-item">
        <Typography variant="body2" className="profile-menu-item-text">
          {profileMenu.manageSubscriptions}
        </Typography>
      </MenuItem>

      <MenuItem onClick={onClose} className="profile-menu-item">
        <Typography variant="body2" className="profile-menu-item-text">
          {profileMenu.help}
        </Typography>
      </MenuItem>

      <MenuItem onClick={onClose} className="profile-menu-item">
        <ListItemIcon className="profile-menu-item-icon">
          <SettingsOutlinedIcon fontSize="small" />
        </ListItemIcon>
        <Typography variant="body2" className="profile-menu-item-text">
          {profileMenu.settings}
        </Typography>
      </MenuItem>

      <Divider className="profile-menu-divider" />

      <MenuItem onClick={onClose} className="profile-menu-logout-item">
        <ListItemIcon className="profile-menu-logout-icon">
          <LogoutOutlinedIcon fontSize="small" />
        </ListItemIcon>
        <Typography variant="body2" className="profile-menu-logout-text">
          {profileMenu.logout}
        </Typography>
      </MenuItem>
    </Menu>
  );
};

export default ProfileDropdown;
