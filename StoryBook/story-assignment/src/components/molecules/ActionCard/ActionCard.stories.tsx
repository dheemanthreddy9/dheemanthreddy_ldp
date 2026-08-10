import type { Meta, StoryObj } from "@storybook/react-vite";

import ActionCard from "../../components/molecules/ActionCard/Actioncard";

const meta: Meta<typeof ActionCard> = {
  title: "Molecules/ActionCard",
  component: ActionCard,
};

export default meta;

type Story = StoryObj<typeof ActionCard>;

export const Default: Story = {
  args: {
    title: "Launch a new Cash Kick",
    description: "You have up to $709,546 available.",
    buttonText: "New Cash Kick",
  },
};
