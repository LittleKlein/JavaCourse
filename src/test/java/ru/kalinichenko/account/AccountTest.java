package ru.kalinichenko.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {Account acc1 = Account.withOwner(""); });
        assertNull(thrown.getMessage());
    }

    @Test
    void setBalance1() throws IllegalArgumentException {
        Account accTest = Account.withOwner("Test");
        Throwable thrown1 = assertThrows(IllegalArgumentException.class, () -> { accTest.setBalance(null, 1); });
        assertNull(thrown1.getMessage());
    }

    @Test
    void setBalance2() throws IllegalArgumentException {
        Account accTest = Account.withOwner("Test");
        Throwable thrown2 = assertThrows(IllegalArgumentException.class, () -> { accTest.setBalance(Currency.RUB, -1); });
        assertNull(thrown2.getMessage());
    }

    @Test
    void saveUndo() throws IllegalArgumentException {
        Account accTest = Account.withOwner("Test");
        Throwable thrown = assertThrows(ArrayIndexOutOfBoundsException.class, () -> { accTest.save(); accTest.undo(); accTest.undo(); });
        assertNull(thrown.getMessage());
    }

    @Test
    void undo() throws CloneNotSupportedException {
        Account accTest = Account.withOwner("Test");
        accTest.save();
        accTest.undo();
    }

    @Test
    void save() throws CloneNotSupportedException {
        Account accTest = Account.withOwner("Test");
        accTest.save();
    }
}