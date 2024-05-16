package ru.kalinichenko.spring.service.impl;

import ru.kalinichenko.spring.entity.Users;
import ru.kalinichenko.spring.model.LogData;
import ru.kalinichenko.spring.entity.Logins;
import ru.kalinichenko.spring.repo.LoginsRepository;
import ru.kalinichenko.spring.repo.UsersRepository;
import ru.kalinichenko.spring.service.SaveDataInt;

import java.util.List;

public class SaveData implements SaveDataInt<LogData> {
    public SaveData() {}

    @Override
    public void saveInBase(List<LogData> logList, String pathForErrorLog, UsersRepository uRep, LoginsRepository lRep) {

        for (LogData logData : logList) {
            String login = logData.getLogin();
            boolean fl = uRep.existsUsersByUsername(login);
            if (fl) {
                Long idUser = uRep.getIdByUsername(login);
                Logins l = lRep.saveAndFlush(new Logins(null, logData.getDate(), idUser, logData.getTypeApp()));
            } else {
                Users u = uRep.saveAndFlush(new Users(null, login, logData.getFio()));
                Logins l = lRep.saveAndFlush(new Logins(null, logData.getDate(), u.getId(), logData.getTypeApp()));
            }

        }

    }

}