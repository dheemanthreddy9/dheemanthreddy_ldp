import type { Meta, StoryObj } from '@storybook/react';
import Dashboard from './index';

const meta = {
  title: 'Molecules/Dashboard',
  component: Dashboard,
  tags: ['autodocs'],
  parameters: {
    layout: 'fullscreen',
  },
} satisfies Meta<typeof Dashboard>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Home: Story = {
  args: {
    initialPage: 'home',
  },
};

export const CashAcceleration: Story = {
  args: {
    initialPage: 'cashAcceleration',
  },
};
