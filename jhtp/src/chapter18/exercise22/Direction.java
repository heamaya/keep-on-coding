package chapter18.exercise22;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {
    RIGHT(1),
    LEFT(2),
    DOWN(3),
    UP(4);

    final int id;

    Direction(int id) {
        this.id = id;
    }

    public static Direction getById(int id) {
        final Optional<Direction> optionalDirection = Arrays.stream(Direction.values())
            .filter(v -> v.id == id)
            .findFirst();
        return optionalDirection.isPresent() ? optionalDirection.get() : null;
    }
}
