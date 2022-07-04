import React from "react";
import { Container } from "@mui/material";
import { useHotels } from "../../contextApi/useHotels";
import {
  CardBox,
  BoxImage,
  BoxHotelDescritions,
  Name,
  Description,
  BoxContact,
  Address,
  Contact,
} from "./styles";

/**
 * Este componente renderiza a lista de hotéis na tela.
 * @returns o componente retorna uma lista de hotéis.
 */

const ListHotels = () => {
  const { hotels } = useHotels();
  return (
    <Container maxWidth="xl">
      <ul>
        {hotels.map((hotel) => (
          <li key={hotel.id}>
            <CardBox>
              <BoxImage>
                <img src={hotel.image} alt="Imagem do Hotel" />
              </BoxImage>
              <BoxHotelDescritions>
                <Name>{hotel.name}</Name>
                <Description>{hotel.description}</Description>
                <BoxContact>
                  <Address>
                    {hotel.address.street}, nº {hotel.address.number}, {""}
                    {hotel.address.district}, {hotel.address.city.city}, {""}
                    {hotel.address.city.state}, CEP {hotel.address.zipCode}
                  </Address>
                  <Contact>
                    {hotel.email}, {hotel.numberPhone}
                  </Contact>
                </BoxContact>
              </BoxHotelDescritions>
            </CardBox>
          </li>
        ))}
      </ul>
    </Container>
  );
};

export default ListHotels;
