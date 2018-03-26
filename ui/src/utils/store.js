import {applyMiddleware, createStore} from 'redux';
import {apiMiddleware} from 'redux-api-middleware';
import {enableBatching} from 'redux-batched-actions';
import thunk from 'redux-thunk';
import reducer from '../reducers';

let middleware = [thunk, apiMiddleware];

// dev only middleware
if(process.env.NODE_ENV !== 'production') {
	const c = require('redux-logger');
	middleware = [...middleware, c.createLogger()];
}

const store = createStore(
	enableBatching(reducer),
	applyMiddleware(...middleware),
);

export default store;
