package model;

import java.util.Random;

public class HeartSensor {
    
    private int heartRate;
    private boolean isInstalled;
    
    public HeartSensor(boolean isInstalled){
        this.isInstalled = isInstalled;
        this.update();
    }    
    
    public boolean isInstalled(){
        return this.isInstalled;
    }
    
    public int getBpm(){
        return this.heartRate;
    }
    
    public void update(){
        if(isInstalled == true) {
            Random rand = new Random();
            this.heartRate = rand.nextInt(210 - 40) + 40;
        }
    }
}

