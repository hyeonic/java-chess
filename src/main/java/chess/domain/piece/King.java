package chess.domain.piece;

import chess.domain.Color;

public class King extends Piece {

    private static final String NOTATION = "K";

    public King(Color color) {
        super(color);
    }

    @Override
    public String getNotation() {
        if (isBlack()) {
            return NOTATION;
        }

        return NOTATION.toLowerCase();
    }
}
