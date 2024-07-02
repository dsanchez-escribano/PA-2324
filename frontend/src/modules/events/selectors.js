const getModuleState = state => state.events;

export const getTypes = state =>
    getModuleState(state).types;

export const getTypeName = (types, id) => {

    if (!types) {
        return '';
    }

    const type = types.find(type => type.id === id);

    if (!type) {
        return '';
    }

    return type.typeName;

}

export const getProvinces = state =>
    getModuleState(state).provinces;

export const getProvinceName = (provinces, id) => {

    if (!provinces) {
        return '';
    }

    const province = provinces.find(province => province.id === id);

    if (!province) {
        return '';
    }

    return province.provinceName;

}

export const getEventSearch = state =>
    getModuleState(state).eventSearch;

export const getEvent = state =>
    getModuleState(state).event;