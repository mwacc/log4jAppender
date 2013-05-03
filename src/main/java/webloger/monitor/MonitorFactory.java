package webloger.monitor;

import webloger.monitor.hadnlers.MonitorClosingHandler;
import webloger.monitor.hadnlers.WebMonitorClosingHandler;

public class MonitorFactory implements MonitorFactoryMBean {

    private boolean enableMonitor;
    private MonitorClosingHandler handler;

    public MonitorFactory() {
        this.enableMonitor = true;
        this.handler = new WebMonitorClosingHandler();
    }

    /**
     * Create new Monitoring instance
     * @param label monitoring description label
     * @return new monitoring instance
     */
    public Monitor start(String label) {
        return new Monitor(label, System.currentTimeMillis(), enableMonitor, handler);
    }

    /**
     * Enable or disable monitoring problematically
     * @param flag true - enable monitoring, false - disable monitoring
     */
    public void setEnableMonitor(boolean flag) {
        enableMonitor = flag;
    }

    public boolean getEnableMonitor() {
        return enableMonitor;
    }

    public MonitorClosingHandler getHandler() {
        return handler;
    }

    public void setHandler(MonitorClosingHandler handler) {
        this.handler = handler;
    }
}