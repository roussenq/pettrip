import { rest } from "msw";
import { baseURL } from "../../services/api";

const mockTasks = {
  content: [
    {
      id: 1,
      cnpj: "11.222.333/0004-02",
      name: "Pet Hotel Astrodog Léo",
      description: "Test",
      email: "hotel2@email.com",
      numberPhone: "(48) 99999-999",
      image: "teste.jpg",
      address: {
        id: 1,
        street: "Rua Hotel 2",
        number: "456",
        complement: null,
        district: "Ingleses",
        city: {
          id: 1,
          city: "Florianópolis",
          state: "SC",
        },
        zipCode: "88222145",
      },
    },
  ],
  pageable: { pageNumber: 0 },
  first: true,
  last: false,
};

const getHotelsEndpoint = `${baseURL}/establishment`;

const hotelsHandler = rest.get(getHotelsEndpoint, async (req, res, ctx) => {
  return res(ctx.json(mockTasks));
});

export const hotelsHandlerExceptionNotFound = rest.get(
  getHotelsEndpoint,
  async (req, res, ctx) => res(ctx.status(400))
);

export const hotelsHandlerExceptionErrorComponent = rest.get(
  getHotelsEndpoint,
  async (req, res, ctx) => res(ctx.status(500))
);

export const hotelHandler = [hotelsHandler];
