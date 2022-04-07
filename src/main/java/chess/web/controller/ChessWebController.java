package chess.web.controller;

import chess.dao.ChessService;
import chess.domain.state.ChessGame;
import chess.domain.Color;
import chess.domain.Result;
import chess.web.dto.BoardResponse;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private final ChessService chessService;

    public ChessWebController(ChessService chessService) {
        this.chessService = chessService;
    }

    public ModelAndView index(Request request, Response response) {
        ChessGame chessGame = chessService.getChessGame();
        if (!chessGame.isStarted()) {
            return new ModelAndView(new HashMap<>(), "index.html");
        }

        Map<String, Object> model = new HashMap<>();
        if (chessGame.isFinished()) {
            chessService.ready();
            Result result = chessGame.result();
            model.put("result", result.name());
            return new ModelAndView(model, "result.html");
        }

        model.putAll(generateModel(chessGame));

        return new ModelAndView(model, "chess.html");
    }

    public ModelAndView create(Request request, Response response) {
        chessService.create();
        response.redirect("/");

        return generateEmptyModelAndView();
    }

    public ModelAndView move(Request request, Response response) {
        String target = request.queryParams("target");
        String source = request.queryParams("source");

        chessService.move(target, source);

        response.redirect("/");

        return generateEmptyModelAndView();
    }

    public void exceptionHandle(Exception exception, Request request, Response response) {
        ChessGame chessGame = chessService.getChessGame();
        String errorMessage = exception.getMessage();

        Map<String, Object> model = new HashMap<>();
        model.put("error", errorMessage);
        model.putAll(generateModel(chessGame));

        response.body(render(model, "chess.html"));
    }

    private Map<String, Object> generateModel(ChessGame chessGame) {
        Map<String, Object> model = new HashMap<>();
        model.put("pieces", new BoardResponse(chessGame.board()).getValue());
        model.put("whiteScore", chessGame.score(Color.WHITE));
        model.put("blackScore", chessGame.score(Color.BLACK));

        return model;
    }

    public String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private ModelAndView generateEmptyModelAndView() {
        return new ModelAndView(null, null);
    }
}
