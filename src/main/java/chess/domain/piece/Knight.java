package chess.domain.piece;

import java.util.Locale;

public class Knight extends Piece {

    private static final String SYMBOL = "N";

    public Knight(Color color) {
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
