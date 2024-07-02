import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useNavigate} from 'react-router-dom';
import PropTypes from 'prop-types';

import {Errors} from '../../common';
import * as actions from '../actions';

const RegistrationForm = ({eventId, eventName}) => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [creditCard, setCreditCard] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {
        event.preventDefault();

        if(form.checkValidity()){

            dispatch(actions.inscribe(eventId, creditCard, eventName, () => navigate('/inscriptions/registration-completed'), errors => setBackendErrors(errors)));

        }else{
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (
        <div id="registrationForm">
            <Errors errors={backendErrors}
                    onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.inscriptions.RegisterForm.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                          className="needs-validation" noValidate
                          onSubmit={(e) => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="creditCard" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.creditCard"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="creditCard" className="form-control"
                                       value={creditCard}
                                       onChange={e => setCreditCard(e.target.value)}
                                       autoFocus
                                       required
                                       />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button type="submit" id="submitButton" className="btn btn-primary">
                                    <FormattedMessage id="project.global.fields.inscribe"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

RegistrationForm.propTypes = {
    eventId: PropTypes.number.isRequired,
    eventName: PropTypes.string.isRequired
};

export default RegistrationForm;