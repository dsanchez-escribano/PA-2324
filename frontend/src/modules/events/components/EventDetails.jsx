import {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedDate, FormattedMessage, FormattedNumber, FormattedTime} from 'react-intl';

import users from "../../users/index.js";
import * as selectors from "../selectors.js";
import * as actions from "../actions.js";
import {BackLink} from '../../common';
import DeliverDorsalForm from "../../inscriptions/components/DeliverDorsalForm.jsx";
import {useParams} from "react-router-dom";
import RegistrationForm from "../../inscriptions/components/RegistrationForm.jsx";

const EventDetails = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const user = useSelector(users.selectors.getUser);
    const employee = (loggedIn && (user.role.toString() === "EMPLOYEE"));
    const AvUser = (loggedIn && (user.role.toString() === "PARTICIPANT"));
    const event = useSelector(selectors.getEvent);
    const types = useSelector(selectors.getTypes);
    const provinces = useSelector(selectors.getProvinces);
    const dispatch = useDispatch();
    const {id} = useParams();

    useEffect( () =>{
            const eventId = Number(id);

            if(!Number.isNaN(eventId)) {
                dispatch(actions.findEventById(eventId));
            }

            return() => dispatch(actions.clearEvent());
    }, [id, dispatch]);

    if(!event) {
        return null;
    }

    return (

        <div>

            <BackLink/>

            <div className="card text-center">
                <div className="card-body">
                    <h5 id="eventName" className="card-title">{event.sportEventName}</h5>
                    <h6 id="type" className="card-subtitle text-muted">
                        <FormattedMessage id='project.global.fields.type'/>:&nbsp;
                        {selectors.getTypeName(types, event.typeId)}
                    </h6>
                    <br/>
                    <p id="description" className="card-text font-weight-light font-italic">{event.description}</p>
                    <br/>
                    <p id="valoration" className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.valoration'/>{': '}
                        {event.valorated ? <FormattedNumber value={event.media}/> : <FormattedMessage id = 'project.global.fields.NoValoration'/>}
                    </p>
                    <p id="location" className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.location'/>{': '}
                        {event.location}
                    </p>
                    <p id="province" className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.province'/>{': '}
                        {selectors.getProvinceName(provinces, event.provinceId)}
                    </p>
                    <p id="date" className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.date'/>{': '}
                        <FormattedDate value={new Date(event.date)}/> - <FormattedTime value={new Date(event.date)}/>
                    </p>
                    <p id="price" className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.price'/>{': '}
                        <FormattedNumber value={event.price} style="currency" currency="EUR"/>
                    </p>
                    <p id="spots" className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.spots'/>{': '}
                        {event.spots}
                    </p>
                    <p id="participants" className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.participants'/>{': '}
                        {event.participants}
                    </p>
                    <br/>
                    {AvUser && event.available && <RegistrationForm eventId={event.id} eventName={event.sportEventName}/>}
                    {employee && !event.started && <DeliverDorsalForm eventId={event.id}/> }
                </div>
            </div>
        </div>

    );
}

export default EventDetails;