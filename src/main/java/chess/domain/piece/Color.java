package chess.domain.piece;

public enum Color {

    BLACK("black"),
    WHITE("white"),
    NONE("none"),
    ;

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
