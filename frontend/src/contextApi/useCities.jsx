import { createContext, useContext, useState } from "react";
import api from "../services/api";

export const CitiesContext = createContext({});

export function CitiesContextProvider({ children }) {
  const [city, setCity] = useState({
    label: "Florianópolis, SC",
    id: 1,
  });

  const [citiesOptions, setCitiesOptions] = useState([]);

  const handleSearchCities = async () => {
    try {
      const response = await api.get("/cities/");
      const citiesFormated = response.data.map((city) => {
        return {
          label: city.cityAndState,
          id: city.id,
        };
      });
      setCitiesOptions(citiesFormated);
    } catch (error) {
      setCitiesOptions([]);
    }
  };

  return (
    <CitiesContext.Provider
      value={{
        city,
        setCity,
        citiesOptions,
        handleSearchCities,
      }}
    >
      {children}
    </CitiesContext.Provider>
  );
}

export const useCities = () => {
  return useContext(CitiesContext);
};
