package smartHomeControlSystem.devices.advanced;

import smartHomeControlSystem.devices.basic.SmartLight;

public class AdvancedSmartLight extends SmartLight {
    private String color;

    public AdvancedSmartLight(String advancedSmartLightName) {
        super(advancedSmartLightName);
        this.color = "White";
    }

    public void setColor(String color) {
        this.color = color;
        System.out.println(getName() + ": Light color set to " + color);
    }

    @Override
    public void performFunction() {
        System.out.println(getName() + " is providing advanced lighting with color " + color);
    }
}