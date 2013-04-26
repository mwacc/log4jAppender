package log4j.web.sample;


import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class WebPatternParser extends PatternParser {

    public WebPatternParser(String pattern) {
        super(pattern);
    }

    protected void finalizeConverter(char c) {
        if( 'P' == c){
            PatternConverter pc = new IpPatternConverter();
            currentLiteral.setLength(0);
            addConverter(pc);
        }
        super.finalizeConverter(c);
    }

    private static class IpPatternConverter extends PatternConverter {
        private static String hostName;

        static {
            try {
                hostName = InetAddress.getLocalHost().getHostName();
            } catch(UnknownHostException e) {
                hostName = "unknown";
            }
        }

        @Override
        protected String convert(LoggingEvent loggingEvent) {
            return hostName;
        }
    }
}