import { Container } from "@mui/material";
import React from "react";
import DogImage from "../../assets/images/dogImage.png";
import Search from "../Search";
import {
  AboutDescription,
  DescriptionItems,
  Title,
  TextAbout,
  BoxQuestion,
  QuestionAbout,
  ContainerBar,
} from "./styles";

const About = () => {
  return (
    <AboutDescription>
      <Container maxWidth="xl" style={{ paddingBottom: 25 }}>
        <DescriptionItems>
          <Title>Encontre o hotel ideal para o seu amigão</Title>
          <TextAbout>
            A <span>Pet Trip</span> vem trazer um maior conforto para você que
            quer viajar e gostaria de deixar seu pet em um local apropriado e
            adequado para ele, bem ao lado do seu destino de viagem.
          </TextAbout>
          <BoxQuestion>
            <img src={DogImage} alt="Ícone de cachorro" />
            <QuestionAbout>Vamos encontrar juntos?</QuestionAbout>
          </BoxQuestion>
        </DescriptionItems>
        <ContainerBar>
          <Search />
        </ContainerBar>
      </Container>
    </AboutDescription>
  );
};

export default About;
