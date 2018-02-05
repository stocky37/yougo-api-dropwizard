import Grid from 'material-ui/Grid';
import { createMuiTheme, MuiThemeProvider, withStyles } from 'material-ui/styles';
import PropTypes from 'prop-types';
import React from 'react';
import './App.css';
import GoForm from './GoForm';
import GoTable from './gos/GoTable';
import YougoAppBar from './menu/YougoAppBar';
import ApiService from './service/ApiService';

const theme = createMuiTheme();
const styles = {
	root: {
		padding: 16,
	},
	container: {
		flexGrow: 1,
	},
};

class App extends React.Component {
	constructor(props) {
		super(props);
		this.loadGos = this.loadGos.bind(this);
		this.state = {
			data: [],
		};
	}

	componentDidMount() {
		this.loadGos();
	}

	loadGos() {
		ApiService.getGos()
			.then((json) => {
				this.setState({
					data: json,
				});
			});
	}

	render() {
		const { classes } = this.props;
		return (
			<MuiThemeProvider theme={theme}>
				<YougoAppBar />
				<div className={classes.root}>
					<Grid container className={classes.container}>
						<Grid item xs={3} />
						<Grid item xs={6}>
							<GoForm loadGos={this.loadGos} />
						</Grid>
						<Grid item xs={3} />
					</Grid>
					<Grid container className={classes.container}>
						<GoTable gos={this.state.data} />
					</Grid>
				</div>
			</MuiThemeProvider>
		);
	}
}

App.propTypes = {
	classes: PropTypes.shape({
		root: PropTypes.string,
		container: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(App);
