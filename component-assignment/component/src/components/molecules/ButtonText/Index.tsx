import Button from "../../atoms/Button/Button";
import Typography from "../../atoms/Typography/Typography";

interface ButtonTextProps {
  title: string;
  description: string;
  buttonText: string;
}

const ButtonText = ({
  title,
  description,
  buttonText,
}: ButtonTextProps) => {
  return (
    <div className="button-text">
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

export default ButtonText;
