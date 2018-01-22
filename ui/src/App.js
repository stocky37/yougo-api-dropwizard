import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Gos from './Gos'

class App extends Component {

  componentDidMount() {
    fetch(`${process.env.REACT_APP_SERVICE_URL}/gos/me`)
    .then(response => response.json())
    .then(json => {
      this.setState({
        data: json
      });
    })
  }

  render() {
    // const x = (!this.state || !this.state.data) ? "" : this.state.data.global[0].description;

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload. No
        </p>
        {
          this.state && this.state.data &&
            <div>
              <Gos gos={this.state.data.global} />
              <Gos gos={this.state.data.personal} />
            </div>
        }
      </div>
    );
  }
}

export default App;
