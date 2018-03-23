import withStyles from 'material-ui/es/styles/withStyles';
import TableCell from 'material-ui/es/Table/TableCell';
import TableRow from 'material-ui/es/Table/TableRow';
import Typography from 'material-ui/es/Typography/Typography';
import TableBody from 'material-ui/Table/TableBody';
import PropTypes from 'prop-types';
import React from 'react';

const styles = {};

const GoTableBody = ({ classes, gos }) => (
	<TableBody className={classes.root}>
		{gos.map(go => (
			<TableRow className={classes.row} key={go.id}>
				<TableCell className={classes.aliasCell}>
					<Typography>{go.alias}</Typography>
				</TableCell>
				<TableCell className={classes.hrefCell}>
					<Typography>{go.href}</Typography>
				</TableCell>
				<TableCell className={classes.descCell}>
					<Typography>{go.description}</Typography>
				</TableCell>
			</TableRow>))}
	</TableBody>
);

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
