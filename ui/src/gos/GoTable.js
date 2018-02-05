import { Table, withStyles } from 'material-ui';
import PropTypes from 'prop-types';
import React, { Component } from 'react';
import ApiService from '../service/ApiService';
import GoTableBody from './GoTableBody';
import GoTableHeader from './GoTableHeader';
import GoTableToolbar from './GoTableToolbar';

const styles = {};

class GoTable extends Component {
	constructor(props) {
		super(props);
		this.state = {
			data: [],
		};
		this.load = this.load.bind(this);
	}

	componentDidMount() {
		this.load();
	}

	load() {
		ApiService.getGos()
			.then((json) => {
				this.setState({
					data: json,
				});
			});
	}

	render() {
		const { classes } = this.props;
		return (
			<div>
				<GoTableToolbar reload={this.load} />
				<Table className={classes.root}>
					<GoTableHeader />
					<GoTableBody gos={this.state.data} />
				</Table>
			</div>
		);
	}
}

GoTable.propTypes = {
	classes: PropTypes.shape({
		root: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(GoTable);
