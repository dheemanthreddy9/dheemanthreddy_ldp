import type { IconType } from "react-icons";

import Icon from "../../atoms/Icon/Icon";
import Typography from "../../atoms/Typography/Typography";

interface SidebarItemProps {
  icon: IconType;
  text: string;
  active?: boolean;
}

const SidebarItem = ({
  icon,
  text,
  active = false,
}: SidebarItemProps) => {
  return (
    <div className={`sidebar-item ${active ? "active" : ""}`}>
      <Icon icon={icon} size={18} color="#FFFFFF" />

      <Typography
        text={text}
        variant="body"
      />
    </div>
  );
};

export default SidebarItem;