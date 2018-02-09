import { combineReducers } from 'redux';
import { ALIASES_FETCH, ALIASES_SUCCESS, INVALIDATE_ALIASES } from './actions';

function aliases(state = {
	error: false,
	loading: false,
	stale: false,
	items: [],
}, action) {
	switch (action.type) {
		case INVALIDATE_ALIASES:
			return Object.assign({}, state, {
				stale: true,
			});
		case ALIASES_FETCH:
			return Object.assign({}, state, {
				loading: true,
			});
		case ALIASES_SUCCESS:
			return Object.assign({}, state, {
				loading: false,
				stale: false,
				items: action.aliases,
				lastUpdated: action.receivedAt,
			});
		default:
			return state;
	}
}

function aliasesByType(state = {}, action) {
	switch (action.type) {
		case INVALIDATE_ALIASES:
		case ALIASES_FETCH:
		case ALIASES_SUCCESS:
			return Object.assign({}, state, {
				[action.aliasType]: aliases(state[action.aliasType], action),
			});
		default:
			return state;
	}
}

const reducer = combineReducers({
	aliasesByType,
});

export default reducer;
