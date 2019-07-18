import React from 'react';

const labelStyle = {
    color: "blue",
    fontWeight: "bold"
}
const inputStyle = {
    border: "2px solid blue",
    backgroundColor: "azure",
    color: "cornflowerblue",
    fontWeight: "bold"
}
const userInput = (props) => {
    return (
        <div >
            <label htmlFor="username-input" style={labelStyle}>Username: </label>
            <input id="username-input" type="text" style={inputStyle} onChange={props.changed} value={props.value} />
        </div>
    );
}

export default userInput