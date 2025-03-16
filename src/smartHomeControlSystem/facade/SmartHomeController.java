package smartHomeControlSystem.facade;

import smartHomeControlSystem.composite.DeviceGroup;
import smartHomeControlSystem.devices.SmartDevice;
import java.util.ArrayList;
import java.util.List;
//here we use Facade design patter because he single interface
// for managing various devices in a smart home system.
public class SmartHomeController {
    private final List<SmartDevice> devices;
    private final List<DeviceGroup> groups;


    public SmartHomeController() {
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
        System.out.println("Turning on all devices...");
        for (SmartDevice device : devices) {
            device.turnOn();
        }
    }

    public void turnAllOff() {
        System.out.println("Turning off all devices...");
        for (SmartDevice device : devices) {
            device.turnOff();
        }
    }

    public void showStatus() {
        System.out.println("Smart Home System Status:");
        for (SmartDevice device : devices) {
            System.out.println("- " + device.getName() + " is " + (device.isOn() ? "ON" : "OFF"));
        }
    }
    public void turnOffDevice(String deviceName) {
        for (SmartDevice device : devices) {
            if (device.getName().equalsIgnoreCase(deviceName)) {
                device.turnOff();
                System.out.println(deviceName + " has been turned off.");
                return;
            }
        }
        System.out.println("Device not found: " + deviceName);
    }
    public void turnOnDevice(String deviceName) {
        for (SmartDevice device : devices) {
            if (device.getName().equalsIgnoreCase(deviceName)) {
                device.turnOn();
                System.out.println(deviceName + " has been turned on.");
                return;
            }
        }
        System.out.println("Device not found: " + deviceName);
    }
    public List<SmartDevice> getDevices() {
        return devices;
    }
    public List<DeviceGroup> getGroups() {return groups;}

    public SmartDevice getDeviceByName(String deviceName) {
        for (SmartDevice device : devices) {
            if (device.getName().equalsIgnoreCase(deviceName)) {
                return device;
            }
        }
        return null;
    }

    public DeviceGroup getGroupByName(String groupName) {
        for (DeviceGroup group : groups) {
            if (group.getName().equalsIgnoreCase(groupName)) {
                return group;
            }
        }
        return null;
    }
}
