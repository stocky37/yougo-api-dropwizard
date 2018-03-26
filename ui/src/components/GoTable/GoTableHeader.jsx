import withStyles from 'material-ui/es/styles/withStyles';
import TableCell from 'material-ui/es/Table/TableCell';
import TableRow from 'material-ui/es/Table/TableRow';
import TableHead from 'material-ui/Table/TableHead';
import PropTypes from 'prop-types';
import React from 'react';

const styles = {};

const GoTableHeader = ({ classes }) => (
	<TableHead className={classes.root}>
		<TableRow className={classes.row}>
			<TableCell className={classes.aliasCell}>Alias</TableCell>
			<TableCell className={classes.hrefCell}>Href</TableCell>
			<TableCell className={classes.descCell}>Description</TableCell>
		</TableRow>
	</TableHead>
);

GoTableHeader.propTypes = {
	classes: PropTypes.shape({
		root: PropTypes.string,
		row: PropTypes.string,
		aliasCell: PropTypes.string,
		hrefCell: PropTypes.string,
		descCell: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(GoTableHeader);
