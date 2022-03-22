package chess.domain;

public class Position {

    private final int row;
    private final int col;

    public Position(String input) {
        String[] splitPositions = input.split("");
        this.row = PositionIndex.of(splitPositions[1]).getValue();
        this.col = PositionIndex.of(splitPositions[0]).getValue();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
