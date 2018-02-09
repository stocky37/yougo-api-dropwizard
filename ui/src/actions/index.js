import ApiService from '../service/ApiService';

export const INVALIDATE_ALIASES = 'INVALIDATE_ALIASES';
export const ALIASES_FETCH = 'LOAD_ALIASES_FETCH';
export const ALIASES_SUCCESS = 'LOAD_ALIASES_SUCCESS';
export const ALIASES_ERROR = 'LOAD_ALIASES_ERROR';
export const CREATE_ALIAS_FETCH = 'CREATE_ALIAS_FETCH';
export const CREATE_ALIAS_SUCCESS = 'CREATE_ALIAS_SUCCESS';
export const CREATE_ALIAS_ERROR = 'CREATE_ALIAS_ERROR';

export const invalidateAliases = aliasType => ({
	type: INVALIDATE_ALIASES,
	aliasType,
});

export const loadAliases = aliasType => ({
	type: ALIASES_FETCH,
	aliasType,
});

export const receiveAliases = (aliasType, aliases) => ({
	type: ALIASES_SUCCESS,
	aliasType,
	aliases,
	receivedAt: Date.now(),
});

// export const createAlias()

const fetchAliases = type => (dispatch) => {
	dispatch(loadAliases(type));
	return ApiService.getGos().then(
		response => response.json(),
		error => console.log('Error: ', error),
	).then(json => dispatch(receiveAliases(type, json)));
};

const shouldFetchAliases = (state, aliasType) => {
	const aliases = state.aliasesByType[aliasType];
	if (!aliases) {
		return true;
	} else if (aliases.loading) {
		return false;
	}
	return aliases.stale;
};

export const fetchAliasesIfNeeded = type => (dispatch, getState) => {
	if (shouldFetchAliases(getState(), type)) {
		return dispatch(fetchAliases(type));
	}
	return Promise.resolve();
};
