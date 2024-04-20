package ru.kalinichenko.cache;

/* Kalinichenko 17.04.2024 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CacheTest {
    @Test
    void testMe() {
        int iResult;

        TestCached tstCached = new TestCached();
        Cacheable tstCachedCopy = Utils.cache(tstCached);
        //System.out.println("call to getValue #1");
        iResult = tstCachedCopy.getValue();// sout сработал
        //System.out.println("call to getValue #2 = "+iResult);
        iResult = tstCachedCopy.getValue();// sout молчит
        //System.out.println("call to getValue #3 = "+iResult);
        iResult = tstCachedCopy.getValue();// sout молчит
        //System.out.println("call to setValue = 11");
        Assertions.assertEquals(0, iResult);
        tstCachedCopy.setValue(11);
        iResult = tstCachedCopy.getValue();
        Assertions.assertEquals(0, iResult);
        //System.out.println("call to getValue #4 = "+iResult);
        iResult = tstCachedCopy.getValue();// sout сработал
        //System.out.println("call to getValue #5 = "+iResult);
        iResult = tstCachedCopy.getValue();// sout молчит
        //System.out.println("call to getValue #6 = "+iResult);
        //System.out.println("call to resetValue = 12");
        tstCachedCopy.resetValue(12);
        iResult = tstCachedCopy.getValue();
        Assertions.assertEquals(12, iResult);
        //System.out.println("call to getValue #6 = "+iResult);

        //System.out.println("---------------------------");

        TestNotCached tstNotCached = new TestNotCached();
        Cacheable tstNotCachedCopy = Utils.cache(tstNotCached);
        //System.out.println("call to getValue #1 = ");
        iResult = tstNotCachedCopy.getValue();// sout сработал
        //System.out.println("call to getValue #2 = "+iResult);
        iResult = tstNotCachedCopy.getValue();// sout молчит
        //System.out.println("call to getValue #3 = "+iResult);
        iResult = tstNotCachedCopy.getValue();// sout молчит
        Assertions.assertEquals(0, iResult);
        //System.out.println("call to setValue = 10");
        tstNotCachedCopy.setValue(10);
        iResult = tstNotCachedCopy.getValue();
        Assertions.assertEquals(10, iResult);
        //System.out.println("call to getValue #4 = "+iResult);
        iResult = tstNotCachedCopy.getValue();// sout сработал
        //System.out.println("call to getValue #5 = "+iResult);
        iResult = tstNotCachedCopy.getValue();// sout молчит
        //System.out.println("call to getValue #6 = "+iResult);
    }
}