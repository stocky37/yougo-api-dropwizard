import { Toolbar, withStyles } from 'material-ui';
import PropTypes from 'prop-types';
import React from 'react';

const styles = {};

const GoTableToolbar = (props) => {
	const { classes } = props;
	return (
		<Toolbar className={classes.root} />
	);
};

GoTableToolbar.propTypes = {
	classes: PropTypes.shape({
		root: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(GoTableToolbar);
