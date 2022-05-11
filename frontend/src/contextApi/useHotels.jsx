import { createContext, useContext, useEffect, useState } from "react";
import api from "../services/api";
import { useCities } from "./useCities";

export const HotelContext = createContext({});

export function HotelContextProvider({ children }) {
  const [hotels, setHotels] = useState([]);
  const [filter, setFilter] = useState({});
  const { city } = useCities();

  const [citiesOptions, setCitiesOptions] = useState([]);

  const [pagination, setPagination] = useState({
    page: 0,
    first: false,
    last: false,
  });

  const handleSearch = async () => {
    let paramsOptions = {};
    paramsOptions = {
      params: {
        type: filter.type,
        gender: filter.gender,
        castrated: filter.castrated,
        weight: filter.weight,
        cityId: city.id,
      },
    };
    paramsOptions = {
      params: {
        ...paramsOptions.params,
        page: pagination.page,
      },
    };

    try {
      const response = await api.get("/establishment/", paramsOptions);
      const {
        content,
        pageable: { pageNumber: page },
        first,
        last,
      } = response.data;
      setHotels(content);
      setPagination({ ...pagination, page, first, last });
    } catch (error) {
      setHotels([]);
    }
  };

  useEffect(() => {
    handleSearch();
  }, [city, filter, pagination.page]);

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
    <HotelContext.Provider
      value={{
        hotels,
        handleSearch,
        citiesOptions,
        handleSearchCities,
        setFilter,
        pagination,
        setPagination,
      }}
    >
      {children}
    </HotelContext.Provider>
  );
}

export const useHotels = () => {
  return useContext(HotelContext);
};
