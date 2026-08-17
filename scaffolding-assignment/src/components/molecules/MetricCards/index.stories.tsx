import React from 'react';
import type { Meta, StoryObj } from '@storybook/react';
import { Box } from '@mui/material';
import { TermCard, OutstandingCard, PromoCard } from './index';

const CardWrapper = ({ children }: { children: React.ReactNode }) => (
  <Box sx={{ maxWidth: 380, p: 2 }}>
    {children}
  </Box>
);

const meta = {
  title: 'Molecules/MetricCards',
  tags: ['autodocs'],
} satisfies Meta;

export default meta;
type Story = StoryObj<typeof meta>;

export const Term: Story = {
  render: () => (
    <CardWrapper>
      <TermCard />
    </CardWrapper>
  ),
};

export const Outstanding: Story = {
  render: () => (
    <CardWrapper>
      <OutstandingCard />
    </CardWrapper>
  ),
};

export const Promo: Story = {
  render: () => (
    <CardWrapper>
      <PromoCard />
    </CardWrapper>
  ),
};

export const AllCards: Story = {
  render: () => (
    <Box className="dashboard-metrics-grid" sx={{ p: 2, maxWidth: 1200 }}>
      <TermCard />
      <OutstandingCard />
      <PromoCard />
    </Box>
  ),
};
