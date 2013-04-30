package webloger;

import common.Message;
import org.apache.log4j.spi.LocationInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageFormatter {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

    private MessageFormatter(){ }

    public static Message getMessage(String conversionPattern, String errorLevel, String message, Throwable th, LocationInfo locationInfo) {
        Message msg = new Message();

        if( conversionPattern.contains("%p") ){
            msg.setPriority( errorLevel );
        }
        if( conversionPattern.contains("%d") ){
            msg.setDate( df.format(new Date( System.currentTimeMillis() )) );
        }
        if( conversionPattern.contains("%P") ){
            String hostName = "unknown";
            try {
                hostName = InetAddress.getLocalHost().getHostName();
            } catch(UnknownHostException e) { }
            msg.setHostName(hostName);
        }
        if( conversionPattern.contains("%t") ){
            msg.setThreadName( Thread.currentThread().getName() );
        }
        if( conversionPattern.contains("%m") ){
            msg.setMessage(message);
        }
        if( conversionPattern.contains("%S") && th != null &&
                th.getStackTrace() != null){
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement element : th.getStackTrace()) {
                sb.append(element.toString());
                sb.append("\n");
            }
            msg.setStackTrace( sb.toString() );
        }
        if( conversionPattern.contains("%F") && locationInfo != null ){
            msg.setFileName(locationInfo.getFileName());
        }
        if( conversionPattern.contains("%C") && locationInfo != null ){
            msg.setClassName(locationInfo.getClassName());
        }
        if( conversionPattern.contains("%L") && locationInfo != null ){
            msg.setLineNumber(locationInfo.getLineNumber());
        }
        if( conversionPattern.contains("%M") && locationInfo != null ){
            msg.setMethodName(locationInfo.getMethodName());
        }

        return msg;
    }

}