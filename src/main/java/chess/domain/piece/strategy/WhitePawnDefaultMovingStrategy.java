package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class WhitePawnDefaultMovingStrategy {

    private static final int RANK_INDEX_STARTING_POINT = 6;
    private static final Direction DIRECTION = Direction.TOP;

    public boolean canMove(List<List<Piece>> board, Position source, Position target) {
        int rankLength = Math.abs(source.getRankIndex() - target.getRankIndex());
        if (source.getRankIndex() != RANK_INDEX_STARTING_POINT && Direction.of(source, target) == Direction.TOP && rankLength == 1) {
            Piece currentPiece = findPiece(board, target);
            return currentPiece.isEmpty();
        }

        return false;
    }

    private Piece findPiece(List<List<Piece>> board, Position position) {
        int rankIndex = position.getRankIndex();
        int fileIndex = position.getFileIndex();

        return board.get(rankIndex).get(fileIndex);
    }
}
