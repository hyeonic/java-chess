package chess.domain.piece;

import java.util.Locale;

public class King extends Piece {

    private static final String SYMBOL = "K";

    public King(Color color) {
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
