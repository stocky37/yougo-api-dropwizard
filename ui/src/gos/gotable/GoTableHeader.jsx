import { TableCell, TableHead, TableRow, withStyles } from 'material-ui';
import PropTypes from 'prop-types';
import React from 'react';

const styles = {};

const GoTableHeader = (props) => {
	const { classes } = props;
	return (
		<TableHead className={classes.root}>
			<TableRow className={classes.row}>
				<TableCell className={classes.aliasCell}>Alias</TableCell>
				<TableCell className={classes.hrefCell}>Href</TableCell>
				<TableCell className={classes.descCell}>Description</TableCell>
			</TableRow>
		</TableHead>
	);
};

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
