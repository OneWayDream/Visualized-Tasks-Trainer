package ru.itis.graduationwork.utils;

import lombok.RequiredArgsConstructor;
import org.yaml.snakeyaml.Yaml;
import ru.itis.graduationwork.exceptions.properties.NotFoundPropertiesException;
import ru.itis.graduationwork.exceptions.properties.PropertiesReadException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class ConfigurationFilesWorker {

    public static Properties loadProperties(String propertiesPath){
        try (InputStreamReader inputStream =
                     new InputStreamReader(Objects.requireNonNull(
                             ConfigurationFilesWorker.class.getClassLoader().getResourceAsStream(propertiesPath)
                     ), StandardCharsets.UTF_8)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException ex) {
            throw new PropertiesReadException(ex);
        } catch (NullPointerException ex){
            throw new NotFoundPropertiesException("Properties file " + propertiesPath + " is not found.");
        }
    }

    public static Map<String, String> propertiesToMap(Properties properties){
        Map<String, String> result = new HashMap<>();
        for (Object key : properties.keySet()){
            String stringKey = (String) key;
            result.put(stringKey, properties.getProperty(stringKey));
        }
        return result;
    }

    public static Map<String, String> loadYamlAsMap(String configFilePath){
        try (InputStreamReader inputStream =
                     new InputStreamReader(Objects.requireNonNull(
                             ConfigurationFilesWorker.class.getClassLoader().getResourceAsStream(configFilePath)
                     ), StandardCharsets.UTF_8)) {
            Yaml yaml = new Yaml();
            Map<String, Object> yamlMap = yaml.load(inputStream);
            return yamlAsMap(yamlMap);
        } catch (IOException ex) {
            throw new PropertiesReadException(ex);
        } catch (NullPointerException ex){
            throw new NotFoundPropertiesException("Properties file " + configFilePath + " is not found.");
        }
    }

    private static Map<String, String> yamlAsMap(Map<String, Object> yamlEntries){
        YamlParser parser = new YamlParser();
        return parser.getMap(yamlEntries);
    }

    @RequiredArgsConstructor
    private static class YamlParser{

        private Map<String, String> resultMap;
        private StringBuilder currentKey;

        private Map<String, String> getMap(Map<String, Object> yamlMap){
            resultMap = new HashMap<>();
            currentKey = new StringBuilder();
            iterate(yamlMap);
            return resultMap;
        }

        private void iterate(Map<String, Object> map){
            for (Map.Entry<String, Object> entry : map.entrySet()){
                currentKey.append(entry.getKey());
                if (entry.getValue() instanceof Map<?, ?>){
                    currentKey.append(".");
                    iterate((Map<String, Object>) entry.getValue());
                    currentKey.delete(currentKey.length() - 1, currentKey.length());
                } else {
                    resultMap.put(currentKey.toString(), (String) entry.getValue());
                }
                currentKey.delete(currentKey.length() - entry.getKey().length(), currentKey.length());
            }
        }

    }

}
