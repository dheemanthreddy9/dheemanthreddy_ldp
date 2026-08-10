import type { IconType } from "react-icons";

import Icon from "../../atoms/Icon";
import Typography from "../../atoms/Typography";

interface IconTextProps {
  icon: IconType;
  text: string;
}

const IconText = ({ icon, text }: IconTextProps) => {
  return (
    <div className="icon-text">
      <Icon icon={icon} size={20} />
      <Typography text={text} variant="body" />
    </div>
  );
};

export default IconText;
