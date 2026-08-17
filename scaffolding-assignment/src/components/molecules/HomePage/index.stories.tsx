import type { Meta, StoryObj } from '@storybook/react';
import HomePage from './index';

const meta = {
  title: 'Molecules/HomePage',
  component: HomePage,
  tags: ['autodocs'],
  parameters: {
    layout: 'fullscreen',
  },
} satisfies Meta<typeof HomePage>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
