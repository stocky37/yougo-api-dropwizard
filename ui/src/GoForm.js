import Button from 'material-ui/Button';
import { withStyles } from 'material-ui/styles';
import TextField from 'material-ui/TextField';
import PropTypes from 'prop-types';
import React, { Component } from 'react';
import ApiService from './service/ApiService';

const styles = {};
const initialState = {
	alias: '',
	href: '',
	desc: '',
};

class GoForm extends Component {
	constructor(props) {
		super(props);
		this.state = initialState;
		this.onSubmit = this.onSubmit.bind(this);
		this.onChange = this.onChange.bind(this);
	}

	onSubmit(event) {
		ApiService.addGo(this.state.alias, this.state.href, this.state.desc)
			.then(() => {
				this.setState(initialState);
				this.props.loadGos();
			});
		event.preventDefault();
	}

	onChange(event) {
		this.setState({
			[event.target.name]: event.target.value,
		});
	}

	render() {
		return (
			<form onSubmit={this.onSubmit}>
				<div>
					<TextField
						name="alias"
						label="Alias"
						placeholder="google"
						value={this.state.alias}
						onChange={this.onChange}
						margin="normal"
					/>
					<br />
					<TextField
						name="href"
						label="Href"
						placeholder="http://www.google.com"
						value={this.state.href}
						onChange={this.onChange}
						margin="normal"
					/>
					<br />
					<TextField
						name="desc"
						label="Description"
						placeholder="Google"
						value={this.state.desc}
						onChange={this.onChange}
						margin="normal"
					/>
					<br />
					<Button raised type="submit" color="primary">Submit</Button>
				</div>
			</form>
		);
	}
}

GoForm.propTypes = {
	loadGos: PropTypes.func.isRequired,
};

export default withStyles(styles)(GoForm);
