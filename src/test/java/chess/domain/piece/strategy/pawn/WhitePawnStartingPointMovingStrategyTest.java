package chess.domain.piece.strategy.pawn;

import static chess.domain.BoardFixtures.generateEmptyChessBoard;
import static chess.domain.BoardFixtures.setPiece;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WhitePawnStartingPointMovingStrategyTest {

    @DisplayName("Pawn은 시작점에서 1칸 혹은 2칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"a2,a4,true", "a2,a3,true", "a2,a5,false", "a2,a1,false", "a3,a4,false"})
    void pawn_시작점에서_1칸_2칸_이동_가능하다(String source, String target, boolean expected) {
        MovingStrategy movingStrategy = new WhitePawnStartingPointMovingStrategy();
        ChessBoard chessBoard = generateEmptyChessBoard();
        setPiece(chessBoard, source, new Pawn(Color.WHITE));

        boolean result = movingStrategy.canMove(chessBoard.getBoard(), new Position(source), new Position(target));

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("Pawn은 시작점에서 2칸 이동할 때 앞에 기물이 존재하면 이동할 수 없다.")
    @Test
    void pawn_시작점에서_2칸_이동할_때_기물이_존재하면_이동_불가능하다() {
        MovingStrategy movingStrategy = new WhitePawnStartingPointMovingStrategy();
        ChessBoard chessBoard = generateEmptyChessBoard();
        setPiece(chessBoard, "a3", new Pawn(Color.WHITE));
        setPiece(chessBoard, "a4", new Pawn(Color.WHITE));

        boolean result = movingStrategy.canMove(chessBoard.getBoard(), new Position("a2"), new Position("a4"));

        assertThat(result).isFalse();
    }
}