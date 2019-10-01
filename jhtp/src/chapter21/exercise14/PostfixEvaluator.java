package chapter21.exercise14;

import chapter21.datastructures.Stack;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostfixEvaluator {

    private final static String RIGHT_PARENTHESIS = ")";
    private final static String EXPONENTIATION = "^";
    private final static String MULTIPLICATION = "*";
    private final static String DIVISION = "/";
    private final static String REMINDER = "%";
    private final static String SUBTRACTION = "-";
    private final static String ADDITION = "+";
    private final Stack<Integer> stack;

    public PostfixEvaluator() {
        stack = new Stack<>();
    }

    public int evaluate(String expression) {
        expression = new StringBuilder(expression)
            .append(RIGHT_PARENTHESIS)
            .toString();
        final Matcher termMatcher = Pattern.compile("[\\(]|[\\)]|[1-9][0-9]*|[\\^]|[\\*]|[\\/]|[\\%]|[\\-]|[\\+]")
            .matcher(expression);
        termMatcher
            .results()
            .map(MatchResult::group)
            .forEach(term -> {
                if (isDigit(term)) {
                    stack.push(Integer.valueOf(term));
                } else if (isOperator(term)) {
                    final int firstPopped = stack.pop();
                    final int secondPopped = stack.pop();
                    stack.push(calculate(secondPopped, firstPopped, term));
                }
            });
        return stack.pop();
    }

    private boolean isDigit(String term) {
        return term.matches("[1-9][0-9]*");
    }

    private Integer calculate(Integer value1, Integer value2, String term) {
        int result = 0;
        if (term.equals(EXPONENTIATION)) {
            result = (int) Math.pow(value1, value2);
        }
        else if(term.equals(MULTIPLICATION)) {
            result = value1 * value2;
        }
        else if(term.equals(DIVISION)) {
            result = value1 / value2;
        }
        else if(term.equals(REMINDER)) {
            result = value1 % value2;
        }
        else if(term.equals(SUBTRACTION)) {
            result = value1 - value2;
        }
        else if(term.equals(ADDITION)) {
            result = value1 + value2;
        }
        return result;
    }

    private boolean isOperator(String term) {
        return term.equals(EXPONENTIATION) || term.equals(MULTIPLICATION) ||
            term.equals(DIVISION) || term.equals(REMINDER) ||
            term.equals(SUBTRACTION) || term.equals(ADDITION);
    }

    public static void main(String [] args) {
        final PostfixEvaluator postfixEvaluator = new PostfixEvaluator();
        final String expression1 = "10 90 + 2 * 800 8 / -";
        final String expression2 = " 10 2 ^ 5 +";
        System.out.printf("%s = %d%n", expression1, postfixEvaluator.evaluate(expression1));
        System.out.printf("%s = %d%n", expression2, postfixEvaluator.evaluate(expression2));
    }

}
