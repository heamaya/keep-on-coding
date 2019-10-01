package chapter21.exercise14;

import chapter21.datastructures.Stack;

import java.util.Comparator;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfixToPostfixConverter {

    private final static String LEFT_PARENTHESIS = "(";
    private final static String RIGHT_PARENTHESIS = ")";
    private final static String EXPONENTIATION = "^";
    private final static String MULTIPLICATION = "*";
    private final static String DIVISION = "/";
    private final static String REMINDER = "%";
    private final static String SUBTRACTION = "-";
    private final static String ADDITION = "+";
    private final static String SPACE = " ";

    private final Stack<String> stack;

    public InfixToPostfixConverter() {
        stack = new Stack<>();
    }

    public String convert(String expression) {
        stack.push(LEFT_PARENTHESIS);
        expression = new StringBuilder(expression)
            .append(RIGHT_PARENTHESIS)
            .toString();
        final StringBuilder postfix = new StringBuilder();
        final Matcher termMatcher = Pattern.compile("[\\(]|[\\)]|[1-9][0-9]*|[\\^]|[\\*]|[\\/]|[\\%]|[\\-]|[\\+]")
            .matcher(expression);
        while (!stack.isEmpty()) {
            termMatcher
                .results()
                .map(MatchResult::group)
                .forEach(term -> {
                    if (isDigit(term)) {
                        postfix.append(term).append(SPACE);
                    } else if (term.equals(LEFT_PARENTHESIS)) {
                        stack.push(term);
                    } else if (isOperator(term)) {
                        while (!stack.isEmpty() && isOperator(stack.peek()) && precedence(stack.peek(), term)) {
                            postfix.append(stack.pop()).append(SPACE);
                        }
                        stack.push(term);
                    } else if (term.equals(RIGHT_PARENTHESIS)) {
                        while (!stack.isEmpty() && !stack.peek().equals(LEFT_PARENTHESIS) && isOperator(stack.peek())) {
                            postfix.append(stack.pop()).append(SPACE);
                        }
                        stack.pop();
                    }
                });
            }
        return postfix.toString();
    }

    private boolean isDigit(String term) {
        return term.matches("[1-9][0-9]*");
    }

    private boolean isOperator(String term) {
        return term.equals(EXPONENTIATION) || term.equals(MULTIPLICATION) ||
            term.equals(DIVISION) || term.equals(REMINDER) ||
            term.equals(SUBTRACTION) || term.equals(ADDITION);
    }

    private boolean precedence(String operator1, String operator2) {
        final Comparator<String> operatorComparator = (String o1, String o2) -> {
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
        final String expression1 = "(10 + 90) * 2 - 800 / 8";
        final String expression2 = " 10^2 + 5";
        System.out.printf("Infix: %s%n", expression1);
        System.out.printf("Posfix: %s%n", infixToPostfixConverter.convert(expression1));
        System.out.printf("Infix: %s%n", expression2);
        System.out.printf("Posfix: %s%n", infixToPostfixConverter.convert(expression2));
    }

}
