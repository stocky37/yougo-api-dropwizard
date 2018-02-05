import Button from 'material-ui/Button';
import DialogActions from 'material-ui/es/Dialog/DialogActions';
import { withStyles } from 'material-ui/styles';
import TextField from 'material-ui/TextField';
import PropTypes from 'prop-types';
import React, { Component } from 'react';
import ApiService from '../service/ApiService';

const styles = {};
const initialState = {
	alias: '',
	href: '',
	desc: '',
};

class CreateGoForm extends Component {
	constructor(props) {
		super(props);
		this.state = initialState;
		this.onSubmit = this.onSubmit.bind(this);
		this.onChange = this.onChange.bind(this);
		this.onCancel = this.onCancel.bind(this);
		this.reset = this.reset.bind(this);
	}

	onSubmit(event) {
		ApiService.addGo(this.state.alias, this.state.href, this.state.desc)
			.then(() => {
				this.setState(initialState);
				this.props.reload();
			});
		this.props.close();
		event.preventDefault();
	}

	onChange(event) {
		this.setState({
			[event.target.name]: event.target.value,
		});
	}

	onCancel() {
		this.reset();
		this.props.close();
	}

	reset() {
		this.setState(initialState);
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
					<DialogActions>
						<Button color="secondary" onClick={this.onCancel}>Cancel</Button>
						<Button type="submit" color="primary">Submit</Button>
					</DialogActions>
				</div>
			</form>
		);
	}
}

CreateGoForm.propTypes = {
	reload: PropTypes.func.isRequired,
	close: PropTypes.func.isRequired,
};

export default withStyles(styles)(CreateGoForm);
