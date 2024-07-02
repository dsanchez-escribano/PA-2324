import { useSelector } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';

const ProvinceSelector = (selectProps) => {
    const provinces = useSelector(selectors.getProvinces);

    return (
        <select {...selectProps}>
            <FormattedMessage id='project.events.ProvinceSelector.allProvinces'>
                {message => (<option value="">{message}</option>)}
            </FormattedMessage>
            {provinces && provinces.map(province => <option key={province.id} value={province.id}>{province.provinceName}</option>)}
        </select>
    );
}

ProvinceSelector.propTypes = {
    selectProps: PropTypes.object
};

export default ProvinceSelector;