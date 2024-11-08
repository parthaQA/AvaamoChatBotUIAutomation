package com.helper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LogUtil {

    public  static Logger logger = LogManager.getLogger();

    public static void logInfo(String message){

        logger.info(message);
    }
    public static void logDebug(String message){

        logger.debug(message);
    }

    public static void logDebug(String message, Exception msg){

        logger.debug(message,msg);
    }

    public static void logDebug(List<String> message){

        logger.debug(message);
    }
}