package log4j.web.sample;

import webloger.monitor.Monitor;
import webloger.monitor.MonitorFactory;
import webloger.monitor.hadnlers.ConsoleMonitorCosingHandler;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Random;

public class JmxTest {

    private MonitorFactory factory = new MonitorFactory();
    private Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        JmxTest jmxTest = new JmxTest();
        jmxTest.runTest();
    }

    public JmxTest() {
        factory = new MonitorFactory();
        factory.setHandler(new ConsoleMonitorCosingHandler());

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = null;
        try {
            name = new ObjectName("log4j.web.sample.mbeans:type=MonitorFactory");
            mbs.registerMBean(factory, name);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        }
    }

    void runTest() throws InterruptedException {
        while (true) {
            Thread t = new InnerThread();
            t.setName("inner thread");
            t.start();
            t.join();
        }
    }

    class InnerThread extends Thread {

        @Override
        public void run() {
            new InnerObject().doSomething();
        }
    }

    class InnerObject {
        public void doSomething() {
            Monitor monitor = factory.start("log4j.web.sample.MonitoringIntegrationTests.InnerObject#doSomething");
            try {
                System.out.println("doSomething not useful");
                Thread.sleep(1000*(rand.nextInt(5)+2));
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } finally {
                monitor.stop();
            }

        }
    }

}