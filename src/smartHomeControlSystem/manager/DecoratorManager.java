package smartHomeControlSystem.manager;

import smartHomeControlSystem.devices.SmartDevice;
import smartHomeControlSystem.decorators.EnergySavingModeDecorator;
import smartHomeControlSystem.decorators.LoggingDecorator;

import java.util.HashMap;
import java.util.Map;

public class DecoratorManager {
    private final Map<String, LoggingDecorator> loggingDecorators;

    public DecoratorManager() {
        this.loggingDecorators = new HashMap<>();
    }

    public SmartDevice applyDecorator(String type, SmartDevice device) {
        switch (type.toLowerCase()) {
            case "energy-saving" -> {
                return new EnergySavingModeDecorator(device);
            }
            case "logging" -> {
                LoggingDecorator loggingDecorator = new LoggingDecorator(device);
                loggingDecorators.put(device.getName(), loggingDecorator);
                return loggingDecorator;
            }
            default -> {
                System.out.println("Unknown decorator type: " + type);
                return device;
            }
        }
    }

    public void printLogs(String deviceName) {
        LoggingDecorator decorator = loggingDecorators.get(deviceName);
        if (decorator != null) {
            decorator.printLogs();
        } else {
            System.out.println("No logs found for " + deviceName);
        }
    }

    public void clearLogs(String deviceName) {
        LoggingDecorator decorator = loggingDecorators.get(deviceName);
        if (decorator != null) {
            decorator.clearLogs();
        } else {
            System.out.println("No logs found for " + deviceName);
        }
    }
}
