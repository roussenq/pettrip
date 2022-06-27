import React from "react";
import { Container } from "@mui/material";
import { SectionFooter, BoxText, TextFooter } from "./styles";

/**
 * Este componente se refere ao Footer da página.
 * @returns o componente retorna os textos do Footer.
 */
const Footer = () => {
  return (
    <SectionFooter>
      <Container maxWidth="xl">
        <BoxText>
          <TextFooter>Projeto TCS Senac Santa Catarina - Palhoça</TextFooter>
          <TextFooter>Dúvidas: pettriptcs@gmail.com</TextFooter>
          <TextFooter>Desenvolvido por Jhony e Kamilla</TextFooter>
        </BoxText>
      </Container>
    </SectionFooter>
  );
};

export default Footer;
