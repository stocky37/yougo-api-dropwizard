import { withStyles } from 'material-ui/styles';
import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';
import PropTypes from 'prop-types';
import React from 'react';

const styles = {};

const Gos = ({ gos }) => (
	<Table>
		<TableHead>
			<TableRow>
				<TableCell>Alias</TableCell>
				<TableCell>Href</TableCell>
				<TableCell>Description</TableCell>
			</TableRow>
		</TableHead>
		<TableBody>
			{gos.map(go => (
				<TableRow key={go.id}>
					<TableCell>{go.alias}</TableCell>
					<TableCell>{go.href}</TableCell>
					<TableCell>{go.description}</TableCell>
				</TableRow>))}
		</TableBody>
	</Table>
);

Gos.propTypes = {
	gos: PropTypes.arrayOf(PropTypes.object).isRequired,
};

export default withStyles(styles)(Gos);
