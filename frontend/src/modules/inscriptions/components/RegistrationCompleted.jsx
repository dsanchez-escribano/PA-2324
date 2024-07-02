import {useSelector} from "react-redux";
import * as selectors from '../selectors';
import {FormattedMessage} from "react-intl";
import {BackLink} from "../../common/index.js";

const RegistrationCompleted = () => {

    const lastInscriptionInfo = useSelector(selectors.getLastInscriptionInfo)

    if (!lastInscriptionInfo) {
        return null;
    }

    return (
        <div>
            <BackLink/>
            <br/>
            <h4 id="eventName" className="text-center">{lastInscriptionInfo.name}</h4>
            <br/>
            <div className="alert alert-success justify-content-center" role="alert">
                <FormattedMessage id="project.inscriptions.inscriptionCompleted.id"/>:
                &nbsp; <p id="inscriptionId">{lastInscriptionInfo.info.inscriptionId}</p>
                <br/>
                <FormattedMessage id="project.inscriptions.inscriptionCompleted.dorsal"/>:
                &nbsp; <p id="dorsal">{lastInscriptionInfo.info.dorsal}</p>
            </div>
        </div>
    );
}


export default RegistrationCompleted;