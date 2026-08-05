import type { Meta, StoryObj } from "@storybook/react-vite";
import Avatar from "../../components/atoms/Avatar/Avatar";

import avatar from "../../assets/images/avatar.png";

const meta: Meta<typeof Avatar> = {
  title: "Atoms/Avatar",
  component: Avatar,
  tags: ["autodocs"],
};

export default meta;

type Story = StoryObj<typeof Avatar>;

export const Default: Story = {
  args: {
    src: avatar,
  },
};