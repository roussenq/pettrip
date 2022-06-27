/** Context API dos hotéis
 * Criação de um contexto para Hotel e gerado um hook para a sua utilização, chamado useHotels.
 *  * funções:
 *   - HotelContextProvider({children}) função que irá compartilhar dados para seus componentes filhos.
 *   - handleSearch() função assíncrona que irá fazer a busca na API dos hotéis com a devida paginação e irá enviar por parâmetro os filtros selecionandos de características do pet, a cidade selecionada e a página.
 * useEffect é o hook que irá fazer a chamada da função handleSearch() e será chamado sempre que uma das variáveis do seu array de dependência atualizar.
 */

import React, { createContext, useContext, useEffect, useState } from "react";
import api from "../services/api";
import { useCities } from "./useCities";

export const HotelContext = createContext({});

export function HotelContextProvider({ children }) {
  const [hotels, setHotels] = useState([]);
  const [filter, setFilter] = useState({});
  const { city } = useCities();

  const [pagination, setPagination] = useState({
    page: 0,
    first: false,
    last: false,
  });

  const [error, setError] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  const handleSearch = async () => {
    setIsLoading(true);
    setError(false);
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
      const data = error.response.data;
      if (data.status === 400) {
        setHotels([]);
      } else {
        setError(true); //se vir qualquer erro diferente de 400, ex: 401, 402, 500...
      }
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    handleSearch();
  }, [city, filter, pagination.page]);

  return (
    <HotelContext.Provider
      value={{
        hotels,
        handleSearch,
        setFilter,
        pagination,
        setPagination,
        isLoading,
        error,
      }}
    >
      {children}
    </HotelContext.Provider>
  );
}

export const useHotels = () => {
  return useContext(HotelContext);
};
