package chess.domain;

import static java.util.stream.Collectors.toList;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ChessBoard {

    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = 7;
    private static final List<Piece> emptyPieces = IntStream.rangeClosed(MIN_INDEX, MAX_INDEX)
            .mapToObj(ignored -> new EmptyPiece())
            .collect(toList());

    private final List<List<Piece>> board;

    public ChessBoard() {
        this.board = IntStream.rangeClosed(MIN_INDEX, MAX_INDEX)
                .mapToObj(ignored -> new ArrayList<>(emptyPieces))
                .collect(toList());
    }

    public void init() {
        Arrays.stream(BoardSetting.values())
                .forEach(this::fillPieces);
    }

    private void fillPieces(BoardSetting boardSetting) {
        Piece piece = boardSetting.getPiece();
        List<String> positions = boardSetting.getPositions();

        positions.forEach(position -> fillPiece(piece, new Position(position)));
    }

    private void fillPiece(Piece piece, Position position) {
        board.get(position.getRow()).remove(position.getCol());
        board.get(position.getRow()).add(position.getCol(), piece);
    }

    public List<List<Piece>> getBoard() {
        return Collections.unmodifiableList(board);
    }
}
