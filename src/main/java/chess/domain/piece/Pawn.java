package chess.domain.piece;

import java.util.Locale;

public class Pawn extends Piece {

    private static final String SYMBOL = "P";

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        if (isBlack()) {
            return SYMBOL;
        }

        return SYMBOL.toLowerCase(Locale.ROOT);
    }
}
