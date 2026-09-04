import type { Meta, StoryObj } from '@storybook/react';
import homeIcon from '../../../assets/home.svg';
import coinIcon from '../../../assets/coin.svg';
import calendarIcon from '../../../assets/calendar.svg';
import documentDownloadIcon from '../../../assets/document-download.svg';
import percentageSquareIcon from '../../../assets/percentage-square.svg';
import receiptTextIcon from '../../../assets/receipt-text.svg';
import circularProgressIcon from '../../../assets/circular-progress.svg';
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
    src: homeIcon,
    alt: 'Home',
    className: 'sidebar-nav-icon-image',
  },
};

export const Coin: Story = {
  args: {
    src: coinIcon,
    alt: 'Cash Acceleration',
    className: 'sidebar-nav-icon-image',
  },
};

export const Calendar: Story = {
  args: {
    src: calendarIcon,
    alt: 'Calendar',
    className: 'cash-acceleration-stat-icon-image',
  },
};

export const DocumentDownload: Story = {
  args: {
    src: documentDownloadIcon,
    alt: 'Document download',
    className: 'cash-acceleration-stat-icon-image',
  },
};

export const PercentageSquare: Story = {
  args: {
    src: percentageSquareIcon,
    alt: 'Percentage',
    className: 'cash-acceleration-stat-icon-image',
  },
};

export const Receipt: Story = {
  args: {
    src: receiptTextIcon,
    alt: 'Receipt',
    className: 'metric-card-icon-image',
  },
};

export const CircularProgress: Story = {
  args: {
    src: circularProgressIcon,
    alt: '0% progress',
    className: 'metric-card-progress-image',
  },
};
