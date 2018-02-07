import withStyles from 'material-ui/es/styles/withStyles';
import Toolbar from 'material-ui/es/Toolbar/Toolbar';
import Typography from 'material-ui/es/Typography/Typography';
import PropTypes from 'prop-types';
import React from 'react';
import CreateGoDialogContainer from '../creatego';

const styles = {
	title: {
		flex: '0 0 auto',
	},
	spacer: {
		flex: '1 1 100%',
	},
};

const GoTableToolbar = ({ classes, reload }) => (
	<Toolbar className={classes.root}>
		<div className={classes.title}>
			<Typography variant="title">Global Aliases</Typography>
		</div>
		<div className={classes.spacer} />
		<div className={classes.actions}>
			<CreateGoDialogContainer reload={reload} />
		</div>
	</Toolbar>
);

GoTableToolbar.propTypes = {
	reload: PropTypes.func.isRequired,
	classes: PropTypes.shape({
		root: PropTypes.string,
		title: PropTypes.string,
		spacer: PropTypes.string,
		actions: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(GoTableToolbar);
