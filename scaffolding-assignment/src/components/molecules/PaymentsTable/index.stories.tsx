import type { Meta, StoryObj } from '@storybook/react';
import { Box } from '@mui/material';
import PaymentsTable from './index';

const meta = {
  title: 'Molecules/PaymentsTable',
  component: PaymentsTable,
  tags: ['autodocs'],
  decorators: [
    (Story) => (
      <Box sx={{ p: 2, width: '100%', maxWidth: 1200 }}>
        <Story />
      </Box>
    ),
  ],
} satisfies Meta<typeof PaymentsTable>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
