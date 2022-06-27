import { Box, styled, Typography } from "@mui/material";
import dogError from "../../assets/images/dogError.jpg";

export const SectionContainer = styled(Box)`
  background: url(${dogError}) no-repeat center;
  background-size: cover;
  max-width: 100%;
  height: 540px;
  border-radius: 9px;

  @media (max-width: 690px) {
    margin-left: 0px;
  }
`;

export const BoxText = styled(Box)`
  font-family: "Montserrat Alternates", sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #382818;
  padding: 40px 166px;

  @media (max-width: 915px) {
    height: 438px;
    padding: 20px;
  }

  @media (max-width: 419px) {
    height: 438px;
    padding: 20px;
  }
`;

export const Text = styled(Typography)`
  margin-top: 20px;
  font-size: 16px;
  margin-top: 4px;

  span {
    color: #f47920;
  }

  @media (max-width: 690px) {
    font-size: 14px;

    span {
      font-size: 14px;
    }
  }
`;
export const TextDescription = styled(Typography)`
  font-size: 15px;
  text-align: center;
  margin-top: 4px;
  line-height: 22px;

  @media (max-width: 690px) {
    font-size: 14px;
  }
`;
