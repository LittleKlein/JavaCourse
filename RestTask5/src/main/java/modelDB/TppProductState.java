package modelDB;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TppProductState {
    S0("CLOSE"),
    S1("OPEN");

    @Getter
    private final String name;

}
