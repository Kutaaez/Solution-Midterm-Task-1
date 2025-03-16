package smartHomeControlSystem.decorators;

import smartHomeControlSystem.devices.SmartDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Decorator for logging actions of devices in local map storage
public class LoggingDecorator extends SmartDevice {
    private final SmartDevice decoratedDevice;
    private static final Map<String, List<String>> logs = new HashMap<>();

    public LoggingDecorator(SmartDevice device) {
        super(device.getName());
        this.decoratedDevice = device;
    }

    @Override
    public void turnOn() {
        decoratedDevice.turnOn();
        log(decoratedDevice.getName(), "turned on.");
    }

    @Override
    public void turnOff() {
        decoratedDevice.turnOff();
        log(decoratedDevice.getName(), "turned off.");
    }

    @Override
    public void performFunction() {
        decoratedDevice.performFunction();
        log(decoratedDevice.getName(), "performed its function.");
    }

    protected static void log(String deviceName, String message) {
        logs.putIfAbsent(deviceName, new ArrayList<>());
        logs.get(deviceName).add(message);
        System.out.println("Log [" + deviceName + "]: " + message);
    }

    public static void printLogs(String deviceName) {
        if (logs.containsKey(deviceName)) {
            System.out.println("Logs for " + deviceName + ":");
            for (String log : logs.get(deviceName)) {
                System.out.println(log);
            }
        } else {
            System.out.println("No logs found for " + deviceName + ".");
        }
    }

    public static void printAllLogs() {
        if (logs.isEmpty()) {
            System.out.println("No logs recorded.");
            return;
        }
        System.out.println("All Device Logs:");
        for (Map.Entry<String, List<String>> entry : logs.entrySet()) {
            System.out.println("Logs for " + entry.getKey() + ":");
            for (String log : entry.getValue()) {
                System.out.println(log);
            }
        }
    }
}
