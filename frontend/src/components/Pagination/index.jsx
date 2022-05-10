import React from "react";
import { Box, Button } from "@mui/material";
import { useHotels } from "../../contextApi/useHotels";

function Pagination() {
  const { pagination, setPagination } = useHotels();

  function returnPage() {
    console.log("voltou");
    const newPage = { ...pagination, page: pagination.page - 1 };
    setPagination(newPage);
  }

  function nextPage() {
    console.log("voltou");
    const newPage = { ...pagination, page: pagination.page + 1 };
    setPagination(newPage);
  }

  return (
    <Box>
      <Button onClick={returnPage}>Voltar</Button>
      <Button disabled={pagination.last} onClick={nextPage}>
        Avançar
      </Button>
    </Box>
  );
}

export default Pagination;
