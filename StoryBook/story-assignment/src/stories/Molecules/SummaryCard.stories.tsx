import type { Meta, StoryObj } from "@storybook/react-vite";

import SummaryCard from "../../components/molecules/SummaryCard/SummaryCard";

import { FiCalendar } from "react-icons/fi";

const meta: Meta<typeof SummaryCard> = {
  title: "Molecules/SummaryCard",
  component: SummaryCard,
};

export default meta;

type Story = StoryObj<typeof SummaryCard>;

export const Default: Story = {
  args: {
    icon: FiCalendar,
    title: "Term Cap",
    value: "12 Months",
  },
};