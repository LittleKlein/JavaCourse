package ru.kalinichenko.spring;

import org.junit.Test;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.kalinichenko.spring.model.LogData;
import ru.kalinichenko.spring.repo.LoginsRepository;
import ru.kalinichenko.spring.repo.UsersRepository;
import ru.kalinichenko.spring.service.CheckerInt;
import ru.kalinichenko.spring.service.impl.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class TestTask {

public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14.3-alpine");

@Autowired
private UsersRepository userRep;

@Autowired
private LoginsRepository loginRep;

@DynamicPropertySource
static void configureProperties(DynamicPropertyRegistry reg){
    reg.add("spring.datasource.url", postgres::getJdbcUrl);
    reg.add("spring.datasource.username", postgres::getUsername);
    reg.add("spring.datasource.password", postgres::getPassword);
}

@BeforeAll
static void beforeAll() {
    postgres.start();
}

@AfterAll
static void afterAll() {
    postgres.stop();
}

@BeforeEach
void beforeEach() {
    loginRep.deleteAll();
    userRep.deleteAll();
}

@Test
@DisplayName("Проверка на чтение из файла")
public void test1() {
    String logFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\logfile";
    String logErrorPath = System.getProperty("user.dir")+"\\src\\test\\resources\\logError";
    ReadData dataReader = new ReadData();
    Assertions.assertDoesNotThrow(()->dataReader.Read(logFilePath), "Ошибка при чтении файла с данными из "+logFilePath);

    try {
        List<LogData> lst = dataReader.Read(logFilePath);
        Assertions.assertEquals (lst.size(), 5, "Ошибка: Считывается некорректное кол-во элементов файла");
    } catch (IOException e){}

}

    @Test
    @DisplayName("Проверка работы компоненты на корректировку ФИО")
    public void test2() {
        String logFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\logfile";
        String logErrorPath = System.getProperty("user.dir")+"\\src\\test\\resources\\logError";
        ReadData dataReader = new ReadData();
        try {
            List<LogData> lst = dataReader.Read(logFilePath);
            CheckerFio refactorFio = new CheckerFio();
            refactorFio.refactor(lst, "");
            for (LogData logData : lst) {
                String fio = logData.getFio();
                char ch;
                ch = fio.charAt(0);
                boolean b = Character.isLowerCase(ch);
                Assertions.assertNotEquals(b, true, "Ошибка. в фамилии "+fio+" есть слово с маленькой буквы");

                int index = fio.indexOf(" ");
                if (index < 0) continue;
                String tmp = fio.substring(index+1);
                ch = tmp.charAt(0);
                b = Character.isLowerCase(ch);
                Assertions.assertNotEquals(b, true, "Ошибка. в фамилии "+fio+" есть слово с маленькой буквы");

                index = tmp.indexOf(" ");
                tmp = tmp.substring(index+1);
                ch = tmp.charAt(0);
                b = Character.isLowerCase(ch);
                Assertions.assertNotEquals(b, true, "Ошибка. в фамилии "+fio+" есть слово с маленькой буквы");

            }
        } catch (IOException e){}

    }

    @Test
    @DisplayName("Проверка работы компоненты на корректировку <приложения>")
    public void test3() {
        String logFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\logfile";
        String logErrorPath = System.getProperty("user.dir")+"\\src\\test\\resources\\logError";
        ReadData dataReader = new ReadData();
        try {
            List<LogData> lst = dataReader.Read(logFilePath);
            CheckerTypeApp refactorTypeApp = new CheckerTypeApp();
            refactorTypeApp.refactor(lst, "");
            for (LogData logData : lst) {
                String typeApp = logData.getTypeApp();
                if (typeApp == null) continue;
                if (!typeApp.equals("web")&&!typeApp.equals("mobile")) {
                    if (typeApp.length()<5)
                        Assertions.assertAll("Ошибка формата типа приложения в строке");
                    String typeApp1 = typeApp.substring(0,6);
                    Assertions.assertEquals(typeApp1, "other:","Ошибка формата типа приложения в строке");
                }
            }
        } catch (IOException e){}
    }
    @Test
    @DisplayName("Проверка работы компоненты на корректировку даты")
    public void test4() {
        String logFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\logfile";
        String logErrorPath = System.getProperty("user.dir")+"\\src\\test\\resources\\logError";
        ReadData dataReader = new ReadData();
        try {
            List<LogData> lst = dataReader.Read(logFilePath);
            CheckerDate refactorDate = new CheckerDate();
            refactorDate.refactor(lst, "");
            for (int i = 0; i < lst.size(); i++) {
                Date date = lst.get(i).getDate();
                Assertions.assertNotNull(date, "Ошибка, компонента преобразования даты не убирает записи с пустой датой");
            }
        } catch (IOException e){}

    }

    @Test
    @DisplayName("Проверка на запись данных в базу")
    public void test5() {
        String logFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\logfile";
        String logErrorPath = System.getProperty("user.dir")+"\\src\\test\\resources\\logError";
        ReadData dataReader = new ReadData();
        SaveData dataSaver = new SaveData();
        Assertions.assertDoesNotThrow(()->dataReader.Read(logFilePath), "Ошибка при чтении файла с данными из "+logFilePath);

        try {
            List<LogData> lst = dataReader.Read(logFilePath);
            Assertions.assertEquals (lst.size(), 5, "Ошибка: Считывается некорректное кол-во элементов файла");
            dataSaver.saveInBase(lst, logErrorPath, userRep, loginRep);

            Long idUser = userRep.getIdByUsername("VKalinichenko");
            Assertions.assertNotEquals(idUser>0, true, "Ошибка: считанные из файла данные не записаны в таблицу Users");
            Long idLogin = loginRep.getLoginIdByUserId(idUser);
            Assertions.assertNotEquals(idLogin>0, true, "Ошибка: считанные из файла данные не записаны в таблицу Logins");

        } catch (IOException e){}
    }
    @Autowired
    OperationsMaker operationsMaker;

    @Autowired
    @Qualifier("beanRefactorFioInterface")
    CheckerInt b1;

    @Autowired
    @Qualifier("beanRefactorDateInterface")
    CheckerInt b2;

    @Autowired
    @Qualifier("beanRefactorTypeAppInterface")
    CheckerInt b3;

    @Test
    @BeforeAll
    public void before()
    {
        // почистим
       loginRep.deleteAll();
       userRep.deleteAll();
    }

    @Test
    @DisplayName("Проверка работы всех компонент")
    public void test6() throws IOException {
        // проверим то что в базу все записалось
        Long idUser = userRep.getIdByUsername("VKalinichenko");
        Assertions.assertNotEquals(idUser>0, true, "Ошибка: считанные из файла данные не записаны в таблицу Users");
        Long idLogin = loginRep.getLoginIdByUserId(idUser);
        Assertions.assertNotEquals(idLogin>0, true, "Ошибка: считанные из файла данные не записаны в таблицу Logins");
    }

}
