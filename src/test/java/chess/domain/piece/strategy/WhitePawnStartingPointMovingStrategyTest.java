package chess.domain.piece.strategy;

import chess.domain.BoardFixtures;
import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhitePawnStartingPointMovingStrategyTest {

    @DisplayName("white pawn은 시작지점에서 1칸 혹은 2칸 이동 가능하다.")
    @Test
    void white_pawn_시작점() {
        WhitePawnStartingPointMovingStrategy movingStrategy = new WhitePawnStartingPointMovingStrategy();

        ChessBoard chessBoard = BoardFixtures.generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        board.get(6).set(0, new Pawn(Color.WHITE));

        System.out.println(movingStrategy.canMove(board, new Position("a2"), new Position("a3")));
        System.out.println(movingStrategy.canMove(board, new Position("a2"), new Position("a4")));
        System.out.println(movingStrategy.canMove(board, new Position("a2"), new Position("a5")));
        System.out.println(movingStrategy.canMove(board, new Position("a3"), new Position("a4")));

        board.get(5).set(0, new Pawn(Color.WHITE));

        System.out.println(movingStrategy.canMove(board, new Position("a2"), new Position("a3")));
        System.out.println(movingStrategy.canMove(board, new Position("a2"), new Position("a4")));

        for (List<Piece> pieces : board) {
            for (Piece piece : pieces) {
                System.out.print(piece.getNotation());
            }
            System.out.println();
        }
    }
}