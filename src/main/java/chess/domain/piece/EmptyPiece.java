package chess.domain.piece;

public class EmptyPiece extends Piece {

    private static final String SYMBOL = ".";

    public EmptyPiece() {
        super(Color.NONE);
    }

    @Override
    public boolean isBlack() {
        throw new UnsupportedOperationException();
    }

    public String getSymbol() {
        return SYMBOL;
    }
}
