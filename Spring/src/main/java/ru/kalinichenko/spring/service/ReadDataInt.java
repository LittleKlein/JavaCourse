package ru.kalinichenko.spring.service;

import java.io.IOException;
import java.util.List;

public interface ReadDataInt<T> {
    List<T> Read(String pathName) throws IOException;
}
