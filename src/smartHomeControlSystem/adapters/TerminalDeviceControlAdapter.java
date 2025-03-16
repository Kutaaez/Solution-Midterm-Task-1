// TerminalDeviceControlAdapter.java
package smartHomeControlSystem.adapters;


import smartHomeControlSystem.devices.SmartDevice;
import smartHomeControlSystem.facade.SmartHomeController;
//this addapter adapting comands from terminal to our smart home system.

public class TerminalDeviceControlAdapter {
    private final SmartHomeController smartHomeController;

    public TerminalDeviceControlAdapter(SmartHomeController smartHomeController) {
        this.smartHomeController = smartHomeController;
    }

    public void processCommand(String command) {
        String[] commandParts = command.split(" ");
        if (commandParts.length < 4) {
            System.out.println("Invalid command format.");
            return;
        }
        String action = commandParts[1];
        String deviceName = commandParts[2];
        if (deviceExists(deviceName)) {
            if (action.equalsIgnoreCase("on")) {
                smartHomeController.turnOnDevice(deviceName);
                System.out.println(deviceName + " has been turned on.");
            } else if (action.equalsIgnoreCase("off")) {
                smartHomeController.turnOffDevice(deviceName);
                System.out.println(deviceName + " has been turned off.");
            } else {
                System.out.println("Unknown action: " + action);
            }
        } else {
            System.out.println("Device " + deviceName + " not found.");
        }
    }


    private boolean deviceExists(String deviceName) {
        for (SmartDevice device : smartHomeController.getDevices()) {
            if (device.getName().equalsIgnoreCase(deviceName)) {
                return true;
            }
        }
        return false;
    }
}
