import { combineReducers } from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    rateInfo: null,
    lastInscription: null,
    giveDorsalInfo: null,
    inscriptionsSearch: null
};


const rateInfo = (state = initialState.rateInfo, action) =>{

    switch (action.type){

        case actionTypes.RATE_INFO_COMPLETED:
            return {name: action.Name, inscriptionId: action.Id};

        default:
            return state;
    }
}
const lastInscriptionInfo = (state = initialState.lastInscription, action) => {

    switch (action.type) {

        case actionTypes.INSCRIPTION_COMPLETED:
            return {info: action.info, name: action.eventName};

        default:
            return state;
    }
}

const giveDorsalInfo = (state = initialState.giveDorsalInfo, action) => {

    switch (action.type) {

        case actionTypes.GIVE_DORSAL_COMPLETED:
            return action.giveDorsalInfo;

        case actionTypes.CLEAR_DORSAL:
            return  initialState.giveDorsalInfo;

        default:
            return state;
    }
}
const inscriptionsSearch = (state = initialState.inscriptionsSearch, action) => {

    switch (action.type) {

        case actionTypes.INSCRIPTION_HISTORY_COMPLETED:
            return action.inscriptionsSearch;

        case actionTypes.CLEAR_INSCRIPTION_SEARCH:
            return initialState.inscriptionsSearch;

        default:
            return state;
    }
}

const reducer = combineReducers({
    rateInfo,
    lastInscriptionInfo,
    giveDorsalInfo,
    inscriptionsSearch
});

export default reducer;
