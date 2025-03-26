package smartHomeControlSystem.adapters;

import smartHomeControlSystem.devices.SmartDevice;
import smartHomeControlSystem.manager.SmartDeviceManager;

public class SmartSpeakerAdapter extends SmartDevice {
    private final SmartDeviceManager controller;

    public SmartSpeakerAdapter(SmartDeviceManager controller, String name) {
        super(name);
        this.controller = controller;
    }

    @Override
    public void turnOn() {
        System.out.println(getName() + " is now listening for voice commands.");
    }

    @Override
    public void turnOff() {
        System.out.println(getName() + " has stopped listening for voice commands.");
    }

    @Override
    public void performFunction() {
        System.out.println(getName() + " is processing voice commands.");
    }

    public void executeVoiceCommand(String command) {
        System.out.println(getName() + " executing voice command: " + command);
        if (command.contains("turn on")) {
            String deviceName = command.replace("turn on", "").trim();
            controller.turnOnDevice(deviceName);
        } else if (command.contains("turn off")) {
            String deviceName = command.replace("turn off", "").trim();
            controller.turnOffDevice(deviceName);
        } else {
            System.out.println("Unsupported command: " + command);
        }
    }
}