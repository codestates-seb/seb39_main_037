import { Button } from "@mui/material";
import React from "react";

interface IButtonProps {
  variants?: string;
  link?: string;
  title?: string;
  icon?: any;
  widthStyle?: string;
  paddingStyle?: string;
  buttonColor?: string;
  color?: string;
  disabled?: boolean;
  onClick?: () => void;
  //   fontSize?:string;
}
export const SquareButtonForm = ({
  variants,
  link,
  title,
  icon,
  widthStyle,
  paddingStyle = "5px 20px",
  buttonColor = " #fec20b",
  color = "white",
  disabled,
  //   fontSize,
  onClick,
}: IButtonProps) => {
  const ButtonStyle = {
    padding: paddingStyle,
    width: widthStyle,
    backgroundColor: buttonColor,
    color,
    // textTransform: "none",
    // fontSize,
  };
  return (
    <Button
      disabled={disabled && disabled}
      variant={variants ? "text" : "contained"}
      href={link && link}
      type="submit"
      style={ButtonStyle}
      onClick={onClick}
      startIcon={icon}
    >
      {title}
    </Button>
  );
};
