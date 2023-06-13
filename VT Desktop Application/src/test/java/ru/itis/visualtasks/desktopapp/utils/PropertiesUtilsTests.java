package ru.itis.visualtasks.desktopapp.utils;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Properties;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class PropertiesUtilsTests {

    @Test
    public void get_instance(){
        Assertions.assertNotNull(PropertiesUtils.getInstance());
    }

    @Test
    public void check_singleton(){
        PropertiesUtils firstInstance = PropertiesUtils.getInstance();
        PropertiesUtils secondInstance = PropertiesUtils.getInstance();
        Assertions.assertSame(firstInstance, secondInstance);
    }

    @Test
    public void get_properties(){
        Properties properties = PropertiesUtils.getInstance().getProperties();
        Assertions.assertEquals("test-value", properties.getProperty("test-property"));
    }

    @Test
    @SneakyThrows
    public void save_properties_instance(){
        PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();
        Properties properties = propertiesUtils.getProperties();
        properties.setProperty("test-property", "new-test-value");
        propertiesUtils.storeProperties(properties);
        properties = propertiesUtils.getProperties();
        Assertions.assertEquals("new-test-value", properties.getProperty("test-property"));
        returnDefaultProperties();
    }

    private void returnDefaultProperties(){
        PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();
        Properties properties = propertiesUtils.getProperties();
        properties.setProperty("test-property", "test-value");
        propertiesUtils.storeProperties(properties);
    }

}
