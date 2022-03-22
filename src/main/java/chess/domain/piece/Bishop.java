package chess.domain.piece;

import java.util.Locale;

public class Bishop extends Piece {

    private static final String SYMBOL = "B";

    public Bishop(Color color) {
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
