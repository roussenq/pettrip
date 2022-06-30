import { setupServer } from "msw/node";
import { hotelHandler } from "./hotels/handlers";
import { citieHandler } from "./cities/handlers";

export const mswServer = setupServer(...hotelHandler, ...citieHandler);
