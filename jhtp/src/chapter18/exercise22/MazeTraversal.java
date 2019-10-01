package chapter18.exercise22;

import java.security.SecureRandom;
import java.util.Arrays;

public class MazeTraversal {

    private static final String NUMBER = "#";
    private static final String DOT = ".";
    private static final String X = "X";
    private static final String O = "O";
    private static final int LOWER_BOUND = 0;
    private static final int MAXIMUM_SPOT_NUMBER = 225;
    private final SecureRandom secureRandom;
    private final int rowCount;
    private final int columnCount;
    private final int startRowIndex;
    private final int startColumnIndex;
    private final int endRowIndex;
    private final int endColumnIndex;
    private boolean hasExit;
    private String [][] maze;

    public MazeTraversal(int rowCount, int columnCount, int startRowIndex, int startColumnIndex, int endRowIndex, int endColumnIndex) {
        if (rowCount > 0) {
            this.rowCount = rowCount;
        }
        else {
            throw new IllegalArgumentException("rowCount must be greater than 0");
        }
        if (columnCount > 0) {
            this.columnCount = columnCount;
        } else {
            throw new IllegalArgumentException("columnCount must be greater than 0");
        }
        if (rowCount * columnCount <= MAXIMUM_SPOT_NUMBER) {

        } else {
            throw new IllegalArgumentException("spots cannot be greater than 225");
        }
        if (startRowIndex == LOWER_BOUND && startColumnIndex == LOWER_BOUND ||
            startRowIndex == LOWER_BOUND && startColumnIndex == columnCount - 1 ||
            startRowIndex == rowCount - 1 && startColumnIndex == LOWER_BOUND ||
            startRowIndex == rowCount - 1 && startColumnIndex == columnCount - 1) {
            throw new IllegalArgumentException("starting or ending spots cannot be upper or lower bounds");
        }
        if (startRowIndex >= LOWER_BOUND && startRowIndex < rowCount) {
            this.startRowIndex = startRowIndex;
        } else {
            throw new IllegalArgumentException("startRowIndex must be greater or equal to 0 and less than rowCount");
        }
        if (startColumnIndex >= LOWER_BOUND && startColumnIndex < columnCount) {
            this.startColumnIndex = startColumnIndex;
        } else {
            throw new IllegalArgumentException("startColumnIndex must be greater or equal to 0 and less than rowCount");
        }
        if (endRowIndex >= LOWER_BOUND && endRowIndex < rowCount) {
            this.endRowIndex = endRowIndex;
        } else {
            throw new IllegalArgumentException("endRowIndex must be greater or equal to 0 and less than rowCount");
        }
        if (endColumnIndex >= LOWER_BOUND && endColumnIndex < columnCount) {
            this.endColumnIndex = endColumnIndex;
        } else {
            throw new IllegalArgumentException("endColumnIndex must be greater or equal to 0 and less than rowCount");
        }
        secureRandom = new SecureRandom();
        hasExit = false;
    }

    public static void main(String [] args) {
        final MazeTraversal mazeTraversal = new MazeTraversal(12, 12, 0, 3, 11, 9);
        mazeTraversal.generateMaze();
        if (mazeTraversal.isHasExit()) {
            mazeTraversal.printMaze();
            mazeTraversal.traverse();
            System.out.println();
            mazeTraversal.printMaze();
        } else {
            mazeTraversal.clean();
            System.out.println("The generated maze has no exit.");
            System.out.println();
            mazeTraversal.printMaze();
        }
    }

    public void traverse() {
        traverseClockWise(startRowIndex, startColumnIndex,0);
    }

    private void traverseClockWise(int rowIndex, int columnIndex, int step) {
        if (isExit(rowIndex, columnIndex, step)) {
            markSpot(rowIndex, columnIndex, X);
        }
        else if (!maze[rowIndex][columnIndex].equals(NUMBER)) {
            walkClockWise(rowIndex, columnIndex, step, DOT, X);
        }
    }

    private void traverseCounterClockWise(int rowIndex, int columnIndex, int step) {
        if (isExit(rowIndex, columnIndex, step)) {
            markSpot(rowIndex, columnIndex, X);
        }
        else if (!maze[rowIndex][columnIndex].equals(NUMBER)) {
            walkCounterClockWise(rowIndex, columnIndex, step, DOT, X);
        }
    }

