import { connect } from 'react-redux';
import reduxForm from 'redux-form/es/reduxForm';
import { closeCreateForm, openCreateForm, resetCreateForm } from '../../actions';
import CreateGoDialog from './CreateGoDialog';

const mapStateToProps = state => ({
	isOpen: state.sections.global.dialog.open,
});

const mapDispatchToProps = dispatch => ({
	open: () => {
		dispatch(openCreateForm('global'));
	},
	close: () => {
		dispatch(closeCreateForm('global'));
	},
	onClose: () => {
		dispatch(resetCreateForm('global'));
	},
});

const CreateGoDialogContainer = connect(mapStateToProps, mapDispatchToProps)(CreateGoDialog);

export default reduxForm({
	form: 'create-alias-global',
})(CreateGoDialogContainer);
