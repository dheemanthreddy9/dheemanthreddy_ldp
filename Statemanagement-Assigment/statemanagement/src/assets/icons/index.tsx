import React from 'react';
import homeIconSvg from './home icon.svg';
import candidatesIconSvg from './candidates icon.svg';
import adverseIconSvg from './Adverse actions icons.svg';
import logsIconSvg from './Logs icon.svg';
import analyticsIconSvg from './Analytics icon.svg';
import accountIconSvg from './Account icon.svg';
import screeningsIconSvg from './screenings icon.svg';
import logoutIconSvg from './logout icon.svg';
import avatarSvg from './Avatar.svg';

import FileDownloadOutlinedIcon from '@mui/icons-material/FileDownloadOutlined';
import AddIcon from '@mui/icons-material/Add';
import SearchIcon from '@mui/icons-material/Search';
import FilterListIcon from '@mui/icons-material/FilterList';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import CloseIcon from '@mui/icons-material/Close';
import AttachmentIcon from '@mui/icons-material/Attachment';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';

interface IconProps {
  className?: string;
  fontSize?: 'small' | 'medium' | 'large';
}

const createSvgIcon = (svgSrc: string, alt: string) => {
  return ({ className = '', fontSize = 'medium' }: IconProps) => {
    return (
      <img
        src={svgSrc}
        alt={alt}
        className={`svg-icon svg-icon-${fontSize} ${className}`}
      />
    );
  };
};

export const GridViewIcon = createSvgIcon(homeIconSvg, 'Home');
export const GroupIcon = createSvgIcon(candidatesIconSvg, 'Candidates');
export const ReportProblemOutlinedIcon = createSvgIcon(adverseIconSvg, 'Adverse Actions');
export const DescriptionOutlinedIcon = createSvgIcon(logsIconSvg, 'Logs');
export const InsertChartOutlinedIcon = createSvgIcon(analyticsIconSvg, 'Analytics');
export const PersonOutlineOutlinedIcon = createSvgIcon(accountIconSvg, 'Account');
export const AssignmentOutlinedIcon = createSvgIcon(screeningsIconSvg, 'Screenings');
export const LogoutIcon = createSvgIcon(logoutIconSvg, 'Logout');
export const AvatarIcon = createSvgIcon(avatarSvg, 'Avatar');

export {
  FileDownloadOutlinedIcon,
  AddIcon,
  SearchIcon,
  FilterListIcon,
  MoreVertIcon,
  KeyboardArrowDownIcon,
  ChevronLeftIcon,
  ChevronRightIcon,
  ArrowBackIcon,
  CloseIcon,
  AttachmentIcon,
  CalendarTodayIcon,
};
