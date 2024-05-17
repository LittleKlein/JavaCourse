package modelDB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.spring.task4.validators.TypeEnter;

@AllArgsConstructor
public enum TppProductType {
    NSO("НСО"),
    SMO("СМО"),
    IJO("ЕЖО"),
    DBDS("ДБДС"),
    CONTRACT("Договор"),
    UNKNOW("UNKNOW");

    @Getter
    private final String name;

    public static TppProductType getEnum(String value) {
        for (var e : values()) {
            if (e.name.equals(value)) {
                return e;

            }
        }
        return UNKNOW;
    }
}
