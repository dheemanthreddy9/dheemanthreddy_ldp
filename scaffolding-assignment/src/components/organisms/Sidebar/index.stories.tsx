import type { Meta, StoryObj } from '@storybook/react';
import Sidebar from './index';

const meta = {
  title: 'Organisms/Sidebar',
  component: Sidebar,
  tags: ['autodocs'],
  parameters: {
    layout: 'fullscreen',
  },
} satisfies Meta<typeof Sidebar>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    activePage: 'home',
    onNavigate: () => {},
  },
};
