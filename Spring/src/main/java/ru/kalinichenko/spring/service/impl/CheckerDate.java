package ru.kalinichenko.spring.service.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.kalinichenko.spring.LogTransformation;
import ru.kalinichenko.spring.model.LogData;
import ru.kalinichenko.spring.service.CheckerInt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@LogTransformation
@Order(20)
public class CheckerDate implements CheckerInt<LogData> {
    public CheckerDate() {}
    public void refactor(List<LogData> lst, String pathForErrorLog) {
        for (int i = 0; i < lst.size(); i++) {
            Date date = lst.get(i).getDate();
            if (date == null) {
                //пишем в лог и удаляем, так как в базе он не нужен;
                saveInErrorLog(lst.get(i), pathForErrorLog);
                lst.remove(i);
            }
        }
    }
    private void saveInErrorLog(LogData ld, String pathForErrorLog) {
        String str = "Ошибка в файле "+ld.getFileName()+" не задана дата входа для "+ld.getLogin()+" - "+ld.getFio();
        try {
            FileWriter writer = new FileWriter(pathForErrorLog+"\\Errors.txt", true);
            writer.write(str+"\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи, проверьте данные.");
        }
    }

}
