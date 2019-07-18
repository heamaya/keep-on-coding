import React from 'react';
import UserOutput from './UserOutput.css'

const userOutput = (props) => {
    return (
        <div>
            <pre className="user-ouput-poem-title">
                {`
                O Me! O Life!
                `}
            </pre>
            <pre className="user-output-poem">
                {`
                Oh me! Oh life! of the questions of these recurring,
                Of the endless trains of the faithless, of cities fill’d with the foolish,
                Of myself forever reproaching myself, (for who more foolish than I, and who more faithless?)
                Of eyes that vainly crave the light, of the objects mean, of the struggle ever renew’d,
                Of the poor results of all, of the plodding and sordid crowds I see around me,
                Of the empty and useless years of the rest, with the rest me intertwined,
                The question, O me! so sad, recurring — What good amid these, O me, O life?

                Answer.
                That you are here—that life exists and identity,
                That the powerful play goes on, and you may contribute a verse.
                `}
            </pre>
            <p className="user-output-poem-author">
                {props.value}
            </p>
        </div>
    );
}

export default userOutput