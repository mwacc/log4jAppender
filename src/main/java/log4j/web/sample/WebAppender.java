package log4j.web.sample;

import com.google.gson.Gson;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebAppender extends AppenderSkeleton {

    private final static int maxRetries = 3;

    private final String host;
    private final Integer port;
    private String token;
    private String projectId;

    private WebPatternLayout layout = new WebPatternLayout();

    public WebAppender() {
        this.host = "localhost";
        this.port = 8081;
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        try {
            if( loggingEvent.getLevel().toInt() < Level.WARN_INT ) return;


            Message msg = getLogStatement(loggingEvent);
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
        } catch(Exception e) {
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
                System.out.println(body);
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(String.format("{\"message\":\"%s\"}", body));
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

    protected Message getLogStatement(LoggingEvent event){
        return layout.formatToMessage(event);
    }

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}