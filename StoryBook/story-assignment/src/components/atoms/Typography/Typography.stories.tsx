import type { Meta, StoryObj } from "@storybook/react-vite";
import Typography from "../../components/atoms/Typography/Typography";

const meta: Meta<typeof Typography> = {
  title: "Atoms/Typography",
  component: Typography,
  tags: ["autodocs"],
};

export default meta;

type Story = StoryObj<typeof Typography>;

export const Title: Story = {
  args: {
    text: "Cash Acceleration",
    variant: "title",
  },
};

export const Subtitle: Story = {
  args: {
    text: "cash acceleration",
    variant: "subtitle",
  },
};

export const Body: Story = {
  args: {
    text: "My Contracts",
    variant: "body",
  },
};

export const Caption: Story = {
  args: {
    text: "Place to create new cash kicks",
    variant: "caption",
  },
};
