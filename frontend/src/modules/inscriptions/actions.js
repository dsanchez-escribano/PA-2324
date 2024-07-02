import backend from '../../backend';
import * as actionTypes from './actionTypes';

const inscriptionCompleted = (info, eventName) => ({
    type: actionTypes.INSCRIPTION_COMPLETED,
    info,
    eventName
});

export const inscribe = (eventId, creditCard, eventName, onSuccess,
                    onErrors) => dispatch =>
    backend.inscriptionsService.inscribe(eventId, creditCard, info => {
            dispatch(inscriptionCompleted(info, eventName));
            onSuccess();
        },
        onErrors);


const  giveDorsalCompleted = giveDorsalInfo => ({
    type: actionTypes.GIVE_DORSAL_COMPLETED,
    giveDorsalInfo
});
export const giveDorsal =(eventId, id, creditCard, onErrors) => dispatch =>
    backend.inscriptionsService.giveDorsal(eventId,id,creditCard, dorsal=>
        dispatch(giveDorsalCompleted(dorsal)), onErrors);

const rateCompleted = ()=> ({
        type: actionTypes.RATE_COMPLETED
    });
export const rate = (inscriptionId, valoration, onSuccess, onErrors) => dispatch =>
    backend.inscriptionsService.rate(inscriptionId, valoration, () => {
        dispatch(rateCompleted());
        onSuccess();
    },
        onErrors
);

const seeInscriptionsHistoryCompleted = inscriptionsSearch => ({
    type: actionTypes.INSCRIPTION_HISTORY_COMPLETED,
    inscriptionsSearch
});

export const seeInscriptionsHistory = criteria => dispatch => {
    backend.inscriptionsService.seeInscriptionHistory(criteria,
        result => dispatch(seeInscriptionsHistoryCompleted({criteria,result})));
}

export const clearInscriptionSearch = () => ({
    type: actionTypes.CLEAR_INSCRIPTION_SEARCH
});

export const clearDorsal = () => ({
    type: actionTypes.CLEAR_DORSAL
});

export const previousSeeInscriptionsResultPage = criteria => dispatch => {
    dispatch(clearInscriptionSearch());
    dispatch(seeInscriptionsHistory({page: criteria.page-1}));
}

export const nextSeeInscriptionsResultPage = criteria => dispatch => {
    dispatch(clearInscriptionSearch());
    dispatch(seeInscriptionsHistory({page: criteria.page+1}));
}

export const rateInfoCompleted = (eventName, inscriptionId) => ({
    type: actionTypes.RATE_INFO_COMPLETED,
    Name: eventName,
    Id: inscriptionId
});