package smartHomeControlSystem.devices;

import smartHomeControlSystem.devices.basic.SmartDevice;

public class SmartAirPurifier extends SmartDevice {
    private int airPurifieirFanSpeed;
    private int airPurifieirQuality;
    @Override
    public void performFunction() {
        if (isOn()) {
            System.out.println(getName() + " is purifying air at fan speed " + airPurifieirFanSpeed + ".");
            checkAirQuality();
        } else {
            System.out.println(getName() + " is OFF. Turn it on to start air purification.");
        }
    }

    public SmartAirPurifier(String smartAirPurifierName) {
        super(smartAirPurifierName);
        this.airPurifieirFanSpeed = 3;
        this.airPurifieirQuality = 60;
    }
    public void setFanSpeed(int speed) {
        if (speed < 1 || speed > 5) {
            System.out.println("Invalid speed. Please select a value between 1 and 5.");
        } else {
            this.airPurifieirFanSpeed = speed;
            this.airPurifieirQuality = 20*airPurifieirFanSpeed;
            System.out.println(getName() + ": Fan speed set to " + speed);
        }
    }

    public void checkAirQuality() {
        System.out.println(getName() + ": Current air quality is " + airPurifieirQuality + "/100.");
    }

    public void turnOn() {
        super.turnOn();
        System.out.println(getName() + " is now purifying the air.");
    }

    public void turnOff() {
        super.turnOff();
        System.out.println(getName() + " has stopped purifying the air.");
    }
}
