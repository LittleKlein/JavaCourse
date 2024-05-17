package modelDB;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TppProductRegisterState {
    S0("CLOSE"),
    S1("OPEN"),
    S2("RESERVE"),
    S3("DELETE");

    @Getter
    private final String name;
}
