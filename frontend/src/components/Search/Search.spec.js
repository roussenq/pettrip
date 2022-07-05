import React from "react";
import {
  render,
  screen,
  fireEvent,
  within,
  waitFor,
} from "@testing-library/react";
import { HotelContextProvider } from "../../contextApi/useHotels";
import { CitiesContextProvider } from "../../contextApi/useCities";
import Search from ".";

beforeEach(() => {
  render(
    <CitiesContextProvider>
      <HotelContextProvider>
        <Search />
      </HotelContextProvider>
    </CitiesContextProvider>
  );
});

describe("Search component", () => {
  it("should be select city onChange autocomplete", async () => {
    const autocomplete = screen.getByTestId("autocomplete");
    const input = within(autocomplete).getByPlaceholderText(
      "Para onde você está indo?"
    );

    autocomplete.click();
    autocomplete.focus();

    fireEvent.change(input, { target: { value: 1 } });

    await waitFor(async () => {
      fireEvent.keyDown(autocomplete, { key: "ArrowDown" });
      fireEvent.keyDown(autocomplete, { key: "Enter" });
    });

    expect(input.value).toEqual("1");
  });

  it("should be onSubmit form test", async () => {
    const form = screen.getByTestId("form-submit-city");
    const autocomplete = screen.getByTestId("autocomplete");
    const input = within(autocomplete).getByPlaceholderText(
      "Para onde você está indo?"
    );

    autocomplete.click();
    autocomplete.focus();

    fireEvent.change(input, { target: { value: 1 } });

    await waitFor(async () => {
      fireEvent.keyDown(autocomplete, { key: "ArrowDown" });
      fireEvent.keyDown(autocomplete, { key: "Enter" });

      form.submit();
    });
  });
});
