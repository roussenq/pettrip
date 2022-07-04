import React from "react";
import { Container } from "@mui/material";
import CircularProgress from "@mui/material/CircularProgress";
import { WrapLoading, TextCity, CardContainer } from "./styles";
import { useHotels } from "../../contextApi/useHotels";
import HotelNotFound from "../HotelNotFound";
import { useCities } from "../../contextApi/useCities";
import ErrorComponent from "../ErrorComponent";
import Pagination from "../Pagination";
import ListHotels from "../ListHotels";

/**
 * Este componente é responsável por renderizar na tela os hotéis ou os componentes HotelNotFound ou ErrorComponent.
 * Através do Context API useCities, este componente consegue ter acesso à cidade escolhida pelo usuário através da variável de estado city, para mostrar em tela.
 * Ainda, através do Context API useHotels, este componente consegue ter acesso aos hotéis que estão vindo da API através da variável de estado hotels, também ao estado do isLoading e estado do erro.
 * @returns caso venha hotéis da API, é renderizado em tela a cidade selecionada e os hotéis.
 *  * @returns caso não venha nenhum hotel da API (status 400), é retornado o componente HotelNotFound.
 *  * @returns caso ocorra um status de erro != 400, é retornado o componente ErrorComponent.
 */

const CardHotel = () => {
  const { hotels, isLoading, error } = useHotels();
  const { city } = useCities();

  return (
    <CardContainer>
      <Container maxWidth="xl">
        <TextCity>
          Hotéis em: <span>{city.label} </span>
        </TextCity>

        {isLoading && (
          <WrapLoading>
            <CircularProgress color="success" />
          </WrapLoading>
        )}
        {error && !isLoading && <ErrorComponent />}
        {!error && !isLoading && hotels.length === 0 && <HotelNotFound />}
        {!error && !isLoading && hotels.length > 0 && <ListHotels />}
      </Container>
      {!error && !isLoading && hotels.length > 0 && <Pagination />}
    </CardContainer>
  );
};

export default CardHotel;
