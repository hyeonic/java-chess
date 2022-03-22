package chess.domain.piece;

import java.util.Locale;

public class Rook extends Piece {

    private static final String SYMBOL = "R";

    public Rook(Color color) {
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
