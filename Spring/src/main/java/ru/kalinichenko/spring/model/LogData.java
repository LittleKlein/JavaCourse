package ru.kalinichenko.spring.model;

import java.util.Date;

public class LogData {
    private String fileName;
    private String login;
    private String fio;
    private Date date;
    private String typeApp;

    public LogData() {}
    public LogData(String fileName, String login, String fio, Date date, String typeApp) {
        this.fileName = fileName;
        this.login = login;
        this.fio = fio;
        this.date = date;
        this.typeApp = typeApp;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTypeApp() {
        return typeApp;
    }

    public void setTypeApp(String typeApp) {
        this.typeApp = typeApp;
    }

    @Override
    public String toString() {
        return "LogData{" +
                "fileName='" + fileName + '\'' +
                ", login='" + login + '\'' +
                ", fio='" + fio + '\'' +
                ", date=" + date +
                ", typeApp='" + typeApp + '\'' +
                '}';
    }
}
