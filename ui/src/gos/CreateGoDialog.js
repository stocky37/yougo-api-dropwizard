import { withStyles } from 'material-ui';
import { Add } from 'material-ui-icons';
import Dialog from 'material-ui/es/Dialog/Dialog';
import DialogContent from 'material-ui/es/Dialog/DialogContent';
import DialogTitle from 'material-ui/es/Dialog/DialogTitle';
import IconButton from 'material-ui/es/IconButton/IconButton';
import Tooltip from 'material-ui/es/Tooltip/Tooltip';
import PropTypes from 'prop-types';
import React, { Component } from 'react';
import CreateGoForm from './CreateGoForm';

const styles = {};

class CreateGoDialog extends Component {
	constructor(props) {
		super(props);
		this.state = {
			open: false,
		};

		this.handleClickOpen = this.handleClickOpen.bind(this);
		this.handleClose = this.handleClose.bind(this);
	}

	handleClickOpen() {
		this.setState({ open: true });
	}

	handleClose() {
		this.setState({ open: false });
	}

	render() {
		const { classes, reload } = this.props;
		return (
			<div className={classes.root}>
				<Tooltip title="Create new">
					<IconButton aria-label="Create new" onClick={this.handleClickOpen}>
						<Add />
					</IconButton>
				</Tooltip>

				<Dialog
					open={this.state.open}
					onClose={this.handleClose}
					aria-labelledby="create-new-form-dialog"
				>
					<DialogTitle id="create-new-form-dialog">Create new alias</DialogTitle>
					<DialogContent>
						<CreateGoForm reload={reload} close={this.handleClose} />
					</DialogContent>
				</Dialog>
			</div>
		);
	}
}

CreateGoDialog.propTypes = {
	reload: PropTypes.func.isRequired,
	classes: PropTypes.shape({
		root: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(CreateGoDialog);
