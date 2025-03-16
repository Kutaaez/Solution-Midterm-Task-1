package smartHomeControlSystem.decorators;

import smartHomeControlSystem.devices.SmartDevice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LoggingDecorator extends SmartDevice {
    private final SmartDevice decoratedDevice;
    private final Map<String, String> logs;

    public LoggingDecorator(SmartDevice device) {
        super(device.getName());
        this.decoratedDevice = device;
        this.logs = new HashMap<>();
    }

    @Override
    public void turnOn() {
        decoratedDevice.turnOn();
        log("turned on");
    }

    @Override
    public void turnOff() {
        decoratedDevice.turnOff();
        log("turned off");
    }

    @Override
    public void performFunction() {
        decoratedDevice.performFunction();
        log("performed its function");
    }

    private void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logEntry = "[" + timestamp + "] " + message;
        logs.put(timestamp, logEntry);
        System.out.println("Log [" + getName() + "]: " + logEntry);
    }

    public void printLogs() {
        if (logs.isEmpty()) {
            System.out.println("No logs found for " + getName() + ".");
            return;
        }
        System.out.println("Logs for " + getName() + ":");
        logs.values().forEach(System.out::println);
    }

    public void clearLogs() {
        logs.clear();
        System.out.println("Logs for " + getName() + " have been cleared.");
    }
}