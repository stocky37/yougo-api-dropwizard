import Grid from 'material-ui/es/Grid/Grid';
import withStyles from 'material-ui/es/styles/withStyles';
import { createMuiTheme } from 'material-ui/styles/index';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import PropTypes from 'prop-types';
import React from 'react';
import GoTableContainer from '../gos/gotable';
import YougoAppBar from '../menu/YougoAppBar';

const styles = {
	root: {
		padding: 16,
	},
	container: {
		flexGrow: 1,
	},
};

const theme = createMuiTheme();

const Yougo = (props) => {
	const { classes } = props;
	return (
		<MuiThemeProvider theme={theme}>
			<YougoAppBar />
			<div className={classes.root}>
				<Grid container className={classes.container}>
					<Grid item lg={3} />
					<Grid item lg={6}>
						<GoTableContainer />
					</Grid>
					<Grid item lg={3} />
				</Grid>
			</div>
		</MuiThemeProvider>
	);
};

Yougo.propTypes = {
	classes: PropTypes.shape({
		root: PropTypes.string,
		container: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(Yougo);
