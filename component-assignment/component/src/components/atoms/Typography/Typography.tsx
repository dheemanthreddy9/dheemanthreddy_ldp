interface TypographyProps {
  text: string;
  variant?: "title" | "subtitle" | "body" | "caption";
}

const Typography = ({
  text,
  variant = "body",
}: TypographyProps) => {
  return <p className={variant}>{text}</p>;
};

export default Typography;