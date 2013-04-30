package log4j.web.sample;

import common.LoggedMessageFormat;
import common.Message;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;
import webloger.MessageFormatter;

public class WebPatternLayout extends org.apache.log4j.Layout{

    protected String conversionPattern = LoggedMessageFormat.messageFormat;


    @Override
    public String format(LoggingEvent loggingEvent) {
        return null;
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }

    public void setConversionPattern(java.lang.String conversionPattern) {
        this.conversionPattern = conversionPattern;
    }

    @Override
    public void activateOptions() {
        // Nothing to do
    }

    /**
     * p	Used to output the priority of the logging event.
     * d    Used to output the date of the logging event, ISO8601
     * P    Ip address
     * t	Used to output the name of the thread that generated the logging event.
     * F*   Used to output the file name where the logging request was issued.
     * C*   Used to output the fully qualified class name of the caller issuing the logging request.
     * L*   Used to output the line number from where the logging request was issued.
     * M*   Used to output the method name where the logging request was issued.
     * m	Used to output the application supplied message associated with the logging event.
     * S    stack trace
     */
    public String getConversionPattern() {
        return conversionPattern;
    }

    public Message formatToMessage(LoggingEvent event) {
        Throwable th = event.getThrowableInformation() != null && event.getThrowableInformation().getThrowable() != null ?
                event.getThrowableInformation().getThrowable() : null;

        return MessageFormatter.getMessage(getConversionPattern(), event.getLevel().toString(), event.getMessage().toString(),
                th, event.getLocationInformation());
    }
}