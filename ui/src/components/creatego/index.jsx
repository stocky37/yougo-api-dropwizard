import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {reduxForm, reset} from 'redux-form';
import {submitAliasForm} from '../../actions/ui';
import {closeModal, openModal} from '../../actions/views/modals';
import {isModalOpen} from '../../selectors';
import {Modals} from '../../utils/constants';
import CreateGoDialog from './CreateGoDialog';

const form = `${Modals.CREATE_ALIAS}-form`;

const mapStateToProps = state => ({
	isOpen: isModalOpen(state, Modals.CREATE_ALIAS),
});

const mapDispatchToProps = dispatch => ({
	actions: bindActionCreators({
		open: () => (openModal(Modals.CREATE_ALIAS)),
		close: () => (closeModal(Modals.CREATE_ALIAS)),
		onClose: () => reset(form),
		onSubmit: values => submitAliasForm(values, Modals.CREATE_ALIAS),
	}, dispatch),
});

const CreateGoDialogContainer = connect(mapStateToProps, mapDispatchToProps)(CreateGoDialog);
export default reduxForm({form})(CreateGoDialogContainer);
