import React, { Component } from 'react';

export default class Test extends Component {
  componentDidMount() {
    fetch("http://localhost:8080/gos")
    .then(response => response.json())
    .then(json => {
      this.setState({
        data: json
      });
    })
  }

  render() {
    const x = (!this.state || !this.state.data) ? "" : this.state.data[0].description;
    return (
        <p>{x}</p>
    )
  }
}