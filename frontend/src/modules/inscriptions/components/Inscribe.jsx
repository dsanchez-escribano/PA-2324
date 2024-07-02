import RegistrationForm from "./RegistrationForm.jsx";
import * as selectors from "../../events/selectors.js";
import {useSelector} from "react-redux";
import PropTypes from "prop-types";

const Inscribe = ({eventId, AvUser}) => {

    if(AvUser){
        return null;
    }

    return (
        <div>
            <RegistrationForm eventId={eventId}/>
        </div>
    );

}

Inscribe.propTypes = {
    eventId: PropTypes.number.isRequired,
    AvUser: PropTypes.bool.isRequired
};
export default Inscribe;