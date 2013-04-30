package log4j.web.sample;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebPatternLayout extends org.apache.log4j.Layout{

    protected String conversionPattern = "%p %d %P %t %F %C %L %m %S";
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

    public WebPatternLayout() {
        setConversionPattern(conversionPattern);
    }

    @Override
    public String format(LoggingEvent loggingEvent) {
        return null;
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }

    protected PatternParser createPatternParser(String pattern) {
        return new WebPatternParser(pattern);
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
     * F    Used to output the file name where the logging request was issued.
     * C    Used to output the fully qualified class name of the caller issuing the logging request.
     * L    Used to output the line number from where the logging request was issued.
     * M    Used to output the method name where the logging request was issued.
     * m	Used to output the application supplied message associated with the logging event.
     * S    stack trace
     */
    public String getConversionPattern() {
        return conversionPattern;
    }

    public Message formatToMessage(LoggingEvent event) {
        Message msg = new Message();

        if( conversionPattern.contains("%p") ){
            msg.setPriority( event.getLevel().toString() );
        }
        if( conversionPattern.contains("%d") ){
            msg.setDate( df.format(new Date(event.getTimeStamp())) );
        }
        if( conversionPattern.contains("%P") ){
            String hostName = "unknown";
            try {
                hostName = InetAddress.getLocalHost().getHostName();
            } catch(UnknownHostException e) { }
            msg.setHostName(hostName);
        }
        if( conversionPattern.contains("%t") ){
            msg.setThreadName(event.getThreadName());
        }
        if( conversionPattern.contains("%F") ){
            msg.setFileName(event.getLocationInformation().getFileName());
        }
        if( conversionPattern.contains("%C") ){
            msg.setClassName(event.getLocationInformation().getClassName());
        }
        if( conversionPattern.contains("%L") ){
            msg.setLineNumber(event.getLocationInformation().getLineNumber());
        }
        if( conversionPattern.contains("%M") ){
            msg.setMethodName(event.getLocationInformation().getMethodName());
        }
        if( conversionPattern.contains("%m") ){
            msg.setMessage(event.getMessage().toString());
        }
        if( conversionPattern.contains("%S") && event.getThrowableInformation() != null &&
                event.getThrowableInformation().getThrowable().getStackTrace() != null){
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement element : event.getThrowableInformation().getThrowable().getStackTrace()) {
                sb.append(element.toString());
                sb.append("\n");
            }
            msg.setStackTrace( sb.toString() );
        }

        return msg;
    }
}