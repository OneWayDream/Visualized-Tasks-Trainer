package ru.itis.visualtasks.desktopapp.utils;

import ru.itis.visualtasks.desktopapp.exceptions.properties.PropertiesReadException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

    private final static String PROPERTIES_FILE_PATH = "application.properties";

    private Properties properties;

    private static class InstanceHolder {
        private static final PropertiesUtils INSTANCE = new PropertiesUtils();
    }

    public static PropertiesUtils getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public Properties getProperties(){
        if (!arePropertiesLoaded()){
            properties = ConfigurationFilesWorker.loadProperties(PROPERTIES_FILE_PATH);
        }
        return properties;
    }

    private boolean arePropertiesLoaded(){
        return properties != null;
    }

    public void storeProperties(){
        try{
            properties.store(new FileOutputStream(PROPERTIES_FILE_PATH), "");
        } catch (IOException ex) {
            throw new PropertiesReadException(ex);
        }

    }

}
