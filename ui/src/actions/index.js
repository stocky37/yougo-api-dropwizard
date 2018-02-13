import { reset } from 'redux-form';
import ApiService from '../service/ApiService';

export const INVALIDATE_ALIASES = 'INVALIDATE_ALIASES';
export const LOAD_ALIASES_REQUEST = 'LOAD_ALIASES_FETCH';
export const LOAD_ALIASES_SUCCESS = 'LOAD_ALIASES_SUCCESS';
export const LOAD_ALIASES_ERROR = 'LOAD_ALIASES_ERROR';
export const CREATE_ALIAS_REQUEST = 'CREATE_ALIAS_REQUEST';
export const CREATE_ALIAS_SUCCESS = 'CREATE_ALIAS_SUCCESS';
export const CREATE_ALIAS_ERROR = 'CREATE_ALIAS_ERROR';
export const OPEN_CREATE_FORM = 'OPEN_CREATE_FORM';
export const CLOSE_CREATE_FORM = 'CLOSE_CREATE_FORM';

export const openCreateForm = section => ({
	type: OPEN_CREATE_FORM,
	section,
});

export const closeCreateForm = section => ({
	type: CLOSE_CREATE_FORM,
	section,
});

export const resetCreateForm = section => dispatch => dispatch(reset(`create-alias-${section}`));

export const invalidateAliases = section => ({
	type: INVALIDATE_ALIASES,
	section,
});

export const loadAliases = section => ({
	type: LOAD_ALIASES_REQUEST,
	section,
});

export const receiveAliases = (section, aliases) => ({
	type: LOAD_ALIASES_SUCCESS,
	section,
	aliases,
	receivedAt: Date.now(),
});

const fetchAliases = section => (dispatch) => {
	dispatch(loadAliases(section));
	return ApiService.getGos().then(
		response => response.json(),
		error => console.log('Error: ', error),
	).then(json => dispatch(receiveAliases(section, json)));
};

const shouldFetchAliases = (state, section) => {
	const aliases = state.sections[section];
	if (!aliases) {
		return true;
	} else if (aliases.loading) {
		return false;
	}
	return aliases.stale;
};

export const fetchAliasesIfNeeded = section => (dispatch, getState) => {
	if (shouldFetchAliases(getState(), section)) {
		return dispatch(fetchAliases(section));
	}
	return Promise.resolve();
};

const createAliasRequest = section => ({
	type: CREATE_ALIAS_REQUEST,
	section,
});

const createAliasSuccess = (section, alias) => ({
	type: CREATE_ALIAS_SUCCESS,
	section,
	alias,
});

export const createAlias = (section, alias) => (dispatch) => {
	dispatch(createAliasRequest(section));
	return ApiService.addGo(alias.alias, alias.href, alias.desc)
		.then(
			response => response.json(),
			error => console.log('Error: ', error),
		).then((json) => {
			dispatch(createAliasSuccess(section, json));
			dispatch(closeCreateForm(section));
		});
};

