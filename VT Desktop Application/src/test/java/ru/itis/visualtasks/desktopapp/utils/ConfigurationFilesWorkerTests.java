package ru.itis.visualtasks.desktopapp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Properties;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class ConfigurationFilesWorkerTests {

    private final static String PROPERTIES_FILE_PATH = "application.properties";
    private final static String YAML_CONFIG_FILE_PATH = "test-config.yaml";

    @Test
    public void load_properties(){
        Properties properties = ConfigurationFilesWorker.loadProperties(PROPERTIES_FILE_PATH);
        Assertions.assertNotNull(properties);
        Assertions.assertEquals("test-value", properties.getProperty("test-property"));
    }

    @Test
    public void get_properties_map(){
        Map<String, String> properties = ConfigurationFilesWorker
                .propertiesToMap(ConfigurationFilesWorker.loadProperties(PROPERTIES_FILE_PATH));
        Assertions.assertNotNull(properties);
        Assertions.assertEquals("test-value", properties.get("test-property"));
    }

    @Test
    public void load_yaml_as_map(){
        Map<String, String> properties = ConfigurationFilesWorker
                .loadYamlAsMap(YAML_CONFIG_FILE_PATH);
        Assertions.assertNotNull(properties);
        Assertions.assertEquals("test-value", properties.get("test.property"));
    }

}
