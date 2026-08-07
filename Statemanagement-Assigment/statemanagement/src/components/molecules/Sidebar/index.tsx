import React from 'react';
import GridViewIcon from '@mui/icons-material/GridView';
import GroupIcon from '@mui/icons-material/Group';
import ReportProblemOutlinedIcon from '@mui/icons-material/ReportProblemOutlined';
import DescriptionOutlinedIcon from '@mui/icons-material/DescriptionOutlined';
import InsertChartOutlinedIcon from '@mui/icons-material/InsertChartOutlined';
import PersonOutlineOutlinedIcon from '@mui/icons-material/PersonOutlineOutlined';
import AssignmentOutlinedIcon from '@mui/icons-material/AssignmentOutlined';
import LogoutIcon from '@mui/icons-material/Logout';
import { Typography } from '../../atoms/Typography';

export const Sidebar: React.FC = () => {
  const menuItems = [
    { label: 'Home', icon: <GridViewIcon fontSize="small" />, active: false },
    { label: 'Candidates', icon: <GroupIcon fontSize="small" />, active: true },
    { label: 'Adverse Actions', icon: <ReportProblemOutlinedIcon fontSize="small" />, active: false },
    { label: 'Logs', icon: <DescriptionOutlinedIcon fontSize="small" />, active: false },
    { label: 'Analytics', icon: <InsertChartOutlinedIcon fontSize="small" />, active: false },
    { label: 'Account', icon: <PersonOutlineOutlinedIcon fontSize="small" />, active: false },
    { label: 'Screenings', icon: <AssignmentOutlinedIcon fontSize="small" />, active: false },
  ];

  return (
    <aside className="sidebar-container">
      <div className="sidebar-brand">
        <Typography variant="h6" className="brand-title">
          RECRUIT
        </Typography>
      </div>

      <nav className="sidebar-nav">
        {menuItems.map((item) => (
          <div key={item.label} className={`nav-item ${item.active ? 'nav-item-active' : ''}`}>
            <span className="nav-icon">{item.icon}</span>
            <span className="nav-label">{item.label}</span>
          </div>
        ))}
      </nav>

      <div className="sidebar-user">
        <div className="user-avatar">
          <img
            src="https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&w=100&q=80"
            alt="James Rodriguez"
            className="avatar-img"
          />
        </div>
        <div className="user-info">
          <Typography variant="subtitle2" className="user-name">
            James Rodriguez
          </Typography>
          <Typography variant="caption" className="user-sub text-muted">
            James.co
          </Typography>
        </div>
        <button className="logout-button" type="button">
          <LogoutIcon fontSize="small" />
        </button>
      </div>
    </aside>
  );
};
