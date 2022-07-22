package sementic;


import org.apache.log4j.Logger;

public class ExpClient {
    private static final Logger logger = Logger.getLogger(ExpClient.class);

    public static void main(String[] args) {
        try {
            ExpTest.testMethod();
        } catch (Exception e) {
            System.out.println("------------------------------> msg, e <------------------------------");
            logger.info("err...", e);
            System.out.println("------------------------------> e <------------------------------");
            logger.info(e);
            System.out.println("------------------------------> msg <------------------------------");
            logger.info(e.getMessage());
        }
    }
}
