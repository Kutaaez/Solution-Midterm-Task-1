package smartHomeControlSystem.manager;

import smartHomeControlSystem.composite.DeviceGroup;
import smartHomeControlSystem.composite.ISmartHomeComponent;
import smartHomeControlSystem.devices.SmartDevice;
import java.util.ArrayList;
import java.util.List;

public class SmartDeviceManager {
    private final List<SmartDevice> devices;
    private final List<DeviceGroup> groups;

    public SmartDeviceManager() {
        this.devices = new ArrayList<>();
        this.groups = new ArrayList<>();
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
        System.out.println(device.getName() + " has been added to the system.");
    }

    public void addGroup(DeviceGroup group) {
        groups.add(group);
        System.out.println("Group " + group.getName() + " has been added to the system.");
    }

    public void turnAllOn() {
        devices.forEach(SmartDevice::turnOn);
    }

    public void turnAllOff() {
        devices.forEach(SmartDevice::turnOff);
    }

    public void turnOnDevice(String deviceName) {
        SmartDevice device = getDeviceByName(deviceName);
        if (device != null) {
            device.turnOn();
        } else {
            System.out.println("Device not found: " + deviceName);
        }
    }

    public void turnOffDevice(String deviceName) {
        SmartDevice device = getDeviceByName(deviceName);
        if (device != null) {
            device.turnOff();
        } else {
            System.out.println("Device not found: " + deviceName);
        }
    }

    public SmartDevice getDeviceByName(String deviceName) {
        return devices.stream()
                .filter(device -> device.getName().equalsIgnoreCase(deviceName))
                .findFirst()
                .orElse(null);
    }

    public DeviceGroup getGroupByName(String groupName) {
        return groups.stream()
                .filter(group -> group.getName().equalsIgnoreCase(groupName))
                .findFirst()
                .orElse(null);
    }
    public void removeDevice(String deviceName) {
        SmartDevice device = getDeviceByName(deviceName);
        if (device != null) {
            devices.remove(device);
            System.out.println("Device " + deviceName + " has been removed from the system.");
        } else {
            System.out.println("Device " + deviceName + " not found.");
        }
    }
    public void showStatus() {
        if (devices.isEmpty()) {
            System.out.println("No devices found in the system.");
            return;
        }

        System.out.println("Smart Home System Status:");
        for (SmartDevice device : devices) {
            System.out.println("- " + device.getName() + " is " + (device.isOn() ? "ON" : "OFF"));
        }
    }
    public void showDeviceStatus(String deviceName) {
        SmartDevice device = getDeviceByName(deviceName);
        if (device != null) {
            System.out.println("Device: " + device.getName());
            System.out.println("Status: " + (device.isOn() ? "ON" : "OFF"));
            device.performFunction();
        } else {
            System.out.println("Device " + deviceName + " not found.");
        }
    }
    public void showGroupStatus(String groupName) {
        DeviceGroup group = getGroupByName(groupName);
        if (group != null) {
            System.out.println("Group: " + group.getName());
            System.out.println("Devices in group:");
            for (ISmartHomeComponent component : group.getComponents()) {
                if (component instanceof SmartDevice) {
                    showDeviceStatus(component.getName());
                }
            }
        } else {
            System.out.println("Group " + groupName + " not found.");
        }
    }

}
