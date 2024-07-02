import {config, appFetch} from "./appFetch.js";

export const inscribe = (eventId, creditCard,onSuccess, onErrors) => appFetch('/inscriptions/inscribe', config('POST', {eventId, creditCard}), onSuccess, onErrors);

export const giveDorsal = (eventId, id, creditCard, onSuccess, onErrors) => appFetch('/inscriptions/giveDorsal', config('POST', {eventId, id, creditCard}), onSuccess, onErrors);

export const seeInscriptionHistory = ({page}, onSuccess) => appFetch(`/inscriptions/inscriptions?page=${page}`, config('GET'), onSuccess);

export const rate = (inscriptionId, valoration, onSuccess, onErrors) => appFetch(`/inscriptions/inscriptions/${inscriptionId}/rate`, config('POST', {valoration}), onSuccess, onErrors);
