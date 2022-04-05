package chess.domain;

import static java.util.stream.Collectors.toList;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardFixtures {

    private static final String NEW_LINE = System.lineSeparator();

    public static ChessBoard generateEmptyChessBoard() {
        return new ChessBoard(() -> generateEmptyBoard());
    }

    public static List<List<Piece>> generateEmptyBoard() {
        String inputBoard = new StringBuilder()
                .append("........").append(NEW_LINE)
                .append("........").append(NEW_LINE)
                .append("........").append(NEW_LINE)
                .append("........").append(NEW_LINE)
                .append("........").append(NEW_LINE)
                .append("........").append(NEW_LINE)
                .append("........").append(NEW_LINE)
                .append("........").toString();

        return parseBoard(inputBoard);
    }

    private static Map<String, Piece> generateNotations() {
        Map<String, Piece> notations = new HashMap<>();

        notations.put("R", new Rook(Color.BLACK));
        notations.put("N", new Knight(Color.BLACK));
        notations.put("B", new Bishop(Color.BLACK));
        notations.put("Q", new Queen(Color.BLACK));
        notations.put("K", new King(Color.BLACK));
        notations.put("P", new Pawn(Color.BLACK));

        notations.put("r", new Rook(Color.WHITE));
        notations.put("n", new Knight(Color.WHITE));
        notations.put("b", new Bishop(Color.WHITE));
        notations.put("q", new Queen(Color.WHITE));
        notations.put("k", new King(Color.WHITE));
        notations.put("p", new Pawn(Color.WHITE));

        notations.put(".", new EmptyPiece());

        return notations;
    }

    public static List<List<Piece>> parseBoard(String inputBoard) {
        Map<String, Piece> notations = generateNotations();
        String[] splitValues = inputBoard.split(NEW_LINE, -1);

        return Arrays.stream(splitValues)
                .map(splitValue -> splitValue.split(""))
                .map(inputPieces -> getPieces(notations, inputPieces))
                .collect(toList());
    }

    public static ChessBoard generatePawnChessBoard() {
        ChessBoard chessBoard = new ChessBoard(() -> generateEmptyBoard());
        Board board = chessBoard.getBoard();
        List<List<Piece>> value = board.getValue();

        value.get(0).set(0, new Pawn(Color.BLACK));
        value.get(1).set(0, new Pawn(Color.BLACK));
        value.get(2).set(0, new Pawn(Color.BLACK));

        return chessBoard;
    }

    public static ChessBoard generateWhiteKingChessBoard() {
        ChessBoard chessBoard = new ChessBoard(() -> generateEmptyBoard());
        Board board = chessBoard.getBoard();
        List<List<Piece>> value = board.getValue();

        value.get(0).set(0, new King(Color.WHITE));
        value.get(1).set(0, new Queen(Color.BLACK));
        value.get(2).set(0, new King(Color.BLACK));

        return chessBoard;
    }

    public static ChessBoard generateBlackKingChessBoard() {
        ChessBoard chessBoard = new ChessBoard(() -> generateEmptyBoard());
        Board board = chessBoard.getBoard();
        List<List<Piece>> value = board.getValue();

        value.get(0).set(0, new King(Color.BLACK));
        value.get(1).set(0, new Queen(Color.WHITE));
        value.get(2).set(0, new King(Color.WHITE));

        return chessBoard;
    }

    private static List<Piece> getPieces(Map<String, Piece> notations, String[] inputPiece) {
        return Arrays.stream(inputPiece)
                .map(notations::get)
                .collect(toList());
    }

    public static List<List<Piece>> setPiece(ChessBoard chessBoard, String inputPosition, Piece piece) {
        Position position = new Position(inputPosition);
        Board board = chessBoard.getBoard();
        List<List<Piece>> value = board.getValue();

        value.get(position.getRankIndex()).set(position.getFileIndex(), piece);

        return value;
    }
}
