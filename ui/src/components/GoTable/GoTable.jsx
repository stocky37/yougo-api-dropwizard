import withStyles from 'material-ui/es/styles/withStyles';
import Table from 'material-ui/es/Table/Table';
import PropTypes from 'prop-types';
import React, {Component} from 'react';
import GoTableBody from './GoTableBody';
import GoTableHeader from './GoTableHeader';
import GoTableToolbar from './GoTableToolbar';

const styles = {};

class GoTable extends Component {
	componentDidMount() {
		return this.props.actions.loadAliases();
	}

	render() {
		const {classes, data} = this.props;
		return (
			<div>
				<GoTableToolbar />
				<Table className={classes.root}>
					<GoTableHeader />
					<GoTableBody gos={data} />
				</Table>
			</div>
		);
	}
}


GoTable.propTypes = {
	actions: PropTypes.shape({
		loadAliases: PropTypes.func.isRequired,
	}).isRequired,
	data: PropTypes.arrayOf(PropTypes.shape({
		id: PropTypes.string,
		alias: PropTypes.string,
		href: PropTypes.string,
		description: PropTypes.string,
	})).isRequired,
	classes: PropTypes.shape({
		root: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(GoTable);
