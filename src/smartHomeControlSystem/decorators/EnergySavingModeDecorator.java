package smartHomeControlSystem.decorators;

import smartHomeControlSystem.devices.SmartDevice;
import smartHomeControlSystem.devices.basic.SmartAirPurifier;
import smartHomeControlSystem.devices.basic.SmartLight;

public class EnergySavingModeDecorator extends SmartDevice {
    private SmartDevice device;
    private boolean energySavingMode;

    public EnergySavingModeDecorator(SmartDevice device) {
        super(device.getName() + " (Energy Saving Mode)");
        this.device = device;
        this.energySavingMode = false;
    }

    public void enableEnergySavingMode() {
        energySavingMode = true;
        System.out.println(device.getName() + " is now in Energy Saving Mode.");
        applyEnergySavingSettings();
        LoggingDecorator.log(device.getName() , " is now in Energy Saving Mode.");

    }

    public void disableEnergySavingMode() {
        energySavingMode = false;
        System.out.println(device.getName() + " exited Energy Saving Mode.");
        LoggingDecorator.log(device.getName() , " exited Energy Saving Mode.");

    }

    private void applyEnergySavingSettings() {
        if (device instanceof SmartLight light) {
            light.setLightBrightness(30);
            LoggingDecorator.log(device.getName() , " brightness set to 30 (Energy Saving Mode)");

        } else if (device instanceof SmartAirPurifier purifier) {
            purifier.setFanSpeed(1);
            LoggingDecorator.log(device.getName() , " fan speed set to 1 (Energy Saving Mode)");

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
}
