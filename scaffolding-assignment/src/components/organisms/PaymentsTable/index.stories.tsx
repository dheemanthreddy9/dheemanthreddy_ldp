import type { Meta, StoryObj } from '@storybook/react';
import PaymentsTable from './index';

const meta = {
  title: 'Organisms/PaymentsTable',
  component: PaymentsTable,
  tags: ['autodocs'],
} satisfies Meta<typeof PaymentsTable>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
