import type { Meta, StoryObj } from "@storybook/react-vite";
import Icon from "../../components/atoms/Icon/Icon";

import { FiHome } from "react-icons/fi";

const meta: Meta<typeof Icon> = {
  title: "Atoms/Icon",
  component: Icon,
  tags: ["autodocs"],
};

export default meta;

type Story = StoryObj<typeof Icon>;

export const Home: Story = {
  args: {
    icon: FiHome,
    size: 22,
    color: "#6C5CE7",
  },
};