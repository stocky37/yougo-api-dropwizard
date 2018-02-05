import { Table, withStyles } from 'material-ui';
import PropTypes from 'prop-types';
import React from 'react';
import GoTableBody from './GoTableBody';
import GoTableHeader from './GoTableHeader';
import GoTableToolbar from './GoTableToolbar';

const styles = {};

const Gos = (props) => {
	const { gos } = props;
	return (
		<div>
			<GoTableToolbar />
			<Table>
				<GoTableHeader />
				<GoTableBody gos={gos} />
			</Table>
		</div>
	);
};

Gos.propTypes = {
	gos: PropTypes.arrayOf(PropTypes.shape({
		alias: PropTypes.string,
		href: PropTypes.string,
		description: PropTypes.string,
	})).isRequired,
};

export default withStyles(styles)(Gos);
