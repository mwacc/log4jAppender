package log4j.web.sample;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;

public class WebPatternLayout extends PatternLayout {

    protected PatternParser createPatternParser(String pattern) {
        return new WebPatternParser(pattern);
    }

}