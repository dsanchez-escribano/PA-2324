import {config, appFetch} from './appFetch';

export const findAllProvinces = (onSuccess) => appFetch('/sportevents/provinces', config('GET'), onSuccess);

export const findAlltypes = (onSuccess) => appFetch('/sportevents/types', config('GET'), onSuccess);

export const findEvents = ({provinceId, typeId, startDate, endDate, page},
                             onSuccess) => {

    let path = `/sportevents/events?page=${page}`;

    path += provinceId? `&provinceId=${provinceId}` : "";
    path += typeId? `&typeId=${typeId}` : "";
    path += startDate.length > 0 ? `&startDate=${encodeURIComponent(startDate)}` : "";
    path += endDate.length > 0 ? `&endDate=${encodeURIComponent(endDate)}` : "";

    appFetch(path, config('GET'), onSuccess);

}

export const findEventById = (id, onSuccess) =>
    appFetch(`/sportevents/events/${id}`, config('GET'), onSuccess);