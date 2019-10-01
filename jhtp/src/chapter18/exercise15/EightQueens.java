package chapter18.exercise15;

import java.util.Arrays;

public class EightQueens {

    private static final int ZERO = 0;
    private static final int SIZE = 8;
    private static final String QUEEN = "Q";
    private static final String ASTERISC = "*";
    private final String board[][];

    public EightQueens() {
        board = new String[SIZE][SIZE];
        for (String[] row : board) {
            Arrays.fill(row, ASTERISC);
        }
        fillColumns(ZERO, ZERO, ZERO, ZERO);
    }

    private void fillColumns(int currentRow, int currentColumn, int previousRow, int previousColumn) {
        boolean move = isMove(currentRow, currentColumn);
        if (move) {
            board[previousRow][previousColumn] = ASTERISC;
            currentRow++;
            if (currentRow == SIZE) {
                previousRow = currentRow - 1;
                currentRow = ZERO;
                int aux = previousColumn;
                previousColumn = currentColumn;
                currentColumn = aux;
            } else {
                previousRow = currentRow - 1;
            }
            fillColumns(currentRow, currentColumn, previousRow, previousColumn);
        } else {
            board[currentRow][currentColumn] = QUEEN;
            currentColumn = (currentColumn + 1) % SIZE;
            if (countQueens() < 8) {
                fillColumns(currentRow, currentColumn, previousRow, previousColumn);
            }
        }
    }

    private boolean isMove(int currentRow, int currentColumn) {
        boolean move = false;
        move = isMoveRight(board[currentRow], currentColumn, move);
        move = isMoveLeft(board[currentRow], currentColumn, move);
        move = isMoveDown(currentRow, currentColumn, move);
        move = isMoveUp(currentRow, currentColumn, move);
        move = isMoveLowerRight(currentRow, currentColumn, move);
        move = isMoveUpperLeft(currentRow, currentColumn, move);
        move = isMoveUpperRight(currentRow, currentColumn, move);
        move = isMoveLowerLeft(currentRow, currentColumn, move);
        return move;
    }

    private boolean isMoveLowerLeft(int currentRow, int currentColumn, boolean move) {
        for (int c = currentColumn - 1, r = currentRow + 1; c >= ZERO && r < SIZE; c--, r++) {
            move = checkHasQueen(board[r][c], move);
        }
        return move;
    }

    private boolean isMoveUpperRight(int currentRow, int currentColumn, boolean move) {
        for (int c = currentColumn + 1, r = currentRow - 1; c < SIZE && r >= ZERO; c++, r--) {
            move = checkHasQueen(board[r][c], move);
        }
        return move;
    }

    private boolean isMoveUpperLeft(int currentRow, int currentColumn, boolean move) {
        for (int c = currentColumn - 1, r = currentRow -1; c >= ZERO && r >= ZERO; c--, r--) {
            move = checkHasQueen(board[r][c], move);
        }
        return move;
    }

    private boolean isMoveLowerRight(int currentRow, int currentColumn, boolean move) {
        for (int c = currentColumn + 1, r = currentRow + 1; c < SIZE && r < SIZE; c++, r++) {
            move = checkHasQueen(board[r][c], move);
        }
        return move;
    }

    private boolean isMoveUp(int currentRow, int currentColumn, boolean move) {
        for (int r = currentRow - 1; r >= ZERO; r--) {
            move = checkHasQueen(board[r][currentColumn], move);
        }
        return move;
    }

    private boolean isMoveDown(int currentRow, int currentColumn, boolean move) {
        for (int r = currentRow + 1; r < SIZE; r++) {
            move = checkHasQueen(board[r][currentColumn], move);
        }
        return move;
    }

    private boolean isMoveLeft(String[] strings, int currentColumn, boolean move) {
        for (int c = currentColumn - 1; c >= ZERO; c--) {
            move = checkHasQueen(strings[c], move);
        }
        return move;
    }

    private boolean isMoveRight(String[] strings, int currentColumn, boolean move) {
        for (int c = currentColumn + 1; c < SIZE; c++) {
            move = checkHasQueen(strings[c], move);
        }
        return move;
    }

    public int countQueens() {
        int count = 0;
        for (String [] row : board) {
            for (String column : row) {
                if(column.equals(QUEEN)) {
                    count++;
                }
            }
        }
        return count;
    }

    public void printBoard() {
        for (int r = ZERO; r < SIZE; r++) {
            System.out.println(Arrays.toString(board[r]));
        }
        System.out.println();
    }

    private boolean checkHasQueen(String s, boolean previousMove) {
        boolean move = previousMove;
        if (s.equals(QUEEN)) {
            move = true;
        }
        return move;
    }

    public static void main(String args[]) {
        final EightQueens eightQueens = new EightQueens();
        eightQueens.printBoard();
    }

}

