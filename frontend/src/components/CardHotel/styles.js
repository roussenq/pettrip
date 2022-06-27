import { Box, styled, Typography } from "@mui/material";

export const CardContainer = styled(Box)`
  background-color: #ffffff;
  width: 100%;
  margin-left: 30px;

  ul {
    list-style: none;
  }

  @media (max-width: 690px) {
    margin-left: 0px;
  }
`;

export const WrapLoading = styled(Box)`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 450px;
`;

export const TextCity = styled(Typography)`
  font-family: "Montserrat Alternates", sans-serif;
  font-size: 16px;
  font-weight: 600;
  padding: 8px 0px;

  span {
    color: #28a0a7;
    font-size: 18px;
  }

  @media (max-width: 950px) {
    font-size: 12px;

    span {
      font-size: 14px;
    }
  }

  @media (max-width: 690px) {
    font-size: 12px;
    margin: 10px 0 20px 0;

    span {
      font-size: 13px;
    }
  }
`;
