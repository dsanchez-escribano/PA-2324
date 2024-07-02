import * as actions from './actions';
import * as actionTypes from './actionTypes';
import reducer from './reducer';
import * as selectors from './selectors';

export {default as FindEvents} from './components/FindEvents';
export {default as FindEventsResult} from './components/FindEventsResult';
/*export {default as EventsDetails} from './components/ProductDetails';*/

export default {actions, actionTypes, reducer, selectors};