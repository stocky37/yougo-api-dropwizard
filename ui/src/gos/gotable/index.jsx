import { connect } from 'react-redux';
import { createAlias } from '../../actions';
import GoTable from './GoTable';

const mapStateToProps = state => ({
	data: state.sections.global && state.sections.global.items,
});

const mapDispatchToProps = dispatch => ({
	onSubmit: values => dispatch(createAlias('global', { ...values })),
});

const GoTableContainer = connect(mapStateToProps, mapDispatchToProps)(GoTable);
export default GoTableContainer;
