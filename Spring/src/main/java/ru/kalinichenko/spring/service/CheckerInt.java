package ru.kalinichenko.spring.service;

import java.util.List;

public interface CheckerInt<T> {
    void refactor(List<T> lst, String str);
}
