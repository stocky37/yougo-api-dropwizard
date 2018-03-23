import {Grid, withStyles} from 'material-ui';
import PropTypes from 'prop-types';
import React from 'react';
import AppBar from './AppBar';
import GoTableContainer from './GoTable/index';

const styles = {
	root: {},
	container: {
		padding: 16,
		flexGrow: 1,
	},
};

const App = (props) => {
	const {classes} = props;
	return (
		<div className={classes.root}>
			<AppBar />
			<Grid container className={classes.container}>
				<Grid item lg={3} />
				<Grid item lg={6}>
					<GoTableContainer />
				</Grid>
				<Grid item lg={3} />
			</Grid>
		</div>
	);
};

App.propTypes = {
	classes: PropTypes.shape({
		root: PropTypes.string,
		container: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(App);
