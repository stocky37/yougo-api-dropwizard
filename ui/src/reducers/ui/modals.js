import {ActionTypes, Modals} from '../../utils/constants';

const defaultModalState = {
	open: false,
};

const defaultState = {};
Object.keys(Modals).forEach((k) => {
	defaultState[Modals[k]] = defaultModalState;
});

const handleModal = (state = defaultModalState, action) => {
	switch(action.type) {
		case ActionTypes.MODAL_OPEN:
			return {
				...state,
				open: true,
			};
		case ActionTypes.MODAL_CLOSE:
			return {
				...state,
				open: false,
			};
		default:
			return state;
	}
};

export default (state = defaultState, action) => {
	switch(action.type) {
		case ActionTypes.MODAL_OPEN:
		case ActionTypes.MODAL_CLOSE: {
			const modal = action.payload;
			return {
				...state,
				[modal]: handleModal(state[modal], action),
			};
		}
		default:
			return state;
	}
};

