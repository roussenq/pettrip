import React from "react";
import Header from "../components/Header";
import About from "../components/About";
import Main from "../components/Main";
import Footer from "../components/Footer";
import { HotelContextProvider } from "../contextApi/useHotels";
import { CitiesContextProvider } from "../contextApi/useCities";

export function Home() {
  return (
    <>
      <Header />
      <CitiesContextProvider>
        <HotelContextProvider>
          <About />
          <Main />
        </HotelContextProvider>
      </CitiesContextProvider>
      <Footer />
    </>
  );
}
