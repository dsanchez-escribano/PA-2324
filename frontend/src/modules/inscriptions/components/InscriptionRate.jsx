import {useDispatch, useSelector} from "react-redux";

import * as selectors from "../selectors";
import {BackLink, Errors} from "../../common/index.js";
import {FormattedMessage} from "react-intl";
import {useState} from "react";
import * as actions from "../actions.js";

const InscriptionRate = () => {
    const dispatch = useDispatch();
    const [hidden, setHidden] = useState(true);
    const rateInfo = useSelector(selectors.getRateInfo);
    const [valoration, setValoration] = useState(1);
    const [backendErrors, setBackendErrors] = useState(null);

    const handleSubmit = event => {
        event.preventDefault();

        dispatch(actions.rate(rateInfo.inscriptionId, valoration, () => {setHidden(false); console.log(hidden); }, errors => setBackendErrors(errors)));
    }

    return (
        <div>
            <BackLink />
            <br/>
            <Errors errors={backendErrors}
                    onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header text-center">
                    {rateInfo.name}
                </h5>
                <div className="card-body">
                    <form className="" noValidate
                          onSubmit={(e) => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="valoration" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.valoration"/>
                            </label>
                            <div className="col-md-4">
                                <select id="valoration" className="form-control"
                                        onChange={e => setValoration(e.target.value)}>
                                    <option value={1}>1</option>
                                    <option value={2}>2</option>
                                    <option value={3}>3</option>
                                    <option value={4}>4</option>
                                    <option value={5}>5</option>
                                </select>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.global.fields.rate"/>
                                </button>
                            </div>
                        </div>
                    </form>
                    {!hidden &&
                    <div className="alert alert-success justify-content-center" role="alert" >
                        <FormattedMessage id="project.inscriptions.rateCompleted"/>
                    </div>}
                </div>
            </div>
        </div>
    );
}

export default InscriptionRate;