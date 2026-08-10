import type { Meta, StoryObj } from "@storybook/react-vite";
import Button from "../../components/atoms/Button/Button";

const meta = {
  title: "Atoms/Button",
  component: Button,
  tags: ["autodocs"],

  argTypes: {
    variant: {
      control: "select",
      options: ["primary", "secondary", "link"],
    },

    disabled: {
      control: "boolean",
    },

    onClick: {
      action: "clicked",
    },
  },
} satisfies Meta<typeof Button>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Primary: Story = {
  args: {
    text: "New Cash Kick",
    variant: "primary",
  },
};

export const Secondary: Story = {
  args: {
    text: "My Contracts",
    variant: "secondary",
  },
};

export const Link: Story = {
  args: {
    text: "Learn More",
    variant: "link",
  },
};

export const Disabled: Story = {
  args: {
    text: "Cash Acceleration",
    variant: "primary",
    disabled: true,
  },
};
