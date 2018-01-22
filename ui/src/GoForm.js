import React, {Component} from 'react';
import ApiService from "./service/ApiService";

export default class GoForm extends Component {

  constructor(props) {
    super(props);
    this.state = {
      shortcut: '',
      href: '',
      desc: ''
    };
    this.onSubmit = this.onSubmit.bind(this);
    this.onChange = this.onChange.bind(this);
  }

  onSubmit(event) {
    ApiService.addGo(this.state.shortcut, this.state.href, this.state.desc);
    event.preventDefault();
  }

  onChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  render() {
    return (
        <form onSubmit={this.onSubmit}>
          <label>
            Shortcut
            <input type="text" name="shortcut" value={this.state.shortcut} onChange={this.onChange}/>
          </label>
          <label>
            Href
            <input type="text" name="href" value={this.state.href} onChange={this.onChange}/>
          </label>
          <label>
            Description
            <input type="text" name="desc" value={this.state.desc} onChange={this.onChange}/>
          </label>
          <button type="submit">
            Submit
          </button>
        </form>
    )
  }
}