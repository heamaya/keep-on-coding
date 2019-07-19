import React from 'react';

const validation = (props) => {
    return (
        <p>
            {props.textLength > 5 ? 'Long enough' : 'Too short'}
        </p>);
}

export default validation;