import { TableBody, TableCell, TableRow, withStyles } from 'material-ui';
import PropTypes from 'prop-types';
import React from 'react';

const styles = {};

const GoTableBody = (props) => {
	const { classes, gos } = props;
	return (
		<TableBody className={classes.root}>
			{gos.map(go => (
				<TableRow className={classes.row} key={go.id}>
					<TableCell className={classes.aliasCell}>{go.alias}</TableCell>
					<TableCell className={classes.hrefCell}>{go.href}</TableCell>
					<TableCell className={classes.descCell}>{go.description}</TableCell>
				</TableRow>))}
		</TableBody>
	);
};

GoTableBody.propTypes = {
	gos: PropTypes.arrayOf(PropTypes.shape({
		alias: PropTypes.string,
		href: PropTypes.string,
		description: PropTypes.string,
	})).isRequired,
	classes: PropTypes.shape({
		root: PropTypes.string,
		row: PropTypes.string,
		aliasCell: PropTypes.string,
		hrefCell: PropTypes.string,
		descCell: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(GoTableBody);
