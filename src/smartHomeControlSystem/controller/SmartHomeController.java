package smartHomeControlSystem.controller;

import smartHomeControlSystem.manager.SmartDeviceManager;

public class SmartHomeController {
    private final SmartDeviceManager deviceManager;

    public SmartHomeController(SmartDeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }

    public void executeCommand(String command) {
        String lowerCommand = command.toLowerCase();

        switch (lowerCommand.split(" ")[0]) {
            case "turn":
                if (lowerCommand.equals("turn all on")) {
                    deviceManager.turnAllOn();
                } else if (lowerCommand.equals("turn all off")) {
                    deviceManager.turnAllOff();
                } else if (lowerCommand.startsWith("turn on ")) {
                    deviceManager.turnOnDevice(lowerCommand.substring(8));
                } else if (lowerCommand.startsWith("turn off ")) {
                    deviceManager.turnOffDevice(lowerCommand.substring(9));
                } else {
                    System.out.println("Invalid turn command.");
                }
                break;

            case "show":
                if (lowerCommand.equals("show status")) {
                    deviceManager.showStatus();
                } else if (lowerCommand.startsWith("show device ")) {
                    deviceManager.showDeviceStatus(lowerCommand.substring(12));
                } else if (lowerCommand.startsWith("show group ")) {
                    deviceManager.showGroupStatus(lowerCommand.substring(11));
                } else {
                    System.out.println("Invalid show command.");
                }
                break;

            default:
                System.out.println("Unknown command: " + command);
        }
    }
}
