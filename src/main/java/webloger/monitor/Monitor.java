package webloger.monitor;

import webloger.monitor.hadnlers.MonitorClosingHandler;

public class Monitor {

    private final String label;
    private final long startAt;
    private final boolean enableMonitor;
    private final MonitorClosingHandler handler;

    Monitor(String label, long startAt, boolean enableMonitor, final MonitorClosingHandler handler) {
        this.label = label;
        this.startAt = startAt;
        this.enableMonitor = enableMonitor;
        this.handler = handler;
    }

    public void stop() {
        if(enableMonitor) {
            handler.handle(label, startAt, System.currentTimeMillis());
        }
    }

}