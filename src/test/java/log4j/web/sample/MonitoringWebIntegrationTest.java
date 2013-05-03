package log4j.web.sample;

import webloger.monitor.Monitor;
import webloger.monitor.MonitorFactory;

import java.util.Random;

public class MonitoringWebIntegrationTest {

    private MonitorFactory factory = new MonitorFactory("test","afGks-dfe4t-rg473-2d-4fdf4");
    private Random rand = new Random();

    public static void main(String[] args) {
        MonitoringWebIntegrationTest o = new MonitoringWebIntegrationTest();
        o.runTest();
    }

    public void runTest() {
        Monitor m = factory.start("log4j.web.sample.MonitoringWebIntegrationTest#runTest");
        try{
            long sleepFor = rand.nextInt(9)*1000+rand.nextInt(1000);
            System.out.print("Sleep for "+sleepFor);
            Thread.sleep(sleepFor);
        }catch(Exception e){

        }finally {
            m.stop();
        }
    }

}