import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import Yougo from './yougo';

ReactDOM.render(<Yougo />, document.getElementById('root'));
registerServiceWorker();