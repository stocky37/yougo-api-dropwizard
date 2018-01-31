import React from 'react';
import Grid from 'material-ui/Grid';
import { createMuiTheme, MuiThemeProvider } from 'material-ui/styles';
import ApiService from './service/ApiService';
import Gos from './Gos';
import GoForm from './GoForm';
import './App.css';

const theme = createMuiTheme();

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
    return (
      <MuiThemeProvider theme={theme}>
        <div style={{ padding: 24 }}>
          <Grid container spacing={24}>
            <GoForm loadGos={this.loadGos} />
            <Gos gos={this.state.data} />
          </Grid>
          <Grid container spacing={24} />
        </div>
      </MuiThemeProvider>
    );
  }
}

export default App;
