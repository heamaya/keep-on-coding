import React, { Component } from 'react';
import Validation from './Validation/Validation'
import Char from './Char/Char'
import './App.css';

class App extends Component {

    state = {
        text: '',
        textLength: 0
    }

    textChangeHandler = (event) => {
        this.setState({
            text: event.target.value,
            textLength: event.target.value.length
        })
    }

    deleteCharHandler = (charIndex) => {
        let chars = [...this.state.text.split('')];
        chars.splice(charIndex, 1);
        const text = chars.join('');
        this.setState({
            text: text,
            textLength: text.length
        })
    }

    render() {
        let chars = (
            <div>
                {this.state.text.split('').map((charElement, index) => {
                    return (<Char key={index} value={charElement} click={() => this.deleteCharHandler(index)}/>);
                })}
            </div>
        );
        return (
            <div className="App">
                <label htmlFor="text">Text: </label>
                <input id="text" type="text" value={this.state.text} onChange={this.textChangeHandler.bind(this)}/>
                <p>
                    Text Length: {this.state.textLength}
                </p>
                <Validation textLength={this.state.textLength}/>
                {chars}
            </div>
        );
    }
}

export default App;
