package chess.domain.generator;

import static java.util.stream.Collectors.toList;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.IntStream;

public class EmptyBoardGenerator implements BoardGenerator {

    @Override
    public List<List<Piece>> generate() {
        return IntStream.rangeClosed(0, 7)
                .mapToObj(ignored -> generatePieces())
                .collect(toList());
    }

    private List<Piece> generatePieces() {
        return IntStream.rangeClosed(0, 7)
                .mapToObj(ignored -> new EmptyPiece())
                .collect(toList());
    }
}
