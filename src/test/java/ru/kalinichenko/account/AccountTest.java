package ru.kalinichenko.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void withOwner() {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {Account acc1 = new Account(""); });
        assertNull(thrown.getMessage());
    }

    @Test
    void setBalance1() throws IllegalArgumentException {
        Account accTest = new Account("Test");
        Throwable thrown1 = assertThrows(IllegalArgumentException.class, () -> { accTest.setBalance(null, 1); });
        assertNull(thrown1.getMessage());
    }

    @Test
    void setBalance2() throws IllegalArgumentException {
        Account accTest = new Account("Test");
        Throwable thrown2 = assertThrows(IllegalArgumentException.class, () -> { accTest.setBalance(Currency.RUB, -1); });
        assertNull(thrown2.getMessage());
    }

    @Test
    void saveUndo() throws IllegalArgumentException {
        Account accTest = new Account("Test");
        Throwable thrown = assertThrows(ArrayIndexOutOfBoundsException.class, () -> { accTest.setOwner("Another"); accTest.undo(); accTest.undo(); });
        assertNull(thrown.getMessage());
    }

    @Test
    void undo()  {
        Account accTest = new Account("Test");
        accTest.setOwner("Another");
        accTest.undo();
    }

    @Test
    void save() {
        Account accTest = new Account("Test");
        Save acc2 = accTest.save();
    }
}