package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Result;

public class End extends Finished {

    public End(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public Result winner() {
        double whiteScore = chessBoard.calculateScore(Color.WHITE);
        double blackScore = chessBoard.calculateScore(Color.BLACK);

        return Result.of(whiteScore, blackScore);
    }
}
