package smartHomeControlSystem.adapters;

import smartHomeControlSystem.composite.DeviceGroup;
import smartHomeControlSystem.composite.ISmartHomeComponent;
import smartHomeControlSystem.decorators.EnergySavingModeDecorator;
import smartHomeControlSystem.decorators.LoggingDecorator;
import smartHomeControlSystem.devices.basic.SmartLock;
import smartHomeControlSystem.facade.SmartHomeController;
import smartHomeControlSystem.devices.SmartDevice;
import smartHomeControlSystem.factories.AdvancedSmartHomeFactory;
import smartHomeControlSystem.factories.BasicSmartHomeFactory;
import smartHomeControlSystem.factories.ISmartDeviceFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TerminalDeviceControlAdapter {
    private final SmartHomeController controller;
    private final Scanner scanner;
    private final Map<String, SmartDevice> devices;
    private final Map<String, DeviceGroup> groups;
    private final Map<String, LoggingDecorator> loggingDecorators;

    public TerminalDeviceControlAdapter(SmartHomeController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
        this.devices = new HashMap<>();
        this.groups = new HashMap<>();
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
            SmartDevice device = controller.getDeviceByName(name);
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
                    device = new SmartSpeakerAdapter(controller, name);
                    break;
                default:
                    System.out.println("Unknown device type: " + type);
                    return;
            }

            devices.put(name, device);
            controller.addDevice(device);
            System.out.println("Device " + name + " created successfully.");
        } else if (parts[1].equalsIgnoreCase("group")) {
            String name = parts[2];
            if (groups.containsKey(name)) {
                System.out.println("Group with name " + name + " already exists.");
                return;
            }

            DeviceGroup group = new DeviceGroup(name);
            groups.put(name, group);
            controller.addGroup(group);
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

        SmartDevice device = devices.get(speakerName);
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

        SmartDevice device = devices.get(deviceName);
        DeviceGroup group = groups.get(groupName);

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
            SmartDevice device = devices.get(target);
            DeviceGroup group = groups.get(target);

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

        String type = parts[1];
        String name = parts[2];

        if (type.equalsIgnoreCase("device")) {
            SmartDevice device = devices.get(name);
            if (device != null) {
                System.out.println("Device: " + device.getName());
                System.out.println("Status: " + (device.isOn() ? "ON" : "OFF"));
                device.performFunction();
            } else {
                System.out.println("Device " + name + " not found.");
            }
        } else if (type.equalsIgnoreCase("group")) {
            DeviceGroup group = groups.get(name);
            if (group != null) {
                System.out.println("Group: " + group.getName());
                System.out.println("Devices in group:");
                for (ISmartHomeComponent component : group.getComponents()) {
                    System.out.println("- " + component.getName());
                }
            } else {
                System.out.println("Group " + name + " not found.");
            }
        } else {
            System.out.println("Invalid command. Usage: show device [device_name] or show group [group_name]");
        }
    }

    private void handleApplyDecoratorCommand(String[] parts) {
        if (parts.length < 6 || !parts[3].equalsIgnoreCase("to")) {
            System.out.println("Invalid command. Usage: apply decorator [type] to device [device_name]");
            return;
        }

        String decoratorType = parts[2];
        String deviceName = parts[5];

        SmartDevice device = devices.get(deviceName);
        if (device == null) {
            System.out.println("Device " + deviceName + " not found.");
            return;
        }

        switch (decoratorType.toLowerCase()) {
            case "energy-saving":
                EnergySavingModeDecorator energySavingDecorator = new EnergySavingModeDecorator(device);
                energySavingDecorator.enableEnergySavingMode();
                devices.put(deviceName, energySavingDecorator);
                System.out.println("Energy-saving decorator applied to " + deviceName + ".");
                break;
            case "logging":
                LoggingDecorator loggingDecorator = new LoggingDecorator(device);
                loggingDecorators.put(deviceName, loggingDecorator);
                devices.put(deviceName, loggingDecorator);
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