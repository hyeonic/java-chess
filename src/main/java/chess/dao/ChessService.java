package chess.dao;

import static chess.dao.dto.PieceType.BISHOP_BLACK;
import static chess.dao.dto.PieceType.BISHOP_WHITE;
import static chess.dao.dto.PieceType.KING_BLACK;
import static chess.dao.dto.PieceType.KING_WHITE;
import static chess.dao.dto.PieceType.KNIGHT_BLACK;
import static chess.dao.dto.PieceType.KNIGHT_WHITE;
import static chess.dao.dto.PieceType.PAWN_BLACK;
import static chess.dao.dto.PieceType.PAWN_WHITE;
import static chess.dao.dto.PieceType.QUEEN_BLACK;
import static chess.dao.dto.PieceType.QUEEN_WHITE;
import static chess.dao.dto.PieceType.ROOK_BLACK;
import static chess.dao.dto.PieceType.ROOK_WHITE;

import chess.dao.dto.PieceDto;
import chess.dao.dto.TurnDto;
import chess.domain.ChessBoard;
import chess.domain.state.ChessGame;
import chess.domain.generator.EmptyBoardGenerator;
import chess.dao.dto.PieceType;
import chess.domain.state.State;
import chess.dao.dto.StateType;
import java.util.List;

public class ChessService {

    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public ChessService(PieceDao pieceDao, TurnDao turnDao) {
        this.pieceDao = pieceDao;
        this.turnDao = turnDao;
    }

    public void ready() {
        turnDao.save(new TurnDto(StateType.READY));
    }

    public void create() {
        pieceDao.removeAll();
        List<PieceDto> initPieces = initPieces();
        for (PieceDto pieceDto : initPieces) {
            pieceDao.save(pieceDto);
        }

        turnDao.save(new TurnDto(StateType.WHITE_TURN));
    }

    public ChessGame getChessGame() {
        ChessBoard chessBoard = generateChessBoard();
        TurnDto lastTurn = getLastTurn();

        State state = generateState(lastTurn, chessBoard);

        return new ChessGame(state);
    }

    public void move(String source, String target) {
        State state = generateState(getLastTurn(), generateChessBoard());

        ChessGame chessGame = new ChessGame(state);
        chessGame.move(source, target);

        shift(source, target);

        StateType stateType = StateType.of(chessGame.getState());
        turnDao.save(new TurnDto(stateType));
    }

    private TurnDto getLastTurn() {
        return turnDao.findLastTurn()
                .orElseThrow(() -> new IllegalArgumentException("턴 정보가 존재하지 않습니다."));
    }

    private void shift(String source, String target) {
        if (!pieceDao.findById(target).isEmpty()) {
            pieceDao.remove(target);
        }

        pieceDao.save(new PieceDto(target, findPiece(source).getPieceType()));
        pieceDao.remove(source);
    }

    private PieceDto findPiece(String source) {
        return pieceDao.findById(source)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다."));
    }

    private State generateState(TurnDto lastTurn, ChessBoard chessBoard) {
        StateType stateType = lastTurn.getTurn();
        return stateType.parseState(chessBoard);
    }

    private ChessBoard generateChessBoard() {
        List<PieceDto> pieces = pieceDao.findAll();
        ChessBoard chessBoard = new ChessBoard(new EmptyBoardGenerator());

        for (PieceDto pieceDto : pieces) {
            PieceType pieceType = pieceDto.getPieceType();
            chessBoard.fill(pieceDto.getId(), pieceType.getPiece());
        }

        return chessBoard;
    }

    private List<PieceDto> initPieces() {
        return List.of(
                new PieceDto("a1", ROOK_WHITE),
                new PieceDto("b1", KNIGHT_WHITE),
                new PieceDto("c1", BISHOP_WHITE),
                new PieceDto("d1", QUEEN_WHITE),
                new PieceDto("e1", KING_WHITE),
                new PieceDto("f1", BISHOP_WHITE),
                new PieceDto("g1", KNIGHT_WHITE),
                new PieceDto("h1", ROOK_WHITE),
                new PieceDto("a2", PAWN_WHITE),
                new PieceDto("b2", PAWN_WHITE),
                new PieceDto("c2", PAWN_WHITE),
                new PieceDto("d2", PAWN_WHITE),
                new PieceDto("e2", PAWN_WHITE),
                new PieceDto("f2", PAWN_WHITE),
                new PieceDto("g2", PAWN_WHITE),
                new PieceDto("h2", PAWN_WHITE),
                new PieceDto("a8", ROOK_BLACK),
                new PieceDto("b8", KNIGHT_BLACK),
                new PieceDto("c8", BISHOP_BLACK),
                new PieceDto("d8", QUEEN_BLACK),
                new PieceDto("e8", KING_BLACK),
                new PieceDto("f8", BISHOP_BLACK),
                new PieceDto("g8", KNIGHT_BLACK),
                new PieceDto("h8", ROOK_BLACK),
                new PieceDto("a7", PAWN_BLACK),
                new PieceDto("b7", PAWN_BLACK),
                new PieceDto("c7", PAWN_BLACK),
                new PieceDto("d7", PAWN_BLACK),
                new PieceDto("e7", PAWN_BLACK),
                new PieceDto("f7", PAWN_BLACK),
                new PieceDto("g7", PAWN_BLACK),
                new PieceDto("h7", PAWN_BLACK)
        );
    }
}
