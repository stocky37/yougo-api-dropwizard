import { withStyles } from 'material-ui';
import { Add } from 'material-ui-icons';
import Button from 'material-ui/es/Button/Button';
import Dialog from 'material-ui/es/Dialog/Dialog';
import DialogActions from 'material-ui/es/Dialog/DialogActions';
import DialogContent from 'material-ui/es/Dialog/DialogContent';
import DialogTitle from 'material-ui/es/Dialog/DialogTitle';
import IconButton from 'material-ui/es/IconButton/IconButton';
import Tooltip from 'material-ui/es/Tooltip/Tooltip';
import TextField from 'material-ui/TextField/TextField';
import PropTypes from 'prop-types';
import React from 'react';

const styles = {
	root: {
		padding: 24,
	},
};

const CreateGoDialog = (props) => {
	const {
		classes, open, close, isOpen, onSubmit, onClose, onChange, data,
	} = props;
	return (
		<div className={classes.root}>
			<Tooltip title="Create new">
				<IconButton aria-label="Create new" onClick={open}>
					<Add />
				</IconButton>
			</Tooltip>

			<Dialog
				open={isOpen}
				onClose={onClose}
				aria-labelledby="create-new-form-dialog"
			>
				<DialogTitle id="create-new-form-dialog">Create new alias</DialogTitle>
				<DialogContent>
					<form onSubmit={onSubmit}>
						<div className={classes.fields}>
							<TextField
								name="alias"
								label="Alias"
								placeholder="google"
								value={data.alias}
								onChange={onChange}
								margin="normal"
							/>
							<br />
							<TextField
								name="href"
								label="Href"
								placeholder="http://www.google.com"
								value={data.href}
								onChange={onChange}
								margin="normal"
							/>
							<br />
							<TextField
								name="desc"
								label="Description"
								placeholder="Google"
								value={data.desc}
								onChange={onChange}
								margin="normal"
							/>
						</div>
						<div className={classes.actions}>
							<DialogActions>
								<Button color="secondary" onClick={close}>Cancel</Button>
								<Button type="submit" color="primary">Submit</Button>
							</DialogActions>
						</div>
					</form>
				</DialogContent>
			</Dialog>
		</div>
	);
};

CreateGoDialog.propTypes = {
	isOpen: PropTypes.bool,
	open: PropTypes.func.isRequired,
	close: PropTypes.func.isRequired,
	onChange: PropTypes.func.isRequired,
	onSubmit: PropTypes.func.isRequired,
	onClose: PropTypes.func.isRequired,
	data: PropTypes.shape({
		alias: PropTypes.string,
		href: PropTypes.string,
		desc: PropTypes.string,
	}).isRequired,
	classes: PropTypes.shape({
		root: PropTypes.string,
		fields: PropTypes.string,
		actions: PropTypes.string,
	}).isRequired,
};

CreateGoDialog.defaultProps = {
	isOpen: false,
};

export default withStyles(styles)(CreateGoDialog);
