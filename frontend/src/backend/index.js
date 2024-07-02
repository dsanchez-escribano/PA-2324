import {init} from './appFetch';
import * as userService from './userService';
import * as eventService from './eventsService.js';
import * as inscriptionsService from "./inscriptionsService.js";

export {default as NetworkError} from "./NetworkError";

export default {init, userService, eventService, inscriptionsService};
