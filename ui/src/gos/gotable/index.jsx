import { connect } from 'react-redux';
import { fetchAliasesIfNeeded, invalidateAliases } from '../../actions';
import GoTable from './GoTable';

const mapStateToProps = state => ({
	data: state.aliasesByType.global.items,
});

const mapDispatchToProps = dispatch => ({
	load: () => {
		dispatch(fetchAliasesIfNeeded('global'));
	},
	doInvalidate: () => {
		dispatch(invalidateAliases('global'));
	},
});

const GoTableContainer = connect(mapStateToProps, mapDispatchToProps)(GoTable);
export default GoTableContainer;
