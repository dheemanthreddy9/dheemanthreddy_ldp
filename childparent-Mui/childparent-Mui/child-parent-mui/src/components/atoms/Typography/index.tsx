import MuiTypography from "@mui/material/Typography";

type TypographyProps = {
  text: string;
  variant?:
    | "h1"
    | "h2"
    | "h3"
    | "h4"
    | "h5"
    | "h6"
    | "subtitle1"
    | "subtitle2"
    | "body1"
    | "body2"
    | "caption"
    | "button"
    | "overline"
    | "inherit";
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