package ru.kalinichenko.spring.service.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.kalinichenko.spring.LogTransformation;
import ru.kalinichenko.spring.model.LogData;
import ru.kalinichenko.spring.service.CheckerInt;

import java.util.List;

@LogTransformation
@Order(10)
public class CheckerFio implements CheckerInt<LogData> {
    public CheckerFio() {}

    public void refactor(List<LogData> lst, String str){
        for (LogData l : lst) {
            String fio = l.getFio();
            int index = fio.indexOf(" ");
            if (index < 0) continue;

            String f = fio.substring(0, index);
            f = f.substring(0, 1).toUpperCase() + f.substring(1);

            String tmp = fio.substring(index + 1);
            index = tmp.indexOf(" ");
            if (index < 0) l.setFio(f + " " + tmp);
            String i = tmp.substring(0, index);
            i = i.substring(0, 1).toUpperCase() + i.substring(1);

            String o = tmp.substring(index + 1);
            o = o.substring(0, 1).toUpperCase() + o.substring(1);
            l.setFio(f + " " + i + " " + o);
        }
    }
}
