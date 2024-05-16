package ru.kalinichenko.spring.service.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.kalinichenko.spring.LogTransformation;
import ru.kalinichenko.spring.model.LogData;
import ru.kalinichenko.spring.service.CheckerInt;

import java.util.List;

@LogTransformation
@Order(30)
public class CheckerTypeApp implements CheckerInt<LogData> {
    public CheckerTypeApp() {}

    public void refactor(List<LogData> lst, String str) {
        for (LogData l : lst) {
            String typeApp = l.getTypeApp();
            if (typeApp == null) continue;
            if (typeApp.equals("web") || typeApp.equals("mobile")) {
                l.setTypeApp(typeApp);
            } else
                l.setTypeApp("other:" + typeApp);
        }
    }

}
