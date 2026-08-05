import type { Meta, StoryObj } from "@storybook/react-vite";

import ProfileMenu from "../../components/molecules/ProfileMenu/ProfileMenu";

import avatar from "../../assets/images/avatar.png";

const meta: Meta<typeof ProfileMenu> = {
  title: "Molecules/ProfileMenu",
  component: ProfileMenu,
};

export default meta;

type Story = StoryObj<typeof ProfileMenu>;

export const Default: Story = {
  args: {
    image: avatar,
    name: "Jessie Wilson",
  },
};