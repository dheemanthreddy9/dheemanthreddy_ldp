import type { Meta, StoryObj } from '@storybook/react';
import ContractsTable from './index';

const meta = {
  title: 'Organisms/ContractsTable',
  component: ContractsTable,
  tags: ['autodocs'],
} satisfies Meta<typeof ContractsTable>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
