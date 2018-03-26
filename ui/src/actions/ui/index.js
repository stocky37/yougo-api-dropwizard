import {reset} from 'redux-form';
import {createAlias} from '../requests';
import {apiRequest} from '../requests/helpers';
import {closeModal} from '../views/modals';

// eslint-disable-next-line import/prefer-default-export
export const submitAliasForm = (alias, source = null) => (dispatch) => {
	let onSuccess = [];

	// if the source was a modal, close the modal and reset the form
	if (source) {
		onSuccess = [
			...onSuccess,
			closeModal(source),
			reset(`${source}-form`),
		];
	}

	dispatch(apiRequest(
		createAlias(alias),
		onSuccess,
	));
};
