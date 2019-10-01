package chapter21.exercise12;

import chapter21.datastructures.Stack;

import java.util.Comparator;

public class InfixToPostfixConverter {

    private final static char LEFT_PARENTHESIS = '(';
    private final static char RIGHT_PARENTHESIS = ')';
    private final static char EXPONENTIATION = '^';
    private final static char MULTIPLICATION = '*';
    private final static char DIVISION = '/';
    private final static char REMINDER = '%';
    private final static char SUBTRACTION = '-';
    private final static char ADDITION = '+';
    private final static char SPACE = ' ';

    private final Stack<Character> stack;

    public InfixToPostfixConverter() {
        stack = new Stack<>();
    }

    public String convert(String expression) {
        stack.push(LEFT_PARENTHESIS);
        final Character [] characters = new StringBuilder(expression)
            .append(RIGHT_PARENTHESIS)
            .toString()
            .chars()
            .mapToObj(i -> Character.valueOf((char) i))
            .toArray(Character[]::new);
        final StringBuilder postfix = new StringBuilder();
        while (!stack.isEmpty()) {
            for (Character character : characters) {
                if (Character.isDigit(character)) {
                    postfix.append(character).append(SPACE);
                } else if (character.equals(LEFT_PARENTHESIS)) {
                    stack.push(character);
                } else if (isOperator(character)) {
                    while (!stack.isEmpty() && isOperator(stack.peek()) && precedence(stack.peek(), character)) {
                        postfix.append(stack.pop()).append(SPACE);
                    }
                    stack.push(character);
                } else if (character.equals(RIGHT_PARENTHESIS)) {
                    while (!stack.isEmpty() && !stack.peek().equals(LEFT_PARENTHESIS) && isOperator(stack.peek())) {
                        postfix.append(stack.pop()).append(SPACE);
                    }
                    stack.pop();
                }
            }
        }
        return postfix.toString();
    }

    private boolean isOperator(Character character) {
        return character.equals(EXPONENTIATION) || character.equals(MULTIPLICATION) ||
            character.equals(DIVISION) || character.equals(REMINDER) ||
            character.equals(SUBTRACTION) || character.equals(ADDITION);
    }

    private boolean precedence(Character operator1, Character operator2) {
        final Comparator<Character> operatorComparator = (Character o1, Character o2) -> {
            int compare = 0;
            if (o1.equals(EXPONENTIATION) && !o2.equals(EXPONENTIATION)) {
                compare = -1;
            }
            else if (o1.equals(MULTIPLICATION) || o1.equals(DIVISION) || o1.equals(REMINDER)) {
                if (o2.equals(EXPONENTIATION)) {
                    compare = 1;
                }
                else if (o2.equals(SUBTRACTION) || o2.equals(ADDITION)) {
                    compare = -1;
                }
            } else if (o1.equals(SUBTRACTION) || o1.equals(ADDITION)) {
                if (o2.equals(EXPONENTIATION) || o2.equals(MULTIPLICATION) || o2.equals(DIVISION) ||
                    o2.equals(REMINDER)) {
                    compare = 1;
                }
            }
            return compare;
        };
        return operatorComparator.compare(operator1, operator2) < 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public static void main(String [] args) {
        final InfixToPostfixConverter infixToPostfixConverter = new InfixToPostfixConverter();
        final String expression1 = "(6 + 2) * 5 - 8 / 4";
        final String expression2 = " 2^2 + 6";
        System.out.printf("Infix: %s%n", expression1);
        System.out.printf("Posfix: %s%n", infixToPostfixConverter.convert(expression1));
        System.out.printf("Infix: %s%n", expression2);
        System.out.printf("Posfix: %s%n", infixToPostfixConverter.convert(expression2));
    }

}
