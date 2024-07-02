import {useSelector} from 'react-redux';
import {Route, Routes} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import users from '../../users';
import EventDetails from "../../events/components/EventDetails.jsx";
import RegistrationCompleted from "../../inscriptions/components/RegistrationCompleted.jsx";
import FindInscriptionsResult from "../../inscriptions/components/FindInscriptionsResult.jsx";
import InscriptionRate from "../../inscriptions/components/InscriptionRate.jsx";

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Routes>
                <Route path="/*" element={<Home/>}/>
                <Route path="/events/event-details/:id" element={<EventDetails/>}/>
                {loggedIn && <Route path="/inscriptions/registration-completed" element={<RegistrationCompleted/>}/>}
                {loggedIn && <Route path="/inscriptions/find-inscriptions-result" element={<FindInscriptionsResult/>}/> }
                {loggedIn && <Route path="/inscriptions/rate" element={<InscriptionRate />}/>}
                {loggedIn && <Route path="/users/update-profile" element={<UpdateProfile/>}/>}
                {loggedIn && <Route path="/users/change-password" element={<ChangePassword/>}/>}
                {loggedIn && <Route path="/users/logout" element={<Logout/>}/>}
                {!loggedIn && <Route path="/users/login" element={<Login/>}/>}
                {!loggedIn && <Route path="/users/signup" element={<SignUp/>}/>}
            </Routes>
        </div>

    );

};

export default Body;
