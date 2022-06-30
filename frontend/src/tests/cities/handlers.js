import { rest } from "msw";
import { baseURL } from "../../services/api";

const mockTasks = [{ id: 1, cityAndState: "Florianópolis" }];

const getCitiesEndpoint = `${baseURL}/cities`;

const citiesHandler = rest.get(getCitiesEndpoint, async (req, res, ctx) => {
  return res(ctx.json(mockTasks));
});

export const citieHandler = [citiesHandler];
