import type { IconType } from "react-icons";

interface IconProps {
  icon: IconType;
  size?: number;
  color?: string;
  className?: string;
}

const Icon = ({
  icon: IconComponent,
  size,
  color,
  className,
}: IconProps) => {
  return (
    <IconComponent
      className={className}
      size={size}
      color={color}
    />
  );
};

export default Icon;