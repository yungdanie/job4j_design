package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        int integer = 13;
        long longer = 14;
        short shorted = 15;
        double doubled = 15.5;
        float floated = 16.7656f;
        byte byted = 1;
        char charted = 'a';
        boolean bool = true;
        LOG.debug("Integer : {}, Longer : {}, Shorted : {}, Doubled : {}, Floated : {}, Byted : {}, Charted : {}, Boolean :{}",
                integer,
                longer,
                shorted,
                doubled,
                floated,
                byted,
                charted,
                bool);
    }
}