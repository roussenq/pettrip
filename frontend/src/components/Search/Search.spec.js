import React from "react";
import { render, screen, fireEvent, within, act } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { HotelContextProvider } from "../../contextApi/useHotels";
import { CitiesContextProvider } from "../../contextApi/useCities";
import Search from ".";

describe("Search component", () => {
  it.only("send selected city", async () => {
    render(
      <CitiesContextProvider>
        <HotelContextProvider>
          <Search />
        </HotelContextProvider>
      </CitiesContextProvider>
    );

    const autocomplete = screen.getByTestId("autocomplete");
    const input = within(autocomplete).getByPlaceholderText(
      "Para onde você está indo?"
    );
    autocomplete.focus();
    fireEvent.change(input, { target: { value: 1 } });
    fireEvent.keyDown(autocomplete, { key: "ArrowDown" });
    fireEvent.keyDown(autocomplete, { key: "Enter" });
    console.log(autocomplete.options);
    expect();
  });

  it("teste form onSubmit", () => {
    render(
      <CitiesContextProvider>
        <HotelContextProvider>
          <Search />
        </HotelContextProvider>
      </CitiesContextProvider>
    );

    const input = screen.getByTestId("input-newValue");
    const form = screen.getByTestId("form-submit-city");

    userEvent.type(input, "Florianópolis");
    fireEvent.submit(form);

    screen.logTestingPlaygroundURL();

    expect(screen.getByTestId("Florianópolis")).toBeTruthy();
  });
});
