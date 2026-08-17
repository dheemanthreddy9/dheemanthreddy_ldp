import type { Meta, StoryObj } from '@storybook/react';
import { Box } from '@mui/material';
import CashAccelerationStats from './index';

const meta = {
  title: 'Molecules/CashAccelerationStats',
  component: CashAccelerationStats,
  tags: ['autodocs'],
  decorators: [
    (Story) => (
      <Box sx={{ p: 2, maxWidth: 900 }}>
        <Story />
      </Box>
    ),
  ],
} satisfies Meta<typeof CashAccelerationStats>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
