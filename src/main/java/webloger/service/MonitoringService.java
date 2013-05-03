package webloger.service;

import common.Message;
import common.MonitoringMessage;

public class MonitoringService extends GenericService {

    public MonitoringService(String token, String projectId) {
        super(token, projectId);
    }

    public String getEndpoint() {
        return "/monitoring";
    }

    public void send(MonitoringMessage message) {
        send(message.toString());
    }
}