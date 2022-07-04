import React from "react";
import { render, screen } from "@testing-library/react";
import About from ".";
import { CitiesContextProvider } from "../../contextApi/useCities";

function renderAll() {
  render(
    <CitiesContextProvider>
      <About />
    </CitiesContextProvider>
  );
}

describe("About component", () => {
  it("should show the title of the site", () => {
    renderAll();

    expect(
      screen.getByText(/encontre o hotel ideal para o seu amigão/i)
    ).toBeInTheDocument();
  });

  it("should show the website logo", () => {
    renderAll();

    expect(screen.getByRole("img")).toBeInTheDocument();
  });

  it("should show the about text", () => {
    renderAll();

    const getByTextWithMarkup = (text) => {
      screen.getByText((content, node) => {
        const doesHaveText = (node) => node.textContent === text;
        const nodeHasText = doesHaveText(node);
        const childrenDontHaveText = Array.from(node.children).every(
          (child) => !doesHaveText(child)
        );
        return nodeHasText && childrenDontHaveText;
      });
    };

    expect(
      getByTextWithMarkup(
        "A Pet Trip vem trazer um maior conforto para você que quer viajar e gostaria de deixar seu pet em um local apropriado e adequado para ele, bem ao lado do seu destino de viagem."
      )
    );
  });

  it("should show the question", () => {
    renderAll();

    expect(screen.getByText("Vamos encontrar juntos?")).toBeInTheDocument();
  });
});
