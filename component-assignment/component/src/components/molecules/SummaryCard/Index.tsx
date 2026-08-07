import type { IconType } from "react-icons";
import { FiInfo } from "react-icons/fi";

import Icon from "../../atoms/Icon/Icon";
import Typography from "../../atoms/Typography/Typography";

interface SummaryCardProps {
  icon: IconType;
  title: string;
  value: string;
}

const SummaryCard = ({
  icon,
  title,
  value,
}: SummaryCardProps) => {
  return (
    <div className="summary-card">
      <div className="summary-icon">
        <Icon icon={icon} size={24} className="icon-summary" />
      </div>

      <div className="summary-title">
        <Typography text={title} variant="caption" />
        <Icon icon={FiInfo} size={14} className="icon-muted" />
      </div>

      <Typography text={value} variant="subtitle" />
    </div>
  );
};

export default SummaryCard;
