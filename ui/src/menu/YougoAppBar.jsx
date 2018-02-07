import MenuIcon from 'material-ui-icons/Menu';
import AppBar from 'material-ui/AppBar';
import Button from 'material-ui/Button';
import withStyles from 'material-ui/es/styles/withStyles';
import IconButton from 'material-ui/IconButton';
import Toolbar from 'material-ui/Toolbar';
import Typography from 'material-ui/Typography';
import PropTypes from 'prop-types';
import React from 'react';

const styles = {
	root: {
		width: '100%',
	},
	flex: {
		flex: 1,
	},
	menuButton: {
		marginLeft: -12,
		marginRight: 20,
	},
};

function YougoAppBar(props) {
	const { classes } = props;
	return (
		<div className={classes.root}>
			<AppBar position="static">
				<Toolbar>
					<IconButton className={classes.menuButton} color="inherit" aria-label="Menu">
						<MenuIcon />
					</IconButton>
					<Typography type="title" color="inherit" className={classes.flex}>
						Title
					</Typography>
					<Button color="inherit">Login</Button>
				</Toolbar>
			</AppBar>
		</div>
	);
}

YougoAppBar.propTypes = {
	classes: PropTypes.shape({
		root: PropTypes.string,
		menuButton: PropTypes.string,
		flex: PropTypes.string,
	}).isRequired,
};

export default withStyles(styles)(YougoAppBar);
