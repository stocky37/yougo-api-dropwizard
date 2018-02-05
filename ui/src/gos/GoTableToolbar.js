import { Toolbar, Typography, withStyles } from 'material-ui';
import PropTypes from 'prop-types';
import React from 'react';
import CreateGoDialog from './CreateGoDialog';

const styles = {
	title: {
		flex: '0 0 auto',
	},
	spacer: {
		flex: '1 1 100%',
	},
};

const GoTableToolbar = (props) => {
	const { classes, reload } = props;
	return (
		<Toolbar className={classes.root}>
			<div className={classes.title}>
				<Typography variant="title">Global Aliases</Typography>
			</div>
			<div className={classes.spacer} />
			<div className={classes.actions}>
				<CreateGoDialog reload={reload} />
			</div>
		</Toolbar>
	);
};

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
