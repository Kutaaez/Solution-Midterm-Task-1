package smartHomeControlSystem.composite;

import java.util.ArrayList;
import java.util.List;
//group all devices v ramkax Composite Design pattern
public class DeviceGroup implements  ISmartHomeComponent{
    private final String groupName;
    private final List<ISmartHomeComponent> components;

    public DeviceGroup(String groupName) {
        this.groupName = groupName;
        this.components =  new ArrayList<>();;
    }
    public void addComponent(ISmartHomeComponent component) {
        components.add(component);
    }

    public void removeComponent(ISmartHomeComponent component) {
        components.remove(component);
    }


    @Override
    public void turnOn() {
        System.out.println("Turning on all devices in group " + groupName + "...");
        for (ISmartHomeComponent component : components) {
            component.turnOn();
        }
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off all devices in group " + groupName + "...");
        for (ISmartHomeComponent component : components) {
            component.turnOff();
        }

    }

    @Override
    public String getName() {
        return groupName;

    }

    @Override
    public boolean isOn() {
        for (ISmartHomeComponent component : components) {
            if (!component.isOn()) {
                return false;
            }
        }
        return true;
    }

    public List<ISmartHomeComponent> getComponents() {
        return components;
    }
}
