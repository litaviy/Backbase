package com.blackbase.test.logging;

/**
 * Created by klitaviy on 1/23/19-10:05 PM.
 */
public class LoggerProvider {

    private static final Logger ourInstance = new LoggerImpl();

    private LoggerProvider() {
    }

    public static Logger provide() {
        return ourInstance;
    }
}
