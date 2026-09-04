import type { Meta, StoryObj } from '@storybook/react';
import CashAccelerationPage from './index';

const meta = {
  title: 'Pages/CashAccelerationPage',
  component: CashAccelerationPage,
  tags: ['autodocs'],
} satisfies Meta<typeof CashAccelerationPage>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
