import {CssBaseline, MuiThemeProvider} from 'material-ui';
import React from 'react';
import theme from '../utils/theme';
import App from './App';

export default () => (
	<MuiThemeProvider theme={theme}>
		<CssBaseline />
		<App />
	</MuiThemeProvider>
);
