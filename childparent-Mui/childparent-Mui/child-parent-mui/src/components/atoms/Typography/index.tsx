import MuiTypography from "@mui/material/Typography";

type TypographyProps = {
  text: string;
  variant?: "subtitle1" | "body2";
  className?: string;
};

const Typography = ({
  text,
  variant = "body2",
  className,
}: TypographyProps) => {
  return (
    <MuiTypography variant={variant} className={className}>
      {text}
    </MuiTypography>
  );
};

export default Typography;