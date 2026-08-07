import MuiButton from "@mui/material/Button";
import React from "react";

type ButtonProps = {
  text: string;
  className?: string;
};

const Button: React.FC<ButtonProps> = ({ text, className }) => {
  return (
    <MuiButton variant="contained" className={className}>
      {text}
    </MuiButton>
  );
};

export default Button;