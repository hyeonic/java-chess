package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Color;

public class WhiteTurn extends Running {

    public WhiteTurn(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public State move(String source, String target) {
        if (chessBoard.isTurn(source, Color.WHITE)) {
            throw new IllegalArgumentException("white 진영의 차례입니다.");
        }

        chessBoard.move(source, target);

        if (chessBoard.isFinished()) {
            return new WhiteWin(chessBoard);
        }

        return new BlackTurn(chessBoard);
    }
}
