package webloger;

import common.Message;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessageService {
    private final static int maxRetries = 3;

    private final String token;
    private final String projectId;
    private final String host;
    private final int port;

    public MessageService(String token, String projectId) {
        this.token = token;
        this.projectId = projectId;
        this.host = "localhost";
        this.port = 8081;
    }

    public void send(Message msg) {
        try {
            URL url = new URL("http", host, port, "/msg-log");

            // AWS ELB sometimes returns 503 Status code when load is increasing.
            // We retry several times to send error
            int retries = 0;
            boolean stopLoop = false;
            while (!stopLoop) {
                try {
                    stopLoop = sendRequest(url, msg.toString());
                } catch (HTTPException e) {
                    if (e.getStatusCode() != 503 || retries >= maxRetries) {
                        throw e;
                    }
                    retries++;

                    int delay = 2*retries*1000;
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        } catch (Exception e){
            //TODO: fix this code
            e.printStackTrace();
        }
    }

    private boolean sendRequest(URL url, String body) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "OAuth " + token);
            conn.setRequestProperty("User-Agent", "WebLogger");

            if (body != null) {
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
            }

            conn.connect();

            if (body != null) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write( body );
                out.close();
            }

            int status = conn.getResponseCode();
            if (status != 200) {
                // TODO: error
                System.err.println(String.format("Get status code %d with message %s", status, conn.getResponseMessage()));
            }
        } finally {
            if(conn != null) {
                conn.disconnect();
            }
        }

        return true;
    }
}