import React from "react";
import { Container } from "@mui/material";
import {
  TextCity,
  CardContainer,
  CardBox,
  BoxImage,
  BoxHotelDescritions,
  Name,
  Description,
  BoxContact,
  Address,
  Contact,
} from "./styles";
import { useHotels } from "../../contextApi/useHotels";
import HotelNotFound from "../HotelNotFound";

const CardHotel = () => {
  const { city, hotels } = useHotels();

  if (hotels.length === 0) {
    return <HotelNotFound />;
  } else {
    return (
      <CardContainer>
        <Container maxWidth="xl">
          <TextCity>
            Hotéis em: <span>{city.label} </span>
          </TextCity>
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
                        {hotel.address.district}, {hotel.address.city.city},{" "}
                        {""}
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
      </CardContainer>
    );
  }
};

export default CardHotel;
