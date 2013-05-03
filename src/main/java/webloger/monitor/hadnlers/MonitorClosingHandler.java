package webloger.monitor.hadnlers;

public interface MonitorClosingHandler {

    public void handle(String label, long startAt, long endAt);

}