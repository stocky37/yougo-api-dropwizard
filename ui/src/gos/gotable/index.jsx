import React, { Component } from 'react';
import ApiService from '../../service/ApiService';
import GoTable from './GoTable';

export default class GoTableContainer extends Component {
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
		return (
			<GoTable load={this.load} data={this.state.data} />
		);
	}
}
