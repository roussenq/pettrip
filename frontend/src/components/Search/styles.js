import { Button, styled, Box, Autocomplete } from "@mui/material";

export const BoxForm = styled(Box)`
  display: flex;
  max-width: 600px;
  justify-content: space-between;
  font-family: "Montserrat Alternates", sans-serif;

  @media (max-width: 630px) {
    flex-direction: column;
  }
`;

export const BoxSearch = styled(Box)`
  display: flex;

  @media (max-width: 480px) {
    flex-direction: column;
    align-items: center;
  }
`;

export const InputAutocomplete = styled(Autocomplete)`
  width: 380px;
  padding: 5px 4px 4px 40px;

  & input {
    width: 200px;
    font-size: 15px;
    font-weight: 600;
  }

  & svg {
    width: 30px;
    margin-bottom: 5x;
  }

  @media (max-width: 480px) {
    padding: 0px 4px;
    width: 180px;

    & input {
      width: 150px;
      padding-left: 10px;
      font-size: 12px;
    }

    & svg {
      width: 30px;
      padding: 4px;
      margin-bottom: 5x;
    }
  }

  @media (max-width: 270px) {
    padding: 0px 4px;
    width: 150px;
  }
`;

export const ButtonSearch = styled(Button)`
  background-color: #f47920;
  color: #ffffff;
  font-size: 8px;
  height: 39px;
  width: 79px;
  border-radius: 0px 4px 4px 0px;

  @media (max-width: 480px) {
    border-radius: 4px;
    margin-top: 18px;
    width: 69px;
    height: 30px;
  }

  :hover {
    background-color: #f47920;
  }
`;
