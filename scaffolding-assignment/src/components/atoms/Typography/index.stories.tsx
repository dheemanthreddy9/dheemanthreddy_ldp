import type { Meta, StoryObj } from '@storybook/react';
import Typography from './index';

const meta = {
  title: 'Atoms/Typography',
  component: Typography,
  tags: ['autodocs'],
  argTypes: {
    variant: {
      control: 'select',
      options: ['h4', 'h5', 'h6', 'subtitle1', 'body1', 'body2', 'caption'],
    },
    children: { control: 'text' },
  },
} satisfies Meta<typeof Typography>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Heading: Story = {
  args: {
    variant: 'h5',
    children: 'Good afternoon 👋',
  },
};

export const Subtitle: Story = {
  args: {
    variant: 'subtitle1',
    children: 'Kane Cooper',
  },
};

export const Body: Story = {
  args: {
    variant: 'body2',
    children: 'You have upto $709,546.00 available for a new cash advance',
  },
};

export const Caption: Story = {
  args: {
    variant: 'caption',
    children: '31 day(s) from now',
  },
};
