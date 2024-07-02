import { useEffect } from 'react';
import { useDispatch } from 'react-redux';

import Header from './Header';
import Body from './Body';
import Footer from './Footer';
import users from '../../users';
import events from '../../events';

const App = () => {

    const dispatch = useDispatch();

    useEffect(() => {

        dispatch(users.actions.tryLoginFromServiceToken(
            () => dispatch(users.actions.logout())));

        dispatch(events.actions.findAllTypes());
        dispatch(events.actions.findAllProvinces());

    }, [dispatch]);

    return (
        <div>
            <Header />
            <Body />
            <Footer />
        </div>
    );

}

export default App;
