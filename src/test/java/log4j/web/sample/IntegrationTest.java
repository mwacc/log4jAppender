package log4j.web.sample;

import org.apache.log4j.Logger;

public class IntegrationTest {

    // get a logger instance named "com.foo"
    private static Logger logger = Logger.getLogger("log4j.web.sample");

    public static void main(String[] args) {
        logger.debug("..... Debug message");
        logger.info("..... Info message");
        logger.warn("..... Warn message");
        try {
            throw new Exception("BUM");
        } catch(Exception e) {
            logger.error("Exception must be logged", e);
        }

        addLayer();
    }

    private static void addLayer() {
        try {
            throw new Exception("BUMED LAYER");
        } catch(Exception e) {
            logger.error("Exception must be logged", e);
        }
    }
}