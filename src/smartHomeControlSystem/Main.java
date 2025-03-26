package smartHomeControlSystem;

import smartHomeControlSystem.adapters.TerminalDeviceControlAdapter;
import smartHomeControlSystem.controller.SmartHomeController;
import smartHomeControlSystem.manager.SmartDeviceManager;

public class Main {
    public static void main(String[] args) {
        SmartDeviceManager deviceManager = new SmartDeviceManager();
        SmartHomeController controller = new SmartHomeController(deviceManager);
        TerminalDeviceControlAdapter adapter = new TerminalDeviceControlAdapter(controller, deviceManager);
        System.out.println("Welcome to the Smart Home Control System!");
        System.out.println("Type 'help' to see a list of available commands.");
        adapter.start();

    }
}
