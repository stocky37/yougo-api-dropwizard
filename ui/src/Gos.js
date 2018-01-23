import React, {Component} from 'react';

export default class Gos extends Component {
  render() {
    return (
        <table>
          <thead>
            <tr>
              <th>Shortcut</th>
              <th>Href</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
          {this.props.gos.map(function (go) {
            return <tr key={go.id}>
              <td>{go.alias}</td>
              <td>{go.href}</td>
              <td>{go.description}</td>
            </tr>
          })}
          </tbody>
        </table>
    )
  }
}