import React from "react";
import { Box } from "@mui/material";
import { useHotels } from "../../contextApi/useHotels";
import { BoxButton, ButtonPage } from "./styles";
import {
  BsFillArrowLeftCircleFill,
  BsFillArrowRightCircleFill,
} from "react-icons/bs";

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
    <BoxButton>
      <ButtonPage disabled={pagination.first} onClick={returnPage}>
        <BsFillArrowLeftCircleFill />
      </ButtonPage>
      <ButtonPage disabled={pagination.last} onClick={nextPage}>
        <BsFillArrowRightCircleFill />
      </ButtonPage>
    </BoxButton>
  );
}

export default Pagination;
