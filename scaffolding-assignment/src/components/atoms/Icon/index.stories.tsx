import type { Meta, StoryObj } from '@storybook/react';
import { icons } from '../../../assets/icons';
import Icon from './index';

const meta = {
  title: 'Atoms/Icon',
  component: Icon,
  tags: ['autodocs'],
  argTypes: {
    src: { control: 'text' },
    alt: { control: 'text' },
  },
} satisfies Meta<typeof Icon>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Home: Story = {
  args: {
    src: icons.home,
    alt: 'Home',
    className: 'sidebar-nav-icon-image',
  },
};

export const Coin: Story = {
  args: {
    src: icons.coin,
    alt: 'Cash Acceleration',
    className: 'sidebar-nav-icon-image',
  },
};

export const Calendar: Story = {
  args: {
    src: icons.calendar,
    alt: 'Calendar',
    className: 'cash-acceleration-stat-icon-image',
  },
};

export const DocumentDownload: Story = {
  args: {
    src: icons.documentDownload,
    alt: 'Document download',
    className: 'cash-acceleration-stat-icon-image',
  },
};

export const PercentageSquare: Story = {
  args: {
    src: icons.percentageSquare,
    alt: 'Percentage',
    className: 'cash-acceleration-stat-icon-image',
  },
};

export const Receipt: Story = {
  args: {
    src: icons.receiptText,
    alt: 'Receipt',
    className: 'metric-card-icon-image',
  },
};

export const CircularProgress: Story = {
  args: {
    src: icons.circularProgress,
    alt: '0% progress',
    className: 'metric-card-progress-image',
  },
};
