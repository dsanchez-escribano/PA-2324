import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

const EventLink = ({id, name}) => {

    return (
        <Link to={`/events/event-details/${id}`} id={`event-${id}`}>
            {name}
        </Link>
    );

}

EventLink.propTypes = {
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
};

export default EventLink;