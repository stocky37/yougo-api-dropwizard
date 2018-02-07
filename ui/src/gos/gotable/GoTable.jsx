import { Table, withStyles } from 'material-ui';
import PropTypes from 'prop-types';
import React from 'react';
import GoTableBody from './GoTableBody';
import GoTableHeader from './GoTableHeader';
import GoTableToolbar from './GoTableToolbar';

const styles = {};

const GoTable = (props) => {
	const { classes, data, load } = props;
	return (
		<div>
			<GoTableToolbar reload={load} />
			<Table className={classes.root}>
				<GoTableHeader />
				<GoTableBody gos={data} />
			</Table>
		</div>
	);
};

GoTable.propTypes = {
	load: PropTypes.func.isRequired,
	data: PropTypes.arrayOf(PropTypes.shape({
		alias: PropTypes.string,
		href: PropTypes.string,
		description: PropTypes.string,
	})).isRequired,
	classes: PropTypes.shape({
		root: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(GoTable);
