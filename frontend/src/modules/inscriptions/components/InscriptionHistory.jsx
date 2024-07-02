import { FormattedMessage, FormattedDate, FormattedTime, FormattedNumber } from 'react-intl';
import PropTypes from 'prop-types';

import { EventLink } from "../../common/index.js";
import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import * as actions from "../actions.js";

const InscriptionHistory = ({inscriptions}) => {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const rate = (eventName, inscriptionId) =>{

        dispatch(actions.rateInfoCompleted(eventName, inscriptionId));

        navigate('/inscriptions/rate');
    }

    return (
        <table className="table table-striped table-hover">

            <thead>
                <tr>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.date' />
                    </th>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.inscriptionId' />
                    </th>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.eventName' />
                    </th>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.dorsal' />
                    </th>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.creditCard' />
                    </th>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.picked' />
                    </th>
                    <th scope="col">
                        <FormattedMessage id='project.global.fields.valoration' />
                    </th>
                    <th scope="col"></th>
                </tr>
            </thead>

            <tbody>
                {inscriptions.map(inscription =>
                    <tr key={inscription.id}>
                        <td>
                            <FormattedDate value={new Date(inscription.inscriptionDate)} /> - <FormattedTime
                                value={new Date(inscription.inscriptionDate)} />
                        </td>
                        <td id={`id-${inscription.id}`}>{inscription.id}</td>
                        <td><EventLink name={inscription.eventName} id={inscription.eventId} /></td>
                        <td id={`dorsal-${inscription.id}`}>{inscription.dorsal}</td>
                        <td>{inscription.creditCard}</td>
                        <td>{inscription.picked ? <FormattedMessage id='project.global.fields.picked' /> : <FormattedMessage id='project.global.fields.notPicked' />}</td>
                        <td>{inscription.rated ? <FormattedNumber value={inscription.valoration} /> : <FormattedMessage id='project.global.fields.NoValoration' />}</td>
                        <td><button className='btn btn-dark' onClick={() => rate(inscription.eventName, inscription.id)} hidden={!inscription.rateAvailable}><FormattedMessage id='project.global.fields.rate' /></button></td>
                    </tr>
                )}
            </tbody>

        </table>

    );
}

InscriptionHistory.propTypes = {
    inscriptions: PropTypes.array.isRequired,
};

export default InscriptionHistory;