    private void walkClockWise(int rowIndex, int columnIndex, int step, String currentMark, String mark) {
        if (canMoveRight(rowIndex, columnIndex, currentMark) && isExit(rowIndex, columnIndex + 1, step + 1)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(rowIndex, ++columnIndex, ++step);
        }
        else if (canMoveDown(rowIndex, columnIndex, currentMark) && isExit(rowIndex + 1, columnIndex, step + 1)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(++rowIndex, columnIndex, ++step);
        }
        else if (canMoveLeft(rowIndex, columnIndex, currentMark) && isExit(rowIndex, columnIndex - 1, step + 1)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(rowIndex, --columnIndex, ++step);
        }
        else if (canMoveUp(rowIndex, columnIndex, currentMark) && isExit(rowIndex - 1, columnIndex, step + 1)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(--rowIndex, columnIndex, ++step);
        }
        else if (canMoveRight(rowIndex, columnIndex, currentMark)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(rowIndex, ++columnIndex, ++step);
        }
        else if (canMoveDown(rowIndex, columnIndex, currentMark)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(++rowIndex, columnIndex, ++step);
        }
        else if (canMoveLeft(rowIndex, columnIndex, currentMark)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(rowIndex, --columnIndex, ++step);
        }
        else if (canMoveUp(rowIndex, columnIndex, currentMark)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(--rowIndex, columnIndex, ++step);
        }
        else {
            if (currentMark.equals(DOT)) {
                markSpot(rowIndex, columnIndex, O);
                walkClockWise(rowIndex, columnIndex, step, X, O);
            } else if (currentMark.equals(O)){
                markSpot(rowIndex, columnIndex, O);
                walkClockWise(rowIndex, columnIndex, step, O, O);
            } else {
                clean();
                traverseCounterClockWise(startRowIndex, endColumnIndex, 0);
            }
        }
    }

    private void walkCounterClockWise(int rowIndex, int columnIndex, int step, String currentMark, String mark) {
        printMaze();
        System.out.println();
        if (canMoveLeft(rowIndex, columnIndex, currentMark) && isExit(rowIndex, columnIndex - 1, step + 1)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(rowIndex, --columnIndex, ++step);
        }
        else if (canMoveDown(rowIndex, columnIndex, currentMark) && isExit(rowIndex + 1, columnIndex, step + 1)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(++rowIndex, columnIndex, ++step);
        }
        else if (canMoveRight(rowIndex, columnIndex, currentMark) && isExit(rowIndex, columnIndex + 1, step + 1)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(rowIndex, ++columnIndex, ++step);
        }
        else if (canMoveUp(rowIndex, columnIndex, currentMark) && isExit(rowIndex - 1, columnIndex, step + 1)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(--rowIndex, columnIndex, ++step);
        }
        else if (canMoveLeft(rowIndex, columnIndex, currentMark)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(rowIndex, --columnIndex, ++step);
        }
        else if (canMoveDown(rowIndex, columnIndex, currentMark)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(++rowIndex, columnIndex, ++step);
        }
        else if (canMoveRight(rowIndex, columnIndex, currentMark)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(rowIndex, ++columnIndex, ++step);
        }
        else if (canMoveUp(rowIndex, columnIndex, currentMark)) {
            markSpot(rowIndex, columnIndex, mark);
            traverseClockWise(--rowIndex, columnIndex, ++step);
        }
        else {
            if (currentMark.equals(DOT)) {
                markSpot(rowIndex, columnIndex, O);
                walkCounterClockWise(rowIndex, columnIndex, step, X, O);
            } else if (currentMark.equals(O)){
                markSpot(rowIndex, columnIndex, O);
                walkCounterClockWise(rowIndex, columnIndex, step, O, O);
            }
        }
    }

    public void printMaze() {
        Arrays.stream(maze)
            .map(s -> String.join(" ", s))
            .forEach(System.out::println);
    }

    private boolean isExit(int rowIndex, int columnIndex, int step) {
        return isBound(rowIndex, columnIndex) && maze[rowIndex][columnIndex].equals(DOT) && step > 0;
    }

