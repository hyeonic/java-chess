package chess.domain.piece;

import java.util.Locale;

public class Queen extends Piece {

    private static final String SYMBOL = "Q";

    public Queen(Color color) {
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
