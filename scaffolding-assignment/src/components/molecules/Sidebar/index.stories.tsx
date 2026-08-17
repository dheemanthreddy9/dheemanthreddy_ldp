import { useState } from 'react';
import type { Meta, StoryObj } from '@storybook/react';
import Sidebar from './index';
import type { AppPage } from '../../../types/navigation';

const SidebarWrapper = ({ initialPage = 'home' }: { initialPage?: AppPage }) => {
  const [activePage, setActivePage] = useState<AppPage>(initialPage);

  return <Sidebar activePage={activePage} onNavigate={setActivePage} />;
};

const meta = {
  title: 'Molecules/Sidebar',
  component: SidebarWrapper,
  tags: ['autodocs'],
  parameters: {
    layout: 'fullscreen',
  },
} satisfies Meta<typeof SidebarWrapper>;

export default meta;
type Story = StoryObj<typeof meta>;

export const HomeActive: Story = {
  args: {
    initialPage: 'home',
  },
};

export const CashAccelerationActive: Story = {
  args: {
    initialPage: 'cashAcceleration',
  },
};
