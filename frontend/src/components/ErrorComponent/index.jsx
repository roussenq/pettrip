import React from "react";
import { Container } from "@mui/material";
import { SectionContainer, BoxText, Text, TextDescription } from "./styles";

/**
 * Este componente renderiza na tela uma mensagem de erro caso o status code seja != 400
 * @returns o componente retorna uma imagem e uma mensagem Tente Novamente.
 */

const ErrorComponent = () => {
  return (
    <SectionContainer>
      <Container maxWidth="xl">
        <BoxText>
          <Text>Desculpe.</Text>
          <TextDescription>
            Não foi possível carregar a página nesse momento. Tente novamente.
          </TextDescription>
        </BoxText>
      </Container>
    </SectionContainer>
  );
};

export default ErrorComponent;
