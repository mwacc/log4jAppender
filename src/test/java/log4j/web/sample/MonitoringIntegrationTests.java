package log4j.web.sample;

import webloger.monitor.Monitor;
import webloger.monitor.MonitorFactory;
import webloger.monitor.hadnlers.ConsoleMonitorCosingHandler;

import java.util.Random;

public class MonitoringIntegrationTests {

    private static MonitorFactory factory = new MonitorFactory();
    private static Random rand = new Random();

    public static void main(String[] args) {
        factory.setHandler(new ConsoleMonitorCosingHandler());

        for( int i = 0; i < 10; i++ ){
            Thread t = new InnerThread();
            t.setName("thread#"+i);
            t.start();
        }
    }


    static class InnerThread extends Thread {

        @Override
        public void run() {
            new InnerObject().doSomething();
        }
    }

    static class InnerObject {
        public void doSomething() {
            Monitor monitor = factory.start("log4j.web.sample.MonitoringIntegrationTests.InnerObject#doSomething");
            try {
                System.out.println("doSomething not useful");
                Thread.sleep(700*(rand.nextInt(5)+2));
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } finally {
                monitor.stop();
            }

        }
    }
}