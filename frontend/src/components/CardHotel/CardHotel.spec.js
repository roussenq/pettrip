import React from "react";
import { render, screen } from "@testing-library/react";

import { hotelsHandlerExceptionNotFound } from "../../tests/hotels/handlers";
import { hotelsHandlerExceptionErrorComponent } from "../../tests/hotels/handlers";

import { mswServer } from "../../tests/msw-server";

import { HotelContextProvider } from "../../contextApi/useHotels";
import { CitiesContextProvider } from "../../contextApi/useCities";
import CardHotel from ".";

function renderAll() {
  render(
    <CitiesContextProvider>
      <HotelContextProvider>
        <CardHotel />
      </HotelContextProvider>
    </CitiesContextProvider>
  );
}

describe("CardHotel component", () => {
  it("should show the list of hotels", async () => {
    renderAll();

    const hotelName = await screen.findAllByText("Pet Hotel Astrodog Léo");
    expect(hotelName[0].textContent).toBe("Pet Hotel Astrodog Léo");
  });

  it("displays error message when fetching hotels not found", async () => {
    mswServer.use(hotelsHandlerExceptionNotFound);
    renderAll();

    const errorDisplay = await screen.findByText(
      "Não foi possível carregar a página nesse momento. Tente novamente."
    );

    expect(errorDisplay).toBeInTheDocument();
  });

  it("displays error message when fetching hotels error", async () => {
    mswServer.use(hotelsHandlerExceptionErrorComponent);
    renderAll();

    const errorDisplay = await screen.findByText("Desculpe.");
    expect(errorDisplay).toBeInTheDocument();
  });
});
