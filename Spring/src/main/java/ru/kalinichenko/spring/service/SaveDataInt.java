package ru.kalinichenko.spring.service;

import ru.kalinichenko.spring.repo.UsersRepository;
import ru.kalinichenko.spring.repo.LoginsRepository;

import java.util.List;

public interface SaveDataInt<T> {
    void saveInBase(List<T> logList, String pathForErrorLog, UsersRepository uRep, LoginsRepository lRep);
}
