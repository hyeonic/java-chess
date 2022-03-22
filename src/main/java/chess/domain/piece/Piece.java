package chess.domain.piece;

public abstract class Piece {

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public abstract String getSymbol();
}
