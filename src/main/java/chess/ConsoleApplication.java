package chess;

import chess.domain.ChessBoard;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();

        OutputView.printStartMessage();
        playing(chessBoard);
    }

    public static void playing(ChessBoard chessBoard) {
        Command command = InputView.inputCommand();
        if (!command.isEnd()) {
            chessBoard.init();
            OutputView.printChessBoard(chessBoard.getBoard());
            playing(chessBoard);
        }
    }
}
