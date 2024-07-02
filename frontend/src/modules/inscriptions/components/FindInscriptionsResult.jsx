import { useDispatch, useSelector } from "react-redux";

import * as actions from '../actions.js';
import * as selectors from '../selectors.js';
import { useEffect } from "react";
import { FormattedMessage } from "react-intl";
import InscriptionHistory from "./InscriptionHistory.jsx";
import { Pager } from "../../common/index.js";

const FindInscriptionsResult = () => {
    const inscriptions = useSelector(selectors.getInscriptionHistory);
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(actions.seeInscriptionsHistory({ page: 0 }));

        return () => dispatch((actions.clearInscriptionSearch()));
    }, [dispatch]);

    if (!inscriptions) {
        return null;
    }

    if (inscriptions.result.items.length === 0) {
        return (
            <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.inscriptions.SeeInscriptionHistory.noInscriptions' />
            </div>
        );
    }
    return (

        <div className=" text-center">
            <h4><FormattedMessage id='project.inscriptions.header.inscriptions' /></h4>
            <br />
            <InscriptionHistory inscriptions={inscriptions.result.items} />
            <Pager
                back={{
                    enabled: inscriptions.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousSeeInscriptionsResultPage(inscriptions.criteria))
                }}
                next={{
                    enabled: inscriptions.result.existMoreItems,
                    onClick: () => dispatch(actions.nextSeeInscriptionsResultPage(inscriptions.criteria))
                }} />
        </div>

    );
}

export default FindInscriptionsResult;