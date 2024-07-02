const getModuleState = state => state.inscriptions;
export const getLastInscriptionInfo = state => getModuleState(state).lastInscriptionInfo;
export const getGiveDorsalInfo = state => getModuleState(state).giveDorsalInfo;
export const getInscriptionHistory = state => getModuleState(state).inscriptionsSearch;
export const getRateInfo = state => getModuleState(state).rateInfo;