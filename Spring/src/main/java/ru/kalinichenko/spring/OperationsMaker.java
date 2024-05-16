package ru.kalinichenko.spring;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kalinichenko.spring.model.LogData;
import ru.kalinichenko.spring.repo.LoginsRepository;
import ru.kalinichenko.spring.repo.UsersRepository;
import ru.kalinichenko.spring.service.ReadDataInt;
import ru.kalinichenko.spring.service.CheckerInt;
import ru.kalinichenko.spring.service.SaveDataInt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OperationsMaker {
    @Autowired
    ReadDataInt<LogData> dataReader;
    @Autowired
    SaveDataInt<LogData> dataSaver;
    @Autowired
    UsersRepository ur;
    @Autowired
    LoginsRepository lr;

    static String logFilePath = System.getProperty("user.dir")+"\\src\\main\\resources\\logfile";
    static String logErrorPath = System.getProperty("user.dir")+"\\src\\main\\resources\\logError";

    @Autowired
    List <CheckerInt> operations = new ArrayList<>();

    @PostConstruct
    public void make() throws IOException {
        List<LogData> lst = dataReader.Read(logFilePath);
        for (CheckerInt op : operations) {
            op.refactor(lst, logErrorPath);
        }
        dataSaver.saveInBase(lst, logErrorPath, ur, lr);
    }

}
