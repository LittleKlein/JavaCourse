package ru.kalinichenko.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kalinichenko.spring.model.LogData;
import ru.kalinichenko.spring.service.ReadDataInt;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReadData implements ReadDataInt<LogData> {
    public ReadData() {}

    @Override
    public List<LogData> Read(String pathName) throws IOException {
        List<LogData> retList = new ArrayList<>();
        File folder = new File(pathName);
        String line;

        for (File file : folder.listFiles()) {
            if (!file.isFile()) continue;

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            do  {
                line = reader.readLine();
                if (line != null) {
                    LogData ld = parse(line, file.getName());
                    if (ld != null) retList.add(ld);
                }
            }
            while (line != null);
        }
        return retList;
    }
    public LogData parse (String input, String fromFile) {
        String login;
        String fio;
        String strDate;
        Date date;
        String typeApp;
        int index;
        String tmp;


        if (input == null) return null;

        index = input.indexOf(" ");
        if (index < 0) return null;

        login = input.substring(0, index);
        LogData ld = new LogData();
        ld.setLogin(login);
        ld.setFileName(fromFile);

        tmp = input.substring(index+1);
        index = tmp.indexOf(" ");
        if (index < 0) return ld;
        fio = tmp.substring(0, index);

        tmp = tmp.substring(index+1);
        index = tmp.indexOf(" ");
        if (index < 0) return ld;
        fio = fio+" "+tmp.substring(0, index);

        tmp = tmp.substring(index+1);
        index = tmp.indexOf(" ");
        if (index > 0) {
            fio = fio+" "+tmp.substring(0, index);
        } else
            fio = fio+" "+tmp.substring(0, tmp.length());
        ld.setFio(fio);

        tmp = tmp.substring(index+1);

        index = tmp.indexOf(" ");
        if (index < 0) return ld;
        strDate = tmp.substring(0, index);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        try {
            Date dDate = format.parse(strDate);
            ld.setDate(dDate);
        } catch (Exception e) {}

        typeApp = tmp.substring(index+1);
        ld.setTypeApp(typeApp);
        return ld;
    }

}
