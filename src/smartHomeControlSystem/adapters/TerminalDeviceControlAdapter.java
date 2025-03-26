package smartHomeControlSystem.adapters;

import smartHomeControlSystem.composite.DeviceGroup;
import smartHomeControlSystem.composite.ISmartHomeComponent;
import smartHomeControlSystem.decorators.EnergySavingModeDecorator;
import smartHomeControlSystem.decorators.LoggingDecorator;
import smartHomeControlSystem.devices.basic.SmartLock;
import smartHomeControlSystem.controller.SmartHomeController;
import smartHomeControlSystem.devices.SmartDevice;
import smartHomeControlSystem.factories.AdvancedSmartHomeFactory;
import smartHomeControlSystem.factories.BasicSmartHomeFactory;
import smartHomeControlSystem.factories.ISmartDeviceFactory;
import smartHomeControlSystem.manager.SmartDeviceManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TerminalDeviceControlAdapter {
    private final SmartHomeController controller;
    private final SmartDeviceManager deviceManager;

    private final Scanner scanner;

    private final Map<String, LoggingDecorator> loggingDecorators;

    public TerminalDeviceControlAdapter(SmartHomeController controller, SmartDeviceManager deviceManager) {
        this.controller = controller;
        this.deviceManager = deviceManager;
        this.scanner = new Scanner(System.in);

        this.loggingDecorators = new HashMap<>();
    }

    public void start() {
        System.out.println("Smart Home Control System");
        System.out.println("Type 'help' for a list of commands.");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            } else if (command.equalsIgnoreCase("help")) {
                printHelp();
            } else {
                processCommand(command);
            }
        }
    }

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("1. create device [type] [name] - Create a new device with a given type and name");
        System.out.println("2. create group [name] - Create a new group with a given name");
        System.out.println("3. add device [device_name] to group [group_name] - Add a device to a group");
        System.out.println("4. turn [on/off] [device_name] - Turn a device on or off");
        System.out.println("5. turn [on/off] [group_name] - Turn a group of devices on or off");
        System.out.println("6. show device [device_name] - Show information about a device");
        System.out.println("7. show group [group_name] - Show information about a group");
        System.out.println("8. apply decorator [type] to device [device_name] - Apply a decorator (energy-saving or logging) to a device");
        System.out.println("9. logs device [deviceName] - Show logs for a device");
        System.out.println("10. logs all - Show all logs");
        System.out.println("11. clear logs [device_name] - Clear logs for a device");
        System.out.println("12. exit - Exit the program");
        System.out.println("13. voice [speaker_name] [command] - Execute a voice command on a smart speaker");
    }

    private void processCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            System.out.println("Invalid command. Type 'help' for a list of commands.");
            return;
        }

        switch (parts[0].toLowerCase()) {
            case "create":
                handleCreateCommand(parts);
                break;
            case "add":
                handleAddCommand(parts);
                break;
            case "turn":
                handleTurnCommand(parts);
                break;
            case "show":
                handleShowCommand(parts);
                break;
            case "apply":
                handleApplyDecoratorCommand(parts);
                break;
            case "logs":
                handleLogsCommand(parts);
                break;
            case "clear":
                handleClearLogsCommand(parts);
                break;
            case "voice":
                handleVoiceCommand(parts);
                break;
            default:
                System.out.println("Unknown command. Type 'help' for a list of commands.");
        }
    }
    private void handleCreateCommand(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Invalid command. Usage: create device [type] [name] or create group [name]");
            return;
        }

        if (parts[1].equalsIgnoreCase("device")) {
            String type = parts[2];
            String name = parts[3];
            SmartDevice device = deviceManager.getDeviceByName(name);
            if (device != null) {
                System.out.println("Device with name " + name + " already exists.");
                return;
            }

            ISmartDeviceFactory factory = getFactory();
            switch (type.toLowerCase()) {
                case "light":
                    device = factory.createSmartLight(name);
                    break;
                case "lock":
                    System.out.print("Enter PIN code for the lock: ");
                    String pinCode = scanner.nextLine().trim();
                    device = factory.createSmartLock(name, pinCode);
                    break;
                case "airpurifier":
                    device = factory.createSmartAirPurifier(name);
                    break;
                case "speaker":
                    device = new SmartSpeakerAdapter(deviceManager, name);
                    break;
                default:
                    System.out.println("Unknown device type: " + type);
                    return;
            }

            deviceManager.addDevice(device);

            System.out.println("Device " + name + " created successfully.");
        } else if (parts[1].equalsIgnoreCase("group")) {
            String name = parts[2];
            if (deviceManager.getGroupByName(name) != null) {
                System.out.println("Group with name " + name + " already exists.");
                return;
            }

            DeviceGroup group = new DeviceGroup(name);
            deviceManager.addGroup(group);
            System.out.println("Group " + name + " created successfully.");
        } else {
            System.out.println("Invalid command. Usage: create device [type] [name] or create group [name]");
        }
    }
    private void handleVoiceCommand(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Invalid command. Usage: voice [speaker_name] [command]");
            return;
        }

        String speakerName = parts[1];
        String command = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));

        SmartDevice device = deviceManager.getDeviceByName(speakerName);
        if (device instanceof SmartSpeakerAdapter) {
            ((SmartSpeakerAdapter) device).executeVoiceCommand(command);
        } else {
            System.out.println("Device " + speakerName + " is not a smart speaker.");
        }
    }
    private ISmartDeviceFactory getFactory() {
        System.out.println("Choose factory type (basic/advanced):");
        String factoryType = scanner.nextLine().trim().toLowerCase();

        switch (factoryType) {
            case "basic":
                return new BasicSmartHomeFactory();
            case "advanced":
                return new AdvancedSmartHomeFactory();
            default:
                System.out.println("Invalid factory type. Using basic factory by default.");
                return new BasicSmartHomeFactory();
        }
    }
    private void handleAddCommand(String[] parts) {
        System.out.println("Received command: " + Arrays.toString(parts)); // отладочный вывод

        if (parts.length != 6 || !parts[0].equalsIgnoreCase("add") || !parts[1].equalsIgnoreCase("device")
                || !parts[4].equalsIgnoreCase("group")) {
            System.out.println("Invalid command. Usage: add device [device_name] to group [group_name]");
            return;
        }

        String deviceName = parts[2];
        String groupName = parts[5];

        SmartDevice device = deviceManager.getDeviceByName(deviceName);
        DeviceGroup group = deviceManager.getGroupByName(groupName);

        if (device == null) {
            System.out.println("Device " + deviceName + " not found.");
            return;
        }

        if (group == null) {
            System.out.println("Group " + groupName + " not found.");
            return;
        }

        group.addComponent(device);
        System.out.println("Device " + deviceName + " added to group " + groupName + ".");
    }

    private void handleTurnCommand(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Invalid command. Usage: turn [on/off] [device_name/group_name]");
            return;
        }

        String action = parts[1];
        String target = parts[2];

        if (action.equalsIgnoreCase("on") || action.equalsIgnoreCase("off")) {
            SmartDevice device = deviceManager.getDeviceByName(target);
            DeviceGroup group = deviceManager.getGroupByName(target);

            if (device != null) {
                if (action.equalsIgnoreCase("on")) {
                    if (device instanceof SmartLock) {
                        System.out.print("Enter PIN code to unlock " + target + ": ");
                        String enteredPin = scanner.nextLine().trim();
                        ((SmartLock) device).unlock(enteredPin);
                    } else {
                        device.turnOn();
                    }
                    System.out.println("Device " + target + " turned " + action + ".");
                } else {
                    if (device instanceof SmartLock) {
                        System.out.print("Enter PIN code to lock " + target + ": ");
                        String enteredPin = scanner.nextLine().trim();
                        ((SmartLock) device).lock(enteredPin);
                    } else {
                        device.turnOff();
                    }
                    System.out.println("Device " + target + " turned " + action + ".");
                }
            } else if (group != null) {
                if (action.equalsIgnoreCase("on")) {
                    group.turnOn();
                } else {
                    group.turnOff();
                }
                System.out.println("Group " + target + " turned " + action + ".");
            } else {
                System.out.println("Target " + target + " not found.");
            }
        } else {
            System.out.println("Invalid action. Usage: turn [on/off] [device_name/group_name]");
        }
    }
    private void handleShowCommand(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Invalid command. Usage: show device [device_name] or show group [group_name]");
            return;
        }

        String command = "show " + parts[1] + " " + parts[2];
        controller.executeCommand(command);
    }


    private void handleApplyDecoratorCommand(String[] parts) {
        if (parts.length < 6 || !parts[3].equalsIgnoreCase("to")) {
            System.out.println("Invalid command. Usage: apply decorator [type] to device [device_name]");
            return;
        }

        String decoratorType = parts[2];
        String deviceName = parts[5];

        SmartDevice device = deviceManager.getDeviceByName(deviceName);
        if (device == null) {
            System.out.println("Device " + deviceName + " not found.");
            return;
        }

        switch (decoratorType.toLowerCase()) {
            case "energy-saving":
                EnergySavingModeDecorator energySavingDecorator = new EnergySavingModeDecorator(device);
                energySavingDecorator.enableEnergySavingMode();
                deviceManager.removeDevice(deviceName);
                deviceManager.addDevice(energySavingDecorator);
                System.out.println("Energy-saving decorator applied to " + deviceName + ".");
                break;
            case "logging":
                LoggingDecorator loggingDecorator = new LoggingDecorator(device);
                loggingDecorators.put(deviceName, loggingDecorator);
                deviceManager.addDevice( loggingDecorator);
                System.out.println("Logging decorator applied to " + deviceName + ".");
                break;
            default:
                System.out.println("Unknown decorator type: " + decoratorType);
        }
    }

    private void handleLogsCommand(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Invalid command. Usage: logs device [deviceName] or logs all");
            return;
        }

        if (parts[1].equalsIgnoreCase("all")) {
            if (loggingDecorators.isEmpty()) {
                System.out.println("No logs available.");
                return;
            }
            for (Map.Entry<String, LoggingDecorator> entry : loggingDecorators.entrySet()) {
                System.out.println("Logs for " + entry.getKey() + ":");
                entry.getValue().printLogs();
            }
        } else if (parts[1].equalsIgnoreCase("device")) {
            if (parts.length < 3) {
                System.out.println("Invalid command. Usage: logs device [deviceName]");
                return;
            }
            String deviceName = parts[2];
            LoggingDecorator loggingDecorator = loggingDecorators.get(deviceName);
            if (loggingDecorator == null) {
                System.out.println("No logs found for device " + deviceName + ".");
                return;
            }
            loggingDecorator.printLogs();
        } else {
            System.out.println("Invalid command. Usage: logs device [deviceName] or logs all");
        }
    }
    private void handleClearLogsCommand(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Invalid command. Usage: clear logs [device_name]");
            return;
        }

        String deviceName = parts[2];
        LoggingDecorator loggingDecorator = loggingDecorators.get(deviceName);
        if (loggingDecorator == null) {
            System.out.println("No logs found for device " + deviceName + ".");
            return;
        }

        loggingDecorator.clearLogs();
    }
}