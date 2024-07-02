import {FormattedDate, FormattedMessage, FormattedNumber, FormattedTime} from 'react-intl';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';
import {EventLink} from '../../common';

const Events = ({events, types, provinces}) => (

    <table className="table table-striped table-hover" id="displayTable">

        <thead>
        <tr>
            <th scope="col">
                <FormattedMessage id='project.global.fields.name'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.type'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.province'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.date'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.valoration'/>
            </th>
        </tr>
        </thead>

        <tbody>
        {events.map(event =>
            <tr key={event.id}>
                <td><EventLink id={event.id} name={event.sportEventName}/></td>
                <td>{selectors.getTypeName(types, event.typeId)}</td>
                <td>{selectors.getProvinceName(provinces, event.provinceId)}</td>
                <td><FormattedDate value={new Date(event.date)}/> - <FormattedTime value={new Date(event.date)}/></td>
                <td>{event.valorated ? <FormattedNumber value={event.media} />  : <FormattedMessage id = 'project.global.fields.NoValoration'/>}</td>
            </tr>
        )}
        </tbody>
    </table>

);

Events.propTypes = {
    events: PropTypes.array.isRequired,
    types: PropTypes.array.isRequired,
    provinces: PropTypes.array.isRequired
};

export default Events;
