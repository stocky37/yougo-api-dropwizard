import withStyles from 'material-ui/es/styles/withStyles';
import Table from 'material-ui/es/Table/Table';
import PropTypes from 'prop-types';
import React from 'react';
import GoTableBody from './GoTableBody';
import GoTableHeader from './GoTableHeader';
import GoTableToolbar from './GoTableToolbar';

const styles = {};

const GoTable = ({
	classes, data, load, doInvalidate,
}) => (
	<div>
		<GoTableToolbar reload={load} doInvalidate={doInvalidate} />
		<Table className={classes.root}>
			<GoTableHeader />
			<GoTableBody gos={data} />
		</Table>
	</div>
);

GoTable.propTypes = {
	load: PropTypes.func.isRequired,
	doInvalidate: PropTypes.func.isRequired,
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
