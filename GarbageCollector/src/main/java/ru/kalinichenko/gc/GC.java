package ru.kalinichenko.gc;

public class GC extends Thread {

    private CachingHandler cachingHandler;

    public void run() {
        int i = 0;
        while (true) {
            // System.out.println(this.getName() + ": New Thread is running..." + i++);
            try {
                cachingHandler.doCleaning();
                // Периодичность повторов -пятая часть времени хранения самого кашируемого объекта
                Thread.sleep(1000 / 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCachingHandler(CachingHandler cachingHandler) {
        this.cachingHandler = cachingHandler;
    }
}
