package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import chess.controller.WebChessGame;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public/assets");

        WebChessGame chessGame = new WebChessGame();
        ChessBoard chessBoard = chessGame.start();
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.start();
            return render(model, "index.html");
        });

        get("/chessboard", "application/json", (req, res) -> {
            JsonArray chessBoardArray = new JsonArray();
            for (Map.Entry<Position, Piece> board : chessBoard.getChessBoard().entrySet()) {
                chessBoardArray.add(boardToJSON(board));
            }
            return chessBoardArray;
        });

        put("/move", "application/json", (req, res) -> {
            Map<String, String> body = gson.fromJson(req.body(), HashMap.class);
            if (chessGame.movable(body.get("source"), body.get("target"))) {
                JsonObject response = new JsonObject();
                JsonObject movedSource = boardToJSON(Position.of(body.get("source")),
                    chessBoard.getPiece(Position.of(body.get("source"))));
                JsonObject movedTarget = boardToJSON(Position.of(body.get("target")),
                    chessBoard.getPiece(Position.of(body.get("target"))));
                response.add("source", movedSource);
                response.add("target", movedTarget);
                return response;
            }
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static JsonObject boardToJSON(Map.Entry<Position, Piece> board) {
        JsonObject square = new JsonObject();
        square.addProperty("id", board.getKey().getStringPosition());
        square.addProperty("position", board.getKey().getStringPosition());

        JsonObject piece = new JsonObject();
        piece.addProperty("type", board.getValue().getName());
        piece.addProperty("color", board.getValue().getColorAsString());

        square.add("piece", piece);
        return square;
    }

    private static JsonObject boardToJSON(Position movedPosition, Piece movedPiece) {
        JsonObject square = new JsonObject();
        square.addProperty("id", movedPosition.getStringPosition());
        square.addProperty("position", movedPosition.getStringPosition());

        JsonObject piece = new JsonObject();
        piece.addProperty("type", movedPiece.getName());
        piece.addProperty("color", movedPiece.getColorAsString());

        square.add("piece", piece);
        return square;
    }
}
