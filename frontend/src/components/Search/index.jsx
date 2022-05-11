import React, { useEffect, useState } from "react";
import { ButtonSearch, BoxForm, InputAutocomplete, BoxSearch } from "./styles";
import { TextField } from "@mui/material";
import { useCities } from "../../contextApi/useCities";
import { useHotels } from "../../contextApi/useHotels";

const Search = () => {
  const { handleSearchCities, citiesOptions, setCity } = useCities();
  const { pagination, setPagination } = useHotels();

  const [selectedCity, setSelectedCity] = useState({
    label: "Florianópolis, SC",
    id: 1,
  });

  function handleSubmit() {
    setCity(selectedCity);
    setPagination({ ...pagination, page: 0 });
  }

  useEffect(() => {
    handleSearchCities();
  }, []);

  return (
    <BoxForm>
      <form
        onSubmit={(event) => {
          event.preventDefault();
          handleSubmit();
        }}
      >
        <BoxSearch>
          <InputAutocomplete
            disablePortal
            id="combo-box-demo"
            options={citiesOptions}
            onChange={(event, newValue) => {
              setSelectedCity(newValue);
            }}
            renderInput={(params) => (
              <TextField
                {...params}
                placeholder="Para onde você está indo?"
                variant="standard"
                InputProps={{ ...params.InputProps, disableUnderline: true }}
              />
            )}
          />
          <ButtonSearch type="submit">Pesquisar</ButtonSearch>
        </BoxSearch>
      </form>
    </BoxForm>
  );
};

export default Search;
