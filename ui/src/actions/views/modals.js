import {createAction} from 'redux-actions';
import {ActionTypes} from '../../utils/constants';

export const openModal = createAction(ActionTypes.MODAL_OPEN);
export const closeModal = createAction(ActionTypes.MODAL_CLOSE);
