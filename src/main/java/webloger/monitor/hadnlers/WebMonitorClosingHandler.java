package webloger.monitor.hadnlers;

import common.MonitoringMessage;
import webloger.service.MonitoringService;

public class WebMonitorClosingHandler implements MonitorClosingHandler {

    private final MonitoringService monitoringService;


    public WebMonitorClosingHandler(String projectId, String token){
        this.monitoringService = new MonitoringService(token, projectId);
    }

    @Override
    public void handle(String label, long startAt, long endAt) {
        long usedAt = endAt-startAt;
        if( usedAt > 0 )
            monitoringService.send( new MonitoringMessage(label, usedAt));
    }
}