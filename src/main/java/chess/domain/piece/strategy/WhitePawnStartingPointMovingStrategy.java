package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class WhitePawnStartingPointMovingStrategy {

    private static final int RANK_INDEX_STARTING_POINT = 6;
    private static final Direction DIRECTION = Direction.TOP;

    public boolean canMove(List<List<Piece>> board, Position source, Position target) {
        int rankLength = Math.abs(source.getRankIndex() - target.getRankIndex());
        if (source.getRankIndex() == RANK_INDEX_STARTING_POINT && rankLength == 2) {
            return canMoveTwoPosition(board, source);
        }

        if (source.getRankIndex() == RANK_INDEX_STARTING_POINT && rankLength == 1) {
            return canMoveOnePosition(board, source);
        }

        return false;
    }

    private boolean canMoveTwoPosition(List<List<Piece>> board, Position source) {
        Position currentPosition = source.add(DIRECTION);
        Piece currentPiece = findPiece(board, currentPosition);

        currentPosition = currentPosition.add(DIRECTION);
        Piece targetPiece = findPiece(board, currentPosition);
        return currentPiece.isEmpty() && targetPiece.isEmpty();
    }

    private boolean canMoveOnePosition(List<List<Piece>> board, Position source) {
        Piece currentPiece = findPiece(board, source.add(DIRECTION));
        return currentPiece.isEmpty();
    }

    private Piece findPiece(List<List<Piece>> board, Position position) {
        int rankIndex = position.getRankIndex();
        int fileIndex = position.getFileIndex();

        return board.get(rankIndex).get(fileIndex);
    }
}
