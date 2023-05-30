package wrappers;

import visualization.managers.RaceManager;

public class Car {

    private int speed;
    private int number;
    private Runnable runnable;

    public Car(int number, int speed, Runnable runnable){
        this.number = number;
        this.speed = speed;
        this.runnable = runnable;
    }

    public void addToTheRace(){
        RaceManager.registerOnTheRace(this);
    }

    public int getSpeed(){
        return speed;
    }

    public void driveToTheRace(){
        new Thread(runnable).start();
    }

}