import * as actionTypes from '../actions';
import { Chance } from 'chance';

const chance = new Chance(7);
const initialState = {
    persons: []
}

const reducer = (state = initialState, action) => {
    let newState = null;
    switch (action.type) {
        case actionTypes.ADD_PERSON:
            newState = {
                ...state,
                persons: state.persons.concat({
                id: chance.guid(),
                name: chance.name(),
                age: chance.age()
            })};
            break;
        case actionTypes.REMOVE_PERSON:
            newState = {
                ...state,
                persons: state.persons.filter(result => result.id !== action.personId)
            }
            break;
        default:
            newState = state;
            break;
    }
    return newState;
}

export default reducer;

