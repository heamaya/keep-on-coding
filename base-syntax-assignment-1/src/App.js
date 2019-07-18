import React, { Component } from 'react';
import UserInput from './UserInput/UserInput'
import UserOutput from './UserOutput/UserOutput'
import './App.css';


class App extends Component {

  state = {
      username: "Walt Whitman"
  }

  changeUsernameHandler = (event) => {
      this.setState({
          username: event.target.value
      })
  }

  render() {
    return (
        <div className="App">
            <UserInput changed={this.changeUsernameHandler} value={this.state.username} />
            <UserOutput value={this.state.username}/>
        </div>
    );
  }
}

export default App;
