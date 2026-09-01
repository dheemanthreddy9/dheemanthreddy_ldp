import React from 'react';
import { Box, List, ListItem, ListItemButton, ListItemIcon, ListItemText } from '@mui/material';
import Typography from '../../atoms/Typography';
import Icon from '../../atoms/Icon';
import { icons } from '../../../assets/icons';
import { mockData } from '../../../data/mockData';
import type { AppPage } from '../../../types/navigation';

export interface SidebarProps {
  activePage: AppPage;
  onNavigate: (page: AppPage) => void;
}

const Sidebar: React.FC<SidebarProps> = ({ activePage, onNavigate }) => {
  const { sidebar, brand } = mockData;

  return (
    <Box className="sidebar-container">
      <Box className="sidebar-header">
        <Icon src={icons.logo} alt={brand.name} className="sidebar-logo-image" />
        <Typography variant="h6" className="sidebar-brand-name">{brand.name}</Typography>
      </Box>

      <List className="sidebar-nav-list">
        <ListItem disablePadding className="sidebar-nav-item">
          <ListItemButton
            selected={activePage === 'home'}
            className={activePage === 'home' ? 'sidebar-nav-button-active' : 'sidebar-nav-button'}
            onClick={() => onNavigate('home')}
          >
            <ListItemIcon className={activePage === 'home' ? 'sidebar-nav-icon-active' : 'sidebar-nav-icon'}>
              <Icon src={icons.home} alt="" className="sidebar-nav-icon-image" />
            </ListItemIcon>
            <ListItemText
              primary={
                <Typography
                  variant="body2"
                  className={activePage === 'home' ? 'sidebar-nav-text-active' : 'sidebar-nav-text'}
                >
                  {sidebar.home}
                </Typography>
              }
            />
          </ListItemButton>
        </ListItem>

        <ListItem disablePadding>
          <ListItemButton
            selected={activePage === 'cashAcceleration'}
            className={activePage === 'cashAcceleration' ? 'sidebar-nav-button-active' : 'sidebar-nav-button'}
            onClick={() => onNavigate('cashAcceleration')}
          >
            <ListItemIcon className={activePage === 'cashAcceleration' ? 'sidebar-nav-icon-active' : 'sidebar-nav-icon'}>
              <Icon src={icons.coin} alt="" className="sidebar-nav-icon-image" />
            </ListItemIcon>
            <ListItemText
              primary={
                <Typography
                  variant="body2"
                  className={activePage === 'cashAcceleration' ? 'sidebar-nav-text-active' : 'sidebar-nav-text'}
                >
                  {sidebar.cashAcceleration}
                </Typography>
              }
            />
          </ListItemButton>
        </ListItem>
      </List>

      <Box className="sidebar-footer">
        <ListItem disablePadding>
          <ListItemButton className="sidebar-nav-button">
            <ListItemIcon className="sidebar-nav-icon">
              <Icon src={icons.flash} alt="" className="sidebar-nav-icon-image" />
            </ListItemIcon>
            <ListItemText
              primary={<Typography variant="body2" className="sidebar-nav-text">{sidebar.watchHowTo}</Typography>}
            />
          </ListItemButton>
        </ListItem>
      </Box>
    </Box>
  );
};

export default Sidebar;
