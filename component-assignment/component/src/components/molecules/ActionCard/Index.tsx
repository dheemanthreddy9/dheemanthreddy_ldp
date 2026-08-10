import Button from "../../atoms/Button";
import Typography from "../../atoms/Typography";

interface ActionCardProps {
  title: string;
  description: string;
  buttonText: string;
}

const ActionCard = ({
  title,
  description,
  buttonText,
}: ActionCardProps) => {
  return (
    <div className="action-card">
      <Typography
        text={title}
        variant="subtitle"
      />

      <Typography
        text={description}
        variant="caption"
      />

      <Button text={buttonText} />
    </div>
  );
};

export default ActionCard;
