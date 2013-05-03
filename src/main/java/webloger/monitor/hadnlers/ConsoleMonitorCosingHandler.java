package webloger.monitor.hadnlers;

public class ConsoleMonitorCosingHandler implements MonitorClosingHandler {
    @Override
    public void handle(String label, long startAt, long endAt) {
        System.out.println( String.format("%s takes %d ms", label, (endAt-startAt)) );
    }
}