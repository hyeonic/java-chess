package chess.domain;

import java.util.Arrays;

public enum PositionIndex {

    ZERO(0, "a", "8"),
    ONE(1, "b", "7"),
    TWO(2, "c", "6"),
    THREE(3, "d", "5"),
    FOUR(4, "e", "4"),
    FIVE(5, "f", "3"),
    SIX(6, "g", "2"),
    SEVEN(7, "h", "1"),
    ;

    private final int value;
    private final String col;
    private final String row;

    PositionIndex(int value, String col, String row) {
        this.value = value;
        this.col = col;
        this.row = row;
    }

    public static PositionIndex of(String position) {
        return Arrays.stream(values())
                .filter(value -> value.col.equals(position) || value.row.equals(position))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getValue() {
        return value;
    }
}
