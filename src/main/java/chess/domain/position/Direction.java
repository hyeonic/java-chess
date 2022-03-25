package chess.domain.position;

import java.util.Arrays;

public enum Direction {

    RIGHT(0, 1, false),
    LEFT(0, -1, false),
    TOP(-1, 0, false),
    BOTTOM(1, 0, false),
    TOP_RIGHT(-1, 1, true),
    TOP_LEFT(-1, -1, true),
    BOTTOM_RIGHT(1, 1, true),
    BOTTOM_LEFT(1, -1, true)
    ;

    private final int row;
    private final int col;
    private final boolean isDiagonal;

    Direction(int row, int col, boolean isDiagonal) {
        this.row = row;
        this.col = col;
        this.isDiagonal = isDiagonal;
    }

    public static Direction of(Position source, Position target) {
        int rowWeight = calculateWeight(source.getRankIndex() - target.getRankIndex());
        int colWeight = calculateWeight(source.getFileIndex() - target.getFileIndex());

        return Arrays.stream(values())
                .filter(direction -> direction.row == rowWeight)
                .filter(direction -> direction.col == colWeight)
                .filter(direction -> direction.isDiagonal == isDiagonal(source, target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("불가능한 이동 방향입니다."));
    }

    private static int calculateWeight(int value) {
        return Integer.compare(0, value);
    }

    private static boolean isDiagonal(Position source, Position target) {
        int absRankIndex = Math.abs(source.getRankIndex() - target.getRankIndex());
        int absFileIndex = Math.abs(source.getFileIndex() - target.getFileIndex());

        return absRankIndex == absFileIndex;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
