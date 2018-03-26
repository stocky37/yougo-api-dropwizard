import {ActionTypes} from '../../utils/constants';

export default (state = [], action) => {
	switch (action.type) {
		case ActionTypes.ALIASES_SUCCESS:
			return action.payload;
		case ActionTypes.CREATE_ALIAS_SUCCESS:
			return [action.payload, ...state];
		default:
			return state;
	}
};
