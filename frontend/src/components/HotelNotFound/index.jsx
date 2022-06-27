import React from "react";
import { Container } from "@mui/material";
import { SectionContainer, BoxText, Text, TextDescription } from "./styles";

/**
 * Este componente renderiza na tela uma mensagem de erro caso o status code seja 400
 * @returns o componente retorna uma imagem e uma mensagem de hotéis não encontrados.
 */

const HotelNotFound = () => {
  return (
    <SectionContainer>
      <Container maxWidth="xl">
        <BoxText>
          <Text>
            <span>Oops!</span>
          </Text>
          <Text>Sinto muito.</Text>
          <TextDescription>
            Não foi possível encontrar hotéis com as buscas que você deseja,
            tente novamente.
          </TextDescription>
        </BoxText>
      </Container>
    </SectionContainer>
  );
};

export default HotelNotFound;
