import type { Meta, StoryObj } from '@storybook/react';
import Avatar from './index';
import { mockData } from '../../../data/mockData';

const meta = {
  title: 'Atoms/Avatar',
  component: Avatar,
  tags: ['autodocs'],
  argTypes: {
    size: { control: 'number' },
    src: { control: 'text' },
    alt: { control: 'text' },
  },
} satisfies Meta<typeof Avatar>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    src: mockData.user.avatarUrl,
    alt: mockData.user.avatarAlt,
    size: 40,
  },
};

export const Large: Story = {
  args: {
    src: mockData.user.avatarUrl,
    alt: mockData.user.avatarAlt,
    size: 64,
  },
};
