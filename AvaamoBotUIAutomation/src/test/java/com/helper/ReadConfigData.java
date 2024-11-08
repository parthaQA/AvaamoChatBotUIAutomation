package com.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigData {

    private static FileInputStream fis = null;
    private static Properties prop = null;

    public static String readData(String key) throws IOException {
        try {
            fis = new FileInputStream("./resources/Config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            LogUtil.logDebug("Exception occured", new Exception("file not found"));
        } finally {
            fis.close();
        }
        return prop.getProperty(key);
    }
}
