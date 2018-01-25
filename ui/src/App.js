import React from 'react';
import logo from './logo.svg';
import './App.css';
import Gos from './Gos'
import ApiService from './service/ApiService'
import GoForm from "./GoForm";

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      data: []
    };
    this.loadGos.bind(this)
  }

  componentDidMount() {
    this.loadGos()
  }

  loadGos() {
    ApiService.getGos().then(
        json => {
          this.setState({
            data: json
          })
        }
    );
  }

  render() {
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo"/>
            <h1 className="App-title">Welcome to React</h1>
          </header>
          <p className="App-intro">
            To get started, edit <code>src/App.js</code> and save to reload. No
          </p>
          <GoForm loadGos={this.loadGos.bind(this)}/>
          {
            <div>
              <h2>Gos</h2>
              <Gos gos={this.state.data}/>
            </div>
          }
        </div>
    );
  }
}

export default App;
