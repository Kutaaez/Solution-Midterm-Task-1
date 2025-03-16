package smartHomeControlSystem.devices.basic;

public abstract class SmartDevice {
    protected String deviceName;
    protected boolean deviceIsOn;

    public SmartDevice(String name) {
        this.deviceName = name;
        this.deviceIsOn = false;
    }

    public void turnOn() {
        deviceIsOn = true;
        System.out.println(deviceName + " is now ON.");
    }

    public void turnOff() {
        deviceIsOn = false;
        System.out.println(deviceName + " is now OFF.");
    }

    public boolean isOn() {
        return deviceIsOn;
    }

    public String getName() {
        return deviceName;
    }

    public abstract void performFunction();
}
