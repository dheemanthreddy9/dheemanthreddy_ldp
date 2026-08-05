import type { Meta, StoryObj } from "@storybook/react-vite";

import SidebarItem from "../../components/molecules/SideBar/SideBar";

import { FiHome } from "react-icons/fi";

const meta: Meta<typeof SidebarItem> = {
  title: "Molecules/SidebarItem",
  component: SidebarItem,
};

export default meta;

type Story = StoryObj<typeof SidebarItem>;

export const Default: Story = {
  args: {
    icon: FiHome,
    text: "Home",
  },
};

export const Active: Story = {
  args: {
    icon: FiHome,
    text: "Cash Acceleration",
    active: true,
  },
};