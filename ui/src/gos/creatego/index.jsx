import PropTypes from 'prop-types';
import React, { Component } from 'react';
import ApiService from '../../service/ApiService';
import CreateGoDialog from './CreateGoDialog';

const initialState = {
	isOpen: false,
	data: {
		alias: '',
		href: '',
		desc: '',
	},
};

export default class CreateGoDialogContainer extends Component {
	constructor(props) {
		super(props);
		this.state = initialState;

		this.onChange = this.onChange.bind(this);
		this.onClose = this.onClose.bind(this);
		this.onSubmit = this.onSubmit.bind(this);
		this.open = this.open.bind(this);
		this.close = this.close.bind(this);
	}

	onChange(event) {
		// setState() will not do a recursive merge, so hacking this in instead
		// we could also use each data piece as a separate state entry but that
		// could be unwieldy. See: https://stackoverflow.com/questions/18933985
		const tmp = Object.assign({}, this.state.data);
		tmp[event.target.name] = event.target.value;
		this.setState({ data: tmp });
	}

	onClose() {
		this.setState(initialState);
	}

	onSubmit(event) {
		const { alias, href, desc } = this.state.data;
		ApiService.addGo(alias, href, desc)
			.then(() => {
				this.setState(initialState);
				this.props.reload();
			});
		this.close();
		event.preventDefault();
	}

	open() {
		this.setState({ isOpen: true });
	}

	close() {
		this.setState({ isOpen: false });
	}

	render() {
		return (<CreateGoDialog
			isOpen={this.state.isOpen}
			open={this.open}
			close={this.close}
			onChange={this.onChange}
			onSubmit={this.onSubmit}
			onClose={this.onClose}
			data={this.state.data}
		/>);
	}
}

CreateGoDialogContainer.propTypes = {
	reload: PropTypes.func.isRequired,
};
