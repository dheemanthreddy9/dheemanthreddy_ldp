import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    primary: {
      main: "#95AAFF",
    },
  },

  typography: {
    subtitle1: {
      fontFamily: "Inter, Arial, sans-serif",
      fontSize: "16px",
      fontWeight: 500,
      lineHeight: "24px",
      letterSpacing: "0%",
      color: "#2C2C2E",
    },

    body2: {
      fontFamily: "Inter, Arial, sans-serif",
      fontSize: "14px",
      fontWeight: 400,
      lineHeight: "20px",
      letterSpacing: "0%",
      color: "#696A6E",
    },

    button: {
      fontFamily: "Inter, Arial, sans-serif",
      fontSize: "14px",
      fontWeight: 500,
      lineHeight: "20px",
      letterSpacing: "0%",
      textTransform: "none",
      color: "#FFFFFF",
    },
  },

  components: {
    MuiPaper: {
      styleOverrides: {
        root: {
          backgroundColor: "#FFFFFF",
          borderRadius: "6px",
        },
      },
    },

    MuiDivider: {
      styleOverrides: {
        root: {
          borderColor: "#E5E7ED",
          borderBottomWidth: "1px",
        },
      },
    },

    MuiTextField: {
      styleOverrides: {
        root: {
          "& .MuiOutlinedInput-root": {
            height: "36px",
            borderRadius: "4px",
            backgroundColor: "#FFFFFF",

            "& fieldset": {
              borderColor: "#E5E7ED",
            },

            "&:hover fieldset": {
              borderColor: "#E5E7ED",
            },

            "&.Mui-focused fieldset": {
              borderColor: "#95AAFF",
              borderWidth: "1px",
            },
          },

          "& .MuiInputBase-input": {
            fontFamily: "Inter, Arial, sans-serif",
            fontSize: "14px",
            fontWeight: 400,
            lineHeight: "20px",
            color: "#696A6E",
            padding: "6px 12px",
          },

          "& .MuiInputLabel-root": {
            fontFamily: "Inter, Arial, sans-serif",
            fontSize: "14px",
            fontWeight: 400,
            lineHeight: "20px",
            color: "#696A6E",
          },

          "& .MuiSvgIcon-root": {
            color: "#95AAFF",
            fontSize: "20px",
          },
        },
      },
    },

    MuiButton: {
      styleOverrides: {
        root: {
          width: "126px",
          height: "36px",
          padding: "8px 16px",
          borderRadius: "6px",
          backgroundColor: "#95AAFF",
          color: "#FFFFFF",
          textTransform: "none",
          fontFamily: "Inter, Arial, sans-serif",
          fontSize: "14px",
          fontWeight: 500,
          lineHeight: "20px",

          "&:hover": {
            backgroundColor: "#7D95FF",
          },
        },
      },
    },
  },
});

export default theme;