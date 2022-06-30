import React from "react";
import { render, screen } from "@testing-library/react";
import { mswServer } from "../../tests/msw-server";
import { hotelsHandlerExceptionNotFound } from "../../tests/hotels/handlers";
import { HotelContextProvider } from "../../contextApi/useHotels";
import { CitiesContextProvider } from "../../contextApi/useCities";
import HotelNotFound from ".";

it("displays error message when fetching hotels not found", async () => {
  mswServer.use(hotelsHandlerExceptionNotFound);
  render(
    <CitiesContextProvider>
      <HotelContextProvider>
        <HotelNotFound />
      </HotelContextProvider>
    </CitiesContextProvider>
  );

  const errorDisplay = await screen.findByText("Sinto muito.");

  expect(errorDisplay).toBeInTheDocument();
});
