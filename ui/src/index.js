import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { applyMiddleware, createStore } from 'redux';
import thunkMiddleware from 'redux-thunk';
import { fetchAliasesIfNeeded } from './actions';
import './index.css';
import reducer from './reducers';
import registerServiceWorker from './registerServiceWorker';
import Yougo from './yougo';

let middleware = [thunkMiddleware];

// dev only middleware
if (process.env.NODE_ENV !== 'production') {
	let c = require('redux-logger');
	middleware = [...middleware, c.createLogger()]
}

const store = createStore(
	reducer,
	applyMiddleware(...middleware)
);

store.dispatch(fetchAliasesIfNeeded('global'));

ReactDOM.render(
	<Provider store={store}>
		<Yougo />
	</Provider>,
	document.getElementById('root')
);
registerServiceWorker();
