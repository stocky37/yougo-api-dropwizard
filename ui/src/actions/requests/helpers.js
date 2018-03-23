import {batchActions} from 'redux-batched-actions';
import arr from '../../utils/arr';


// eslint-disable-next-line import/prefer-default-export
export const apiRequest = (
	request,
	onSuccess = [],
	onFailure = [],
) => async (dispatch) => {
	const response = await dispatch(request);
	return dispatch(batchActions(arr(response.error ? onFailure : onSuccess)));
};
