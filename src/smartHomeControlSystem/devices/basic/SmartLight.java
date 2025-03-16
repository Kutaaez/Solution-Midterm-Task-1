package smartHomeControlSystem.devices.basic;

import smartHomeControlSystem.devices.basic.SmartDevice;

public class SmartLight extends SmartDevice {
    private int lightBrightness;
    private String lightColor;

    public SmartLight(String smartLightName){
        super(smartLightName);
        this.lightBrightness = 100;
        this.lightColor = "white";
    }

    @Override
    public void performFunction() {

    }

    public void setLightBrightness(int newLightBrightness){
        if (newLightBrightness < 0 || newLightBrightness > 100) {
            System.out.println("Brightness must be between 0 and 100.");
        } else {
            this.lightBrightness = newLightBrightness;
            System.out.println(getName() + " brightness set to " + newLightBrightness + "%.");
        }
    }

    public void setColor(String newLightColor) {
        this.lightColor = newLightColor;
        System.out.println(getName() + " color set to " + newLightColor + ".");
    }

    public void turnOn() {
        super.turnOn();
        System.out.println(getName() + " is now glowing at " + lightBrightness + "% brightness in " + lightColor + " color.");
    }

    public void turnOff() {
        super.turnOff();
        System.out.println(getName() + " is now OFF.");
    }
}
