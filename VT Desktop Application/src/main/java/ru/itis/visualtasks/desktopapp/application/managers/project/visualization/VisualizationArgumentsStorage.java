package ru.itis.visualtasks.desktopapp.application.managers.project.visualization;

import java.util.HashMap;
import java.util.Map;

public class VisualizationArgumentsStorage {

    private static final Map<String, Object> storage = new HashMap<>();

    public static void put(String argName, Object value){
        storage.put(argName, value);
    }

    public static Object get(String argName){
        return storage.get(argName);
    }

    public static boolean contains(String argName){
        return storage.containsKey(argName);
    }

    public static void reset(){
        storage.clear();
    }

}
