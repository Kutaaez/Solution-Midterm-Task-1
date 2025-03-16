package smartHomeControlSystem.decorators;

import smartHomeControlSystem.devices.SmartDevice;
import smartHomeControlSystem.devices.basic.SmartAirPurifier;
import smartHomeControlSystem.devices.basic.SmartLight;

public class EnergySavingModeDecorator extends SmartDevice {
    private final SmartDevice device;
    private boolean energySavingMode;

    public EnergySavingModeDecorator(SmartDevice device) {
        super(device.getName());
        this.device = device;
        this.energySavingMode = false;
    }

    public void enableEnergySavingMode() {
        if (!energySavingMode) {
            energySavingMode = true;
            System.out.println(device.getName() + " is now in Energy Saving Mode.");
            applyEnergySavingSettings();
        }
    }

    public void disableEnergySavingMode() {
        if (energySavingMode) {
            energySavingMode = false;
            System.out.println(device.getName() + " exited Energy Saving Mode.");
        }
    }

    private void applyEnergySavingSettings() {
        if (device instanceof SmartLight light) {
            light.setLightBrightness(30);
        } else if (device instanceof SmartAirPurifier purifier) {
            purifier.setFanSpeed(1);
        }
    }

    @Override
    public void turnOn() {
        device.turnOn();
    }

    @Override
    public void turnOff() {
        device.turnOff();
    }

    @Override
    public void performFunction() {
        device.performFunction();
    }

    @Override
    public boolean isOn() {
        return device.isOn();
    }
}