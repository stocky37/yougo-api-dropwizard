import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {getAliases} from '../../selectors';
import {getAliases as getAliasesAction} from '../../actions/requests';
import GoTable from './GoTable';

const mapStateToProps = state => ({
	data: getAliases(state),
});

const mapDispatchToProps = dispatch => ({
	actions: bindActionCreators({
		loadAliases: getAliasesAction,
	}, dispatch),
});

const GoTableContainer = connect(mapStateToProps, mapDispatchToProps)(GoTable);
export default GoTableContainer;
