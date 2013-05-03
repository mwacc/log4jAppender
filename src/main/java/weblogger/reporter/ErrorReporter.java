package weblogger.reporter;


import common.LoggedMessageFormat;
import common.Message;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LocationInfo;
import webloger.MessageFormatter;
import webloger.service.MessageService;

public final class ErrorReporter {

    private static final String FQCN = ErrorReporter.class.getName();

    private String messageFormatter = LoggedMessageFormat.messageFormat;
    private MessageService messageService;

    public void reportMessage(Message message) {
        messageService.send(message);
    }

    public void reportMessage(String message, String errorLevel, Throwable th) {
        Message msg = MessageFormatter.getMessage(getMessageFormatter(), errorLevel, message, th, new LocationInfo(th, FQCN));
        messageService.send(msg);
        msg = null;
    }

    public void reportFatal(String errorMessage, Throwable th) {
        reportMessage(errorMessage, Level.FATAL.toString(), null);
    }

    public void reportFatal(String errorMessage) {
        reportMessage(errorMessage, Level.FATAL.toString(), null);
    }

    public void reportError(String errorMessage, Throwable th) {
        reportMessage(errorMessage, Level.ERROR.toString(), null);
    }

    public void reportError(String errorMessage) {
        reportMessage(errorMessage, Level.ERROR.toString(), null);
    }

    public void reportWarn(String errorMessage, Throwable th) {
        reportMessage(errorMessage, Level.WARN.toString(), null);
    }

    public void reportWarn(String errorMessage) {
        reportMessage(errorMessage, Level.WARN.toString(), null);
    }


    public String getMessageFormatter() {
        return messageFormatter;
    }

    public void setMessageFormatter(String messageFormatter) {
        this.messageFormatter = messageFormatter;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}