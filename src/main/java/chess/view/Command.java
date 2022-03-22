package chess.view;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    ;

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
                .filter(value -> input.equals(value.value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isEnd() {
        return this == END;
    }
}
