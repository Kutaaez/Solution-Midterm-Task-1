package smartHomeControlSystem;

import smartHomeControlSystem.adapters.TerminalDeviceControlAdapter;
import smartHomeControlSystem.facade.SmartHomeController;

public class Main {
    public static void main(String[] args) {
        SmartHomeController controller = new SmartHomeController();
        TerminalDeviceControlAdapter adapter = new TerminalDeviceControlAdapter(controller);
        System.out.println("Welcome to the Smart Home Control System!");
        System.out.println("Type 'help' to see a list of available commands.");
        adapter.start();

    }
}
