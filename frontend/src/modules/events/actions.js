import * as actionTypes from './actionTypes';
import * as selectors from './selectors';
import backend from '../../backend';

const findAllTypesCompleted = types => ({
    type: actionTypes.FIND_ALL_TYPES_COMPLETED,
    types
});

export const findAllTypes = () => (dispatch, getState) => {

    const types = selectors.getTypes(getState());

    if (!types) {

        backend.eventService.findAlltypes(
            types => dispatch(findAllTypesCompleted(types))
        );

    }

}

const findAllProvincesCompleted = provinces => ({
    type: actionTypes.FIND_ALL_PROVINCES_COMPLETED,
    provinces
});

export const findAllProvinces = () => (dispatch, getState) => {

    const provinces = selectors.getProvinces(getState());

    if (!provinces) {

        backend.eventService.findAllProvinces(
            provinces => dispatch(findAllProvincesCompleted(provinces))
        );

    }

}

const findEventsCompleted = eventSearch => ({
    type: actionTypes.FIND_EVENTS_COMPLETED,
    eventSearch
});

export const findEvents = criteria => dispatch => {
    dispatch(clearEventsSearch());
    backend.eventService.findEvents(criteria,
        result => dispatch(findEventsCompleted({ criteria, result })));
}

const clearEventsSearch = () => ({
    type: actionTypes.CLEAR_EVENTS_SEARCH
});

export const previousFindEventsResultPage = criteria =>
    findEvents({ ...criteria, page: criteria.page - 1 });

export const nextFindEventsResultPage = criteria =>
    findEvents({ ...criteria, page: criteria.page + 1 });

const findEventByIdCompleted = event => ({
    type: actionTypes.FIND_EVENT_BY_ID_COMPLETED,
    event
});

export const findEventById = id => dispatch => {
    backend.eventService.findEventById(id,
        event => dispatch(findEventByIdCompleted(event)));
}

export const clearEvent = () => ({
    type: actionTypes.CLEAR_EVENT
});