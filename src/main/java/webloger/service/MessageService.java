package webloger.service;

import common.Message;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessageService extends GenericService {

    public MessageService(String token, String projectId) {
        super(token, projectId);
    }

    public String getEndpoint() {
        return "/msg-log";
    }

    public void send(Message message) {
        send(message.toString());
    }
}