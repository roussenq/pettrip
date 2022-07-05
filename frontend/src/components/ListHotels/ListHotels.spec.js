import React from "react";
import { render, screen } from "@testing-library/react";
import { HotelContextProvider } from "../../contextApi/useHotels";
import { CitiesContextProvider } from "../../contextApi/useCities";
import ListHotels from ".";

describe("CardHotel component", () => {
  it("should show the list of hotels", async () => {
    render(
      <CitiesContextProvider>
        <HotelContextProvider>
          <ListHotels />
        </HotelContextProvider>
      </CitiesContextProvider>
    );
    const hotelName = await screen.findAllByText("Pet Hotel Astrodog Léo");

    expect(hotelName[0].textContent).toBe("Pet Hotel Astrodog Léo");
  });
});
