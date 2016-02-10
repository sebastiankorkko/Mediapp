package model;

import java.util.Random;

public class BloodPressureSensor {
    private int lowPressure;
    private int highPressure;
    private boolean isInstalled;
    
    public BloodPressureSensor(boolean isInstalled){       
        this.isInstalled = isInstalled;
        this.update();       
    }
    
    public boolean isInstalled(){
        return this.isInstalled;
    }
    
    public int getLow(){
        return this.lowPressure;        
    }
    
    public int getHigh(){
        return this.highPressure;
    }
    
    public void update(){
        if(isInstalled == true){
            Random rand = new Random();
            this.lowPressure = rand.nextInt(130-30) + 30;
            this.highPressure = rand.nextInt(230-lowPressure) + lowPressure;
        }
    }
}
