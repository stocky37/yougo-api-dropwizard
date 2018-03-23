import Add from 'material-ui-icons/Add';
import Button from 'material-ui/es/Button/Button';
import Dialog from 'material-ui/es/Dialog/Dialog';
import DialogActions from 'material-ui/es/Dialog/DialogActions';
import DialogContent from 'material-ui/es/Dialog/DialogContent';
import DialogTitle from 'material-ui/es/Dialog/DialogTitle';
import IconButton from 'material-ui/es/IconButton/IconButton';
import withStyles from 'material-ui/es/styles/withStyles';
import Tooltip from 'material-ui/es/Tooltip/Tooltip';
import TextField from 'material-ui/TextField/TextField';
import PropTypes from 'prop-types';
import React from 'react';
import { formPropTypes } from 'redux-form';
import Field from 'redux-form/es/Field';

const styles = {
	root: {
		padding: 24,
	},
};

const renderTextField = ({
	input,
	label,
	placeholder,
}) => (
	<div>
		<TextField
			label={label}
			placeholder={placeholder}
			{...input}
		/>
	</div>
);

const CreateGoDialog = ({
	classes, actions, isOpen, handleSubmit,
}) => (
	<div className={classes.root}>
		<Tooltip title="Create new">
			<IconButton aria-label="Create new" onClick={actions.open}>
				<Add />
			</IconButton>
		</Tooltip>

		<Dialog
			open={isOpen}
			onClose={actions.close}
			onExit={actions.onClose}
			aria-labelledby="create-new-form-dialog"
		>
			<DialogTitle id="create-new-form-dialog">Create new alias</DialogTitle>
			<DialogContent>
				<form onSubmit={handleSubmit(actions.onSubmit)}>
					<div className={classes.fields}>
						<Field
							component={renderTextField}
							name="alias"
							label="Alias"
							placeholder="google"
						/>
						<Field
							component={renderTextField}
							name="href"
							label="Href"
							placeholder="http://www.google.com.au"
						/>
						<Field
							component={renderTextField}
							name="desc"
							label="Description"
							placeholder="Google"
						/>
					</div>
					<div className={classes.actions}>
						<DialogActions>
							<Button color="secondary" onClick={actions.close}>Cancel</Button>
							<Button type="submit" color="primary">Submit</Button>
						</DialogActions>
					</div>
				</form>
			</DialogContent>
		</Dialog>
	</div>
);

CreateGoDialog.propTypes = {
	...formPropTypes,
	actions: PropTypes.shape({
		open: PropTypes.func.isRequired,
		close: PropTypes.func.isRequired,
		onClose: PropTypes.func.isRequired,
	}).isRequired,
	isOpen: PropTypes.bool,
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