    private boolean isBound(int rowIndex, int columnIndex) {
        if (rowIndex == LOWER_BOUND || rowIndex == rowCount - 1 || columnIndex == LOWER_BOUND || columnIndex == columnCount - 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isOut(int rowIndex, int columnIndex) {
        if (rowIndex < LOWER_BOUND || rowIndex > rowCount - 1 || columnIndex < LOWER_BOUND || columnIndex > columnCount - 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean canMoveRight(int rowIndex, int columnIndex, String mark) {
        columnIndex++;
        if (!isOut(rowIndex, columnIndex) && maze[rowIndex][columnIndex].equals(mark)) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean canMoveLeft(int rowIndex, int columnIndex, String mark) {
        columnIndex--;
        if (!isOut(rowIndex, columnIndex) && maze[rowIndex][columnIndex].equals(mark)) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean canMoveUp(int rowIndex, int columnIndex, String mark) {
        rowIndex--;
        if (!isOut(rowIndex, columnIndex) && maze[rowIndex][columnIndex].equals(mark)) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean canMoveDown(int rowIndex, int columnIndex, String mark) {
        rowIndex++;
        if (!isOut(rowIndex, columnIndex) && maze[rowIndex][columnIndex].equals(mark)) {
            return true;
        }
        else {
            return false;
        }
    }

    private void cleanDeadEnds() {
        for (String [] row : maze) {
            for (int c = 0; c < row.length; c++) {
                if (row[c].equals(O)) {
                    row[c] = DOT;
                }
            }
        }
    }

    public void clean() {
        for (String [] row : maze) {
            for (int c = 0; c < row.length; c++) {
                if (row[c].equals(O) || row[c].equals(X)) {
                    row[c] = DOT;
                }
            }
        }
    }

    private void markSpot(int rowIndex, int columnIndex, String mark) {
        maze[rowIndex][columnIndex] = mark;
    }

    public void generateMaze() {
        maze = new String[rowCount][columnCount];
        for (String [] row : maze) {
            Arrays.fill(row, NUMBER);
        }
        generateMaze(startRowIndex, startColumnIndex, endRowIndex, endColumnIndex);
    }

    private void generateMaze(int rowIndex, int columnIndex, int endRowIndex, int endColumnIndex) {
        if (rowIndex == endRowIndex && columnIndex == endColumnIndex) {
            markSpot(rowIndex, columnIndex, DOT);
            cleanDeadEnds();
            setHasExit(true);
        }
        else if (maze[rowIndex][columnIndex].equals(NUMBER) || maze[rowIndex][columnIndex].equals(DOT)) {
            generateSpot(rowIndex, columnIndex, endRowIndex, endColumnIndex, DOT);
        }
    }

    private void generateSpot(int rowIndex, int columnIndex, int endRowIndex, int endColumnIndex, String mark) {
        if (columnIndex == LOWER_BOUND) {
            markSpot(rowIndex, columnIndex, mark);
            generateMaze(rowIndex, ++columnIndex, endRowIndex, endColumnIndex);
        }
        else if (columnIndex == columnCount - 1) {
            markSpot(rowIndex, columnIndex, mark);
            generateMaze(--rowIndex, columnIndex, endRowIndex, endColumnIndex);
        }
        else if (rowIndex == rowCount - 1) {
            markSpot(rowIndex, columnIndex, mark);
            generateMaze(rowIndex, --columnIndex, endRowIndex, endColumnIndex);
        }
        else if(rowIndex == LOWER_BOUND) {
            markSpot(rowIndex, columnIndex, mark);
            generateMaze(++rowIndex, columnIndex, endRowIndex, endColumnIndex);
        } else {
            final Direction direction = Direction.getById(secureRandom.nextInt(4) + 1);
            if ((direction.equals(Direction.RIGHT) && canGenerateRight(rowIndex, columnIndex, NUMBER) &&
                allowedMoveRightSides(rowIndex, columnIndex) || moveRightIsExit(rowIndex, columnIndex, endRowIndex, endColumnIndex))) {
                markSpot(rowIndex, columnIndex, mark);
                generateMaze(rowIndex, ++columnIndex, endRowIndex, endColumnIndex);
            }
            else if ((direction.equals(Direction.LEFT) && canGenerateLeft(rowIndex, columnIndex, NUMBER)
                && allowedMoveLeftSides(rowIndex, columnIndex)) || moveLeftIsExit(rowIndex, columnIndex, endRowIndex, endColumnIndex)) {
                markSpot(rowIndex, columnIndex, mark);
                generateMaze(rowIndex, --columnIndex, endRowIndex, endColumnIndex);
            }
            else if ((direction.equals(Direction.UP) && canGenerateUp(rowIndex, columnIndex, NUMBER)
                && allowedMoveUpSides(rowIndex, columnIndex)) || moveUpIsExit(rowIndex, columnIndex, endRowIndex, endColumnIndex)) {
                markSpot(rowIndex, columnIndex, mark);
                generateMaze(--rowIndex, columnIndex, endRowIndex, endColumnIndex);
            }
            else if (direction.equals(Direction.DOWN) && canGenerateDown(rowIndex, columnIndex, NUMBER)
                && allowedMoveDownSides(rowIndex, columnIndex) || moveDownIsExit(rowIndex, columnIndex, endRowIndex, endColumnIndex)) {
                markSpot(rowIndex, columnIndex, mark);
                generateMaze(++rowIndex, columnIndex, endRowIndex, endColumnIndex);
            }
            else if ((canGenerateRight(rowIndex, columnIndex, NUMBER) && allowedMoveRightSides(rowIndex, columnIndex))
                || moveRightIsExit(rowIndex, columnIndex, endRowIndex, endColumnIndex)) {
                markSpot(rowIndex, columnIndex, mark);
                generateMaze(rowIndex, ++columnIndex, endRowIndex, endColumnIndex);
            } else if ((canGenerateLeft(rowIndex, columnIndex, NUMBER) && allowedMoveLeftSides(rowIndex, columnIndex))
                || moveLeftIsExit(rowIndex, columnIndex, endRowIndex, endColumnIndex)) {
                markSpot(rowIndex, columnIndex, mark);
                generateMaze(rowIndex, --columnIndex, endRowIndex, endColumnIndex);
            } else if ((canGenerateUp(rowIndex, columnIndex, NUMBER) && allowedMoveUpSides(rowIndex, columnIndex))
                || moveUpIsExit(rowIndex, columnIndex, endRowIndex, endColumnIndex)) {
                markSpot(rowIndex, columnIndex, mark);
                generateMaze(--rowIndex, columnIndex, endRowIndex, endColumnIndex);
            } else if ((canGenerateDown(rowIndex, columnIndex, NUMBER) && allowedMoveDownSides(rowIndex, columnIndex))
                || moveDownIsExit(rowIndex, columnIndex, endRowIndex, endColumnIndex)) {
                markSpot(rowIndex, columnIndex, mark);
                generateMaze(++rowIndex, columnIndex, endRowIndex, endColumnIndex);
            } else {
                goBack(rowIndex, columnIndex, endRowIndex, endColumnIndex);
            }
        }
    }

    private boolean moveRightIsExit(int rowIndex, int columnIndex, int endRowIndex, int endColumnIndex) {
        columnIndex++;
        if (rowIndex == endRowIndex && columnIndex == endColumnIndex) {
            return true;
        } else {
            return false;
        }
    }

    private boolean moveLeftIsExit(int rowIndex, int columnIndex, int endRowIndex, int endColumnIndex) {
        columnIndex--;
        if (rowIndex == endRowIndex && columnIndex == endColumnIndex) {
            return true;
        } else {
            return false;
        }
    }

    private boolean moveUpIsExit(int rowIndex, int columnIndex, int endRowIndex, int endColumnIndex) {
        rowIndex--;
        if (rowIndex == endRowIndex && columnIndex == endColumnIndex) {
            return true;
        } else {
            return false;
        }
    }

    private boolean moveDownIsExit(int rowIndex, int columnIndex, int endRowIndex, int endColumnIndex) {
        rowIndex++;
        if (rowIndex == endRowIndex && columnIndex == endColumnIndex) {
            return true;
        } else {
            return false;
        }
    }

    private void goBack(int rowIndex, int columnIndex, int endRowIndex, int endColumnIndex) {
        if (canMoveRight(rowIndex, columnIndex, DOT)) {
            markSpot(rowIndex, columnIndex, O);
            generateMaze(rowIndex, ++columnIndex, endRowIndex, endColumnIndex);
        } else if (canMoveLeft(rowIndex, columnIndex, DOT)) {
            markSpot(rowIndex, columnIndex, O);
            generateMaze(rowIndex, --columnIndex, endRowIndex, endColumnIndex);
        } else if (canMoveUp(rowIndex, columnIndex, DOT)) {
            markSpot(rowIndex, columnIndex, O);
            generateMaze(--rowIndex, columnIndex, endRowIndex, endColumnIndex);
        } else if (canMoveDown(rowIndex, columnIndex, DOT)) {
            markSpot(rowIndex, columnIndex, O);
            generateMaze(++rowIndex, columnIndex, endRowIndex, endColumnIndex);
        }
    }

    private boolean allowedMoveRightSides(int rowIndex, int columnIndex) {
        if (!isOut(rowIndex, columnIndex + 1) &&
            !(maze[rowIndex + 1][columnIndex + 1].equals(DOT) && maze[rowIndex + 1][columnIndex].equals(DOT)) &&
            !(maze[rowIndex - 1][columnIndex + 1].equals(DOT) && maze[rowIndex - 1][columnIndex].equals(DOT)) &&
            !(maze[rowIndex + 1][columnIndex + 1].equals(O) && maze[rowIndex + 1][columnIndex].equals(O)) &&
            !(maze[rowIndex - 1][columnIndex + 1].equals(O) && maze[rowIndex - 1][columnIndex].equals(O))
        ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean allowedMoveLeftSides(int rowIndex, int columnIndex) {
        if (!isOut(rowIndex, columnIndex - 1) &&
            !(maze[rowIndex + 1][columnIndex - 1].equals(DOT) && maze[rowIndex + 1][columnIndex].equals(DOT)) &&
            !(maze[rowIndex - 1][columnIndex - 1].equals(DOT) && maze[rowIndex - 1][columnIndex].equals(DOT)) &&
            !(maze[rowIndex + 1][columnIndex - 1].equals(O) && maze[rowIndex + 1][columnIndex].equals(O)) &&
            !(maze[rowIndex - 1][columnIndex - 1].equals(O) && maze[rowIndex - 1][columnIndex].equals(O))
        ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean allowedMoveUpSides(int rowIndex, int columnIndex) {
        if (!isOut(rowIndex - 1, columnIndex) &&
            !(maze[rowIndex - 1][columnIndex - 1].equals(DOT) && maze[rowIndex][columnIndex - 1].equals(DOT)) &&
            !(maze[rowIndex - 1][columnIndex + 1].equals(DOT) && maze[rowIndex][columnIndex + 1].equals(DOT)) &&
            !(maze[rowIndex - 1][columnIndex - 1].equals(O) && maze[rowIndex][columnIndex - 1].equals(O)) &&
            !(maze[rowIndex - 1][columnIndex + 1].equals(O) && maze[rowIndex][columnIndex + 1].equals(O))
        ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean allowedMoveDownSides(int rowIndex, int columnIndex) {
        if (!isOut(rowIndex + 1, columnIndex) &&
            !(maze[rowIndex + 1][columnIndex - 1].equals(DOT) && maze[rowIndex][columnIndex - 1].equals(DOT)) &&
            !(maze[rowIndex + 1][columnIndex + 1].equals(DOT) && maze[rowIndex][columnIndex + 1].equals(DOT)) &&
            !(maze[rowIndex + 1][columnIndex - 1].equals(O) && maze[rowIndex][columnIndex - 1].equals(O)) &&
            !(maze[rowIndex + 1][columnIndex + 1].equals(O) && maze[rowIndex][columnIndex + 1].equals(O))
        ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean canGenerateRight(int rowIndex, int columnIndex, String mark) {
        columnIndex++;
        if (!isBound(rowIndex, columnIndex) && maze[rowIndex][columnIndex].equals(mark) && !maze[rowIndex][columnIndex + 1].equals(DOT)) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean canGenerateLeft(int rowIndex, int columnIndex, String mark) {
        columnIndex--;
        if (!isBound(rowIndex, columnIndex) && maze[rowIndex][columnIndex].equals(mark) && !maze[rowIndex][columnIndex - 1].equals(DOT)) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean canGenerateUp(int rowIndex, int columnIndex, String mark) {
        rowIndex--;
        if (!isBound(rowIndex, columnIndex) && maze[rowIndex][columnIndex].equals(mark) && !maze[rowIndex - 1][columnIndex].equals(DOT)) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean canGenerateDown(int rowIndex, int columnIndex, String mark) {
        rowIndex++;
        if (!isBound(rowIndex, columnIndex) && maze[rowIndex][columnIndex].equals(mark) && !maze[rowIndex + 1][columnIndex].equals(DOT)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isHasExit() {
        return hasExit;
    }

    public void setHasExit(boolean hasExit) {
        this.hasExit = hasExit;
    }
}
