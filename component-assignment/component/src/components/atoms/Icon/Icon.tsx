import type { IconType } from "react-icons";

interface IconProps {
  icon: IconType;
  size?: number;
  color?: string;
  className?: string;
}

const Icon = ({
  icon: IconComponent,
  size = 24,
  color,
  className,
}: IconProps) => {
  const props: any = { size };
  if (color) props.color = color;
  if (className) props.className = className;

  return <IconComponent {...props} />;
};

export default Icon;