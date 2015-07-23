package by.epam.nba.transport.trash;

import java.util.concurrent.CountDownLatch;

/**
 * @author Anton_Astrouski
 *
 */
public class Main {

    public static class Simple {

        public synchronized void methodA() {
            System.out.println("A: Thread entered method, sleeping...");
            sleep(1000);
            System.out.println("A: method sleep complete. Exit.");
        }

        public synchronized void methodB() {
            System.out.println("B: Thread entered method, sleeping...");
            sleep(500);
            System.out.println("B: method sleep complete. Exit.");
        }

        public synchronized void methodC() {
            System.out.println("C: Thread entered method, sleeping...");
            sleep(100);
            System.out.println("C: method sleep complete. Exit.");
        }

        private void sleep(final long time) {
            try {
                Thread.sleep(time);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(final String[] args) throws InterruptedException {
        final Simple s = new Simple();
        final CountDownLatch latch = new CountDownLatch(2);
        final Thread t1 = new Thread(new Run1(s, latch));
        final Thread t2 = new Thread(new Run2(s, latch));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    public static class Run1 implements Runnable {
        private final Simple s;
        private final CountDownLatch latch;

        public Run1(final Simple s, final CountDownLatch latch) {
            this.s = s;
            this.latch = latch;
        }

        @Override
        public void run() {
            latch.countDown();
            try {
                latch.await();
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (s) {
                System.out.println("T1: aquiring lock on S obj, calling method A!");
                s.methodA();
            }
        }

    }

    public static class Run2 implements Runnable {
        private final Simple s;
        private final CountDownLatch latch;

        public Run2(final Simple s, final CountDownLatch latch) {
            this.s = s;
            this.latch = latch;
        }

        @Override
        public void run() {
            latch.countDown();
            try {
                latch.await();
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2: trying to call method B!");
            s.methodB();
        }

    }

}
