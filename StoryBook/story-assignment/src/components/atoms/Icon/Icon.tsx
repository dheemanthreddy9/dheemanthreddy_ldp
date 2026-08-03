import type { IconType } from "react-icons";

interface IconProps {
  icon: IconType;
  size?: number;
  color?: string;
}

const Icon = ({
  icon: IconComponent,
  size = 24,
  color = "#FFFFFF",
}: IconProps) => {
  return <IconComponent size={size} color={color} />;
};

export default Icon;