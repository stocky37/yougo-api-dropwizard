import { combineReducers } from 'redux';
import { reducer as form } from 'redux-form';

import {
	CLOSE_CREATE_FORM,
	CREATE_ALIAS_REQUEST,
	CREATE_ALIAS_SUCCESS,
	INVALIDATE_ALIASES,
	LOAD_ALIASES_REQUEST,
	LOAD_ALIASES_SUCCESS,
	OPEN_CREATE_FORM,
} from './actions';

function aliasDialog(state = {
	open: false,
	loading: false,
}, action) {
	switch (action.type) {
		case OPEN_CREATE_FORM:
			return {
				...state,
				open: true,
			};
		case CLOSE_CREATE_FORM:
			return {
				...state,
				open: false,
			};
		case CREATE_ALIAS_REQUEST:
			return {
				...state,
				loading: true,
			};
		case CREATE_ALIAS_SUCCESS:
			return {
				...state,
				loading: false,
				created: action.json,
			};
		default:
			return state;
	}
}

function aliases(state = {
	error: false,
	loading: false,
	stale: false,
	items: [],
	dialog: {
		open: false,
	},
}, action) {
	switch (action.type) {
		case INVALIDATE_ALIASES:
			return {
				...state,
				stale: true,
			};
		case LOAD_ALIASES_REQUEST:
			return {
				...state,
				loading: true,
			};
		case LOAD_ALIASES_SUCCESS:
			return {
				...state,
				loading: false,
				stale: false,
				items: action.aliases,
				lastUpdated: action.receivedAt,
			};
		case CREATE_ALIAS_REQUEST:
		case OPEN_CREATE_FORM:
		case CLOSE_CREATE_FORM:
			return {
				...state,
				dialog: aliasDialog(state.dialog, action),
			};
		case CREATE_ALIAS_SUCCESS:
			return {
				...state,
				dialog: aliasDialog(state.dialog, action),
				items: [action.alias, ...state.items],
			};
		default:
			return state;
	}
}

function sections(state = {}, action) {
	switch (action.type) {
		case OPEN_CREATE_FORM:
		case CLOSE_CREATE_FORM:
		case INVALIDATE_ALIASES:
		case LOAD_ALIASES_REQUEST:
		case LOAD_ALIASES_SUCCESS:
		case CREATE_ALIAS_REQUEST:
		case CREATE_ALIAS_SUCCESS:
			return {
				...state,
				[action.section]: aliases(state[action.section], action),
			};
		default:
			return state;
	}
}

const reducer = combineReducers({
	sections,
	form,
});

export default reducer;
