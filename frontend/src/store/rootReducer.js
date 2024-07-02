import { combineReducers } from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import events from '../modules/events';
import inscriptions from '../modules/inscriptions';

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    events: events.reducer,
    inscriptions: inscriptions.reducer
});

export default rootReducer;
