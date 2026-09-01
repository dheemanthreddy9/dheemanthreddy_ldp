import React from 'react';
import {
  GridViewIcon,
  GroupIcon,
  ReportProblemOutlinedIcon,
  DescriptionOutlinedIcon,
  InsertChartOutlinedIcon,
  PersonOutlineOutlinedIcon,
  AssignmentOutlinedIcon,
  LogoutIcon,
} from '../../../assets/icons';
import { Typography } from '../../atoms/Typography';
import { Avatar } from '../../atoms/Avatar';
import { BRAND_NAME, defaultUser, SIDEBAR_NAV_ITEMS } from '../../../data/constants';

interface SidebarProps {
  activePage?: string;
  onNavigate?: (page: string) => void;
}

const iconMap: Record<string, React.ReactNode> = {
  GridView: <GridViewIcon fontSize="small" />,
  Group: <GroupIcon fontSize="small" />,
  ReportProblem: <ReportProblemOutlinedIcon fontSize="small" />,
  Description: <DescriptionOutlinedIcon fontSize="small" />,
  InsertChart: <InsertChartOutlinedIcon fontSize="small" />,
  Person: <PersonOutlineOutlinedIcon fontSize="small" />,
  Assignment: <AssignmentOutlinedIcon fontSize="small" />,
};

export const Sidebar: React.FC<SidebarProps> = ({ activePage = 'page1', onNavigate }) => {
  const menuItems = SIDEBAR_NAV_ITEMS.map((item) => ({
    id: item.page,
    label: item.label,
    icon: iconMap[item.iconKey],
  }));

  return (
    <aside className="sidebar-container">
      <div className="sidebar-brand">
        <Typography variant="h6" className="brand-title">
          {BRAND_NAME}
        </Typography>
      </div>

      <nav className="sidebar-nav">
        {menuItems.map((item) => {
          const isActive = activePage === item.id || (activePage === 'page2' && item.id === 'page1');
          return (
            <div
              key={item.id}
              className={`nav-item ${isActive ? 'nav-item-active' : ''}`}
              onClick={() => onNavigate?.(item.id)}
              role="button"
              tabIndex={0}
            >
              <span className="nav-icon">{item.icon}</span>
              <span className="nav-label">{item.label}</span>
            </div>
          );
        })}
      </nav>

      <div className="sidebar-user">
        <div className="user-avatar">
          <Avatar src={defaultUser.avatarUrl} alt={defaultUser.name} />
        </div>
        <div className="user-info">
          <Typography variant="subtitle2" className="user-name">
            {defaultUser.name}
          </Typography>
          <Typography variant="caption" className="user-sub text-muted">
            {defaultUser.email}
          </Typography>
        </div>
        <button className="logout-button" type="button">
          <LogoutIcon fontSize="small" />
        </button>
      </div>
    </aside>
  );
};
