import type { Meta, StoryObj } from '@storybook/react';
import CashAccelerationStats from './index';

const meta = {
  title: 'Organisms/CashAccelerationStats',
  component: CashAccelerationStats,
  tags: ['autodocs'],
} satisfies Meta<typeof CashAccelerationStats>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
