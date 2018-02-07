import React from 'react';
import ReactDOM from 'react-dom';
import Yougo from './index';

it('renders without crashing', () => {
	const div = document.createElement('div');
	ReactDOM.render(<Yougo />, div);
	ReactDOM.unmountComponentAtNode(div);
});
