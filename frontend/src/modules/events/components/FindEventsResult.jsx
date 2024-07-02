import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';
import * as actions from '../actions';
import {Pager} from '../../common';
import Events from './Events';

const FindEventsResult = () => {

    const eventSearch = useSelector(selectors.getEventSearch);
    const types = useSelector(selectors.getTypes);
    const provinces = useSelector(selectors.getProvinces);
    const dispatch = useDispatch();

    if (!eventSearch) {
        return null;
    }

    if (eventSearch.result.items.length === 0) {
        return (
            <div className="alert alert-danger" role="alert">
                <FormattedMessage id='project.events.FindEventsResult.noEventsFound'/>
            </div>
        );
    }

    return (

        <div>
            <Events events={eventSearch.result.items} types={types} provinces={provinces}/>
            <Pager
                back={{
                    enabled: eventSearch.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousFindEventsResultPage(eventSearch.criteria))}}
                next={{
                    enabled: eventSearch.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindEventsResultPage(eventSearch.criteria))}}/>
        </div>

    );

}

export default FindEventsResult;
