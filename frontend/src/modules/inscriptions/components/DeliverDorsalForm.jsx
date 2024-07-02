import {useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors.js';

const DeliverDorsalForm = (eventId) => {
    const dorsal = useSelector(selectors.getGiveDorsalInfo);
    const dispatch = useDispatch();
    const [creditCard, setCreditCard] = useState('');
    const [inscriptionId, setInscriptionId] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    useEffect( () =>{
        return() => dispatch(actions.clearDorsal());
    }, [dispatch]);

    const handleSubmit = event => {
        event.preventDefault();

        if(form.checkValidity()){
            setBackendErrors(null);
            dispatch(actions.giveDorsal(eventId.eventId, inscriptionId, creditCard, errors => setBackendErrors(errors)));
        }else{
            setBackendErrors(null);
            form.classList.add('was-validated');
        }
    }

    return (
        <div>
            <Errors errors={backendErrors}
                    onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.inscriptions.GiveDorsalForm.title"/>
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
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>

                        <div className="form-group row">
                            <label htmlFor="inscriptionId" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.inscriptionId"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="inscriptionId" className="form-control"
                                       value={inscriptionId}
                                       onChange={e => setInscriptionId(e.target.value)}
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.global.fields.verify"/>
                                </button>
                            </div>
                        </div>
                    </form>
                    <div className="alert alert-success justify-content-center" role="alert" hidden={!dorsal}>
                        <FormattedMessage id="project.inscriptions.inscriptionCompleted.dorsal"/>:
                        &nbsp; {dorsal}
                    </div>
                </div>
            </div>
        </div>
    );
}

DeliverDorsalForm.propTypes = {
    eventId: PropTypes.number.isRequired
};

export default DeliverDorsalForm;