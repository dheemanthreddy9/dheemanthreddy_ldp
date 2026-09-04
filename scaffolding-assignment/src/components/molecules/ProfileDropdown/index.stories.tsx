import React, { useState } from 'react';
import type { Meta, StoryObj } from '@storybook/react';
import ProfileDropdown from './index';
import Avatar from '../../atoms/Avatar';
import { Box } from '@mui/material';
import { mockData } from '../../../data/mockData';

const ProfileDropdownWrapper = () => {
  const [anchorEl, setAnchorEl] = useState<HTMLElement | null>(null);

  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <Box className="header-container">
      <Box onClick={handleClick} className="header-user-trigger">
        <Avatar src={mockData.user.avatarUrl} alt={mockData.user.avatarAlt} />
      </Box>
      <ProfileDropdown anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={handleClose} />
    </Box>
  );
};

const meta = {
  title: 'Molecules/ProfileDropdown',
  component: ProfileDropdownWrapper,
  tags: ['autodocs'],
} satisfies Meta<typeof ProfileDropdownWrapper>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
