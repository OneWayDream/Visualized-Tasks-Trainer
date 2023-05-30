package visualization.managers;

import visualization.panels.*;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationArgumentsStorage;
import wrappers.Car;

public class RaceManager {

    public static void registerOnTheRace(Car car){
        if (!isFirstCarRegister()){
            VisualizationArgumentsStorage.put("isFirstCarRegister", true);
            VisualizationArgumentsStorage.put("firstCarSpeed", car.getSpeed());
        } else if (!isSecondCarRegister()){
            VisualizationArgumentsStorage.put("isSecondCarRegister", true);
            VisualizationArgumentsStorage.put("secondCarSpeed", car.getSpeed());
        }
    }

    public static boolean isFirstCarRegister(){
        return VisualizationArgumentsStorage.contains("isFirstCarRegister");
    }

    public static boolean isSecondCarRegister(){
        return VisualizationArgumentsStorage.contains("isSecondCarRegister");
    }

    public static Integer getFirstCarSpeed(){
        return (Integer) VisualizationArgumentsStorage.get("firstCarSpeed");
    }

    public static Integer getSecondCarSpeed(){
        return (Integer) VisualizationArgumentsStorage.get("secondCarSpeed");
    }

}