import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { FormattedMessage } from 'react-intl';

import TypeSelector from './TypeSelector';
import ProvinceSelector from './ProvinceSelector';
import * as actions from '../actions';

const FindEvents = () => {

    const dispatch = useDispatch();
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [typeId, setTypeId] = useState('');
    const [provinceId, setProvinceId] = useState('');

    const handleSubmit = event => {
        event.preventDefault();
        dispatch(actions.findEvents(
            {
                startDate: startDate.trim(),
                endDate: endDate.trim(),
                typeId: toNumber(typeId),
                provinceId: toNumber(provinceId),
                page: 0
            }));
    }

    const toNumber = value => value.length > 0 ? Number(value) : null;

    return (

        <form className="form-inline justify-content-center mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>

            <TypeSelector id="typeId" className="custom-select my-1 mr-sm-2"
                value={typeId} onChange={e => setTypeId(e.target.value)} />

            <ProvinceSelector id="provinceId" className="custom-select my-1 mr-sm-2"
                value={provinceId} onChange={e => setProvinceId(e.target.value)} />

            <input id="startDate" type="date" className="form-control mr-sm-2"
                value={startDate} onChange={e => setStartDate(e.target.value)} />

            <input id="endDate" type="date" className="form-control mr-sm-2"
                value={endDate} onChange={e => setEndDate(e.target.value)} />

            <button type="submit" id="findSubmit" className="btn btn-primary my-2 my-sm-0">
                <FormattedMessage id='project.global.buttons.search' />
            </button>

        </form>

    );

}

export default FindEvents;
