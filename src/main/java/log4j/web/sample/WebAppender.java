package log4j.web.sample;

import common.Message;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import webloger.service.MessageService;

import java.util.concurrent.atomic.AtomicReference;

public class WebAppender extends AppenderSkeleton {

    private String token;
    private String projectId;

    private WebPatternLayout layout = new WebPatternLayout();
    private AtomicReference<MessageService> service = new AtomicReference<MessageService>();

    private void init() {
        service.set(new MessageService(getToken(), getProjectId()));
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        if( loggingEvent.getLevel().toInt() < Level.WARN_INT ) return;
        if( service.get() == null ){
            synchronized (this) {
                init();
            }
        }

        Message msg = getLogStatement(loggingEvent);
        service.get().send(msg);
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