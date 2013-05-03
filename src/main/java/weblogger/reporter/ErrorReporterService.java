package weblogger.reporter;

import common.LoggedMessageFormat;
import common.Message;
import org.apache.log4j.Level;
import webloger.service.MessageService;

public class ErrorReporterService {

    private final ErrorReporter reporter;

    public ErrorReporterService(String token, String projectId, String messageFormat) {
        MessageService service = new MessageService(token, projectId);
        reporter = new ErrorReporter();
        reporter.setMessageService(service);
        reporter.setMessageFormatter(messageFormat);
    }

    public ErrorReporterService(String token, String projectId) {
        this(token, projectId, LoggedMessageFormat.messageFormat);
    }

    public void reportMessage(Message message) {
        reporter.reportMessage(message);
    }

    public void reportMessage(String message, String errorLevel, Throwable th) {
        reporter.reportMessage(message, errorLevel, th);
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

}