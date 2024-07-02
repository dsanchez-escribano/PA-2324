import { useSelector } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';

const TypeSelector = (selectProps) => {
    const types = useSelector(selectors.getTypes);
    return (
        <select {...selectProps}>
            <FormattedMessage id='project.events.TypeSelector.allTypes'>
                {message => (<option value="">{message}</option>)}
            </FormattedMessage>

            {types && types.map(type => <option key={type.id} value={type.id}>{type.typeName}</option>)}
        </select>
    );
}

TypeSelector.propTypes = {
    selectProps: PropTypes.object
};

export default TypeSelector;