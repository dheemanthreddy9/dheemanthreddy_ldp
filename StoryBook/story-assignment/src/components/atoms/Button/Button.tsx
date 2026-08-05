interface ButtonProps {
  text: string;
  variant?: "primary" | "secondary" | "link";
  disabled?: boolean;
  onClick?: () => void;
}

const Button = ({
  text,
  variant = "primary",
  disabled = false,
  onClick,
}: ButtonProps) => {
  return (
    <button
      className={`button ${variant}`}
      disabled={disabled}
      onClick={onClick}
    >
      {text}
    </button>
  );
};

export default Button;