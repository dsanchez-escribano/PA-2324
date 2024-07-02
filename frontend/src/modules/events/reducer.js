import { combineReducers } from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    types: null,
    provinces: null,
    eventSearch: null,
    event: null
};

const types = (state = initialState.types, action) => {

    switch (action.type) {

        case actionTypes.FIND_ALL_TYPES_COMPLETED:
            return action.types;

        default:
            return state;

    }

}

const provinces = (state = initialState.provinces, action) => {

    switch (action.type) {

        case actionTypes.FIND_ALL_PROVINCES_COMPLETED:
            return action.provinces;

        default:
            return state;

    }

}

const eventSearch = (state = initialState.eventSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_EVENTS_COMPLETED:
            return action.eventSearch;

        case actionTypes.CLEAR_EVENTS_SEARCH:
            return initialState.eventSearch;

        default:
            return state;

    }

}

const event = (state = initialState.event, action) => {

    switch (action.type) {

        case actionTypes.FIND_EVENT_BY_ID_COMPLETED:
            return action.event;

        case actionTypes.CLEAR_EVENT:
            return initialState.event;

        default:
            return state;

    }

}

const reducer = combineReducers({
    types,
    provinces,
    eventSearch,
    event
});

export default reducer;


