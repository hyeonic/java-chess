package chess.domain.piece;

import chess.domain.location.Position;

import java.util.List;
import java.util.Map;

public abstract class Basis implements Piece {
    private final String displayName;

    public Basis(String displayName) {
        this.displayName = displayName;
    }

    public abstract boolean isSameColor(Color color);

    public abstract boolean isEmpty();

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public abstract double score();

    public abstract List<Position> movablePositions(Position from, Map<Position, Piece> pieceByPosition);


    @Override
    public String display() {
        return displayName;
    }
}
