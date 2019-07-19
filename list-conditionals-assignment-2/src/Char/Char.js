import React from 'react';
import './Char.css'

const char = (props) => {
    return (
        <p className="char" onClick={props.click}>{props.value}</p>
    );
}

export default char;