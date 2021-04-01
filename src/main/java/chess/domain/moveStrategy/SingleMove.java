package chess.domain.moveStrategy;

import chess.domain.location.Position;
import chess.domain.location.Vector;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SingleMove implements MoveStrategy {
    private final Color color;
    private final List<Vector> directions;

    public SingleMove(Color color, List<Vector> directions) {
        this.color = color;
        this.directions = directions;
    }

    @Override
    public List<Position> movablePositions(Position from, Map<Position, Piece> pieceByPosition) {
        return directions.stream()
                         .map(from::move)
                         .filter(position -> !pieceByPosition.get(position)
                                                             .isSameColor(color))
                         .collect(Collectors.toList());
    }
}
