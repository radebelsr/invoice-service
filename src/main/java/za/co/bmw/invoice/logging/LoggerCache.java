package za.co.bmw.invoice.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerCache {

    private static Map<Class<?>, Logger> loggerMap = new ConcurrentHashMap<>();

    static Logger findLogger(Class<?> loggingClass) {
        if(loggerMap.containsKey(loggingClass)){
            return loggerMap.get(loggingClass);
        }
        Logger loggingClassLogger = LoggerFactory.getLogger(loggingClass.getName());
        loggerMap.put(loggingClass, loggingClassLogger);
        return loggingClassLogger;
    }

}
