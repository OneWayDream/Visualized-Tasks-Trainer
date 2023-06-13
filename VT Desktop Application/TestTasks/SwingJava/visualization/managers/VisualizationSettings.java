package visualization.managers;

import visualization.managers.*;

public class VisualizationSettings {

    private boolean isFirstCarRegistredFlag = false;
    private int firstCarSpeed = 0;
    private boolean isSecondCarRegistredFlag = false;
    private int secondCarSpeed = 0;
    private RaceState raceState = RaceState.WAITING;

    public void setFirstCarRegistred(boolean isFirstCarRegistredFlag){
        this.isFirstCarRegistredFlag = isFirstCarRegistredFlag;
    }

    public void setSecondCarRegistred(boolean isSecondCarRegistredFlag){
        this.isSecondCarRegistredFlag = isSecondCarRegistredFlag;
    }

    public boolean isCarRegistred(int carNumber){
        if (carNumber == 1){
            return isFirstCarRegistredFlag;
        } else {
            return isSecondCarRegistredFlag;
        }
    }

    public void setFirstCarSpeed(int firstCarSpeed){
        this.firstCarSpeed = firstCarSpeed;
    }

    public void setSecondCarSpeed(int secondCarSpeed){
        this.secondCarSpeed = secondCarSpeed;
    }

    public int getCarSpeed(int carNumber){
        if (carNumber == 1){
            return firstCarSpeed;
        } else {
            return secondCarSpeed;
        }
    }

    public void setRaceState(RaceState raceState){
        this.raceState = raceState;
    }

    public RaceState getRaceState(){
        return raceState;
    }

}