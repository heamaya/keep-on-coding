package chapter21.exercise13;

import chapter21.datastructures.Stack;

public class PostfixEvaluator {

    private final static char RIGHT_PARENTHESIS = ')';
    private final static char EXPONENTIATION = '^';
    private final static char MULTIPLICATION = '*';
    private final static char DIVISION = '/';
    private final static char REMINDER = '%';
    private final static char SUBTRACTION = '-';
    private final static char ADDITION = '+';
    private final static char ZERO = '0';
    private final Stack<Integer> stack;

    public PostfixEvaluator() {
        stack = new Stack<>();
    }

    public int evaluate(String expression) {
        final Character [] characters = new StringBuilder(expression)
            .append(RIGHT_PARENTHESIS)
            .toString()
            .chars()
            .mapToObj(i -> Character.valueOf((char) i))
            .toArray(Character[]::new);
        int result = 0;
        for (Character character : characters) {
            if (Character.isDigit(character)) {
                stack.push(Character.getNumericValue(character) - Character.getNumericValue(ZERO));
            } else if (isOperator(character)) {
                final int firstPopped = stack.pop();
                final int secondPopped = stack.pop();
                stack.push(calculate(secondPopped, firstPopped, character));
            }
            else if(character.equals(RIGHT_PARENTHESIS)) {
                result = stack.pop();
            }
        }
        return result;
    }

    private Integer calculate(Integer value1, Integer value2, Character character) {
        int result = 0;
        if (character.equals(EXPONENTIATION)) {
            result = (int) Math.pow(value1, value2);
        }
        else if(character.equals(MULTIPLICATION)) {
            result = value1 * value2;
        }
        else if(character.equals(DIVISION)) {
            result = value1 / value2;
        }
        else if(character.equals(REMINDER)) {
            result = value1 % value2;
        }
        else if(character.equals(SUBTRACTION)) {
            result = value1 - value2;
        }
        else if(character.equals(ADDITION)) {
            result = value1 + value2;
        }
        return result;
    }

    private boolean isOperator(Character character) {
        return character.equals(EXPONENTIATION) || character.equals(MULTIPLICATION) ||
            character.equals(DIVISION) || character.equals(REMINDER) ||
            character.equals(SUBTRACTION) || character.equals(ADDITION);
    }

    public static void main(String [] args) {
        final PostfixEvaluator postfixEvaluator = new PostfixEvaluator();
        final String expression1 = "6 2 + 5 * 8 4 / -";
        final String expression2 = " 2 2 ^ 6 +";
        System.out.printf("%s = %d%n", expression1, postfixEvaluator.evaluate(expression1));
        System.out.printf("%s = %d%n", expression2, postfixEvaluator.evaluate(expression2));
    }

}
