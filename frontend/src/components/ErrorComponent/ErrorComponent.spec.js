import React from "react";
import { render, screen } from "@testing-library/react";
import { mswServer } from "../../tests/msw-server";
import { hotelsHandlerExceptionErrorComponent } from "../../tests/hotels/handlers";
import { HotelContextProvider } from "../../contextApi/useHotels";
import { CitiesContextProvider } from "../../contextApi/useCities";
import ErrorComponent from ".";

describe("ErrorComponent component", () => {
  it("displays error message when fetching hotels error", async () => {
    mswServer.use(hotelsHandlerExceptionErrorComponent);
    render(
      <CitiesContextProvider>
        <HotelContextProvider>
          <ErrorComponent />
        </HotelContextProvider>
      </CitiesContextProvider>
    );
    const errorDisplay = await screen.findByText("Desculpe.");
    expect(errorDisplay).toBeInTheDocument();
  });
});
