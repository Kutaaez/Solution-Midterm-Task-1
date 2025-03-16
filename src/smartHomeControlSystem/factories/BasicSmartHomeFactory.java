package smartHomeControlSystem.factories;

import smartHomeControlSystem.devices.basic.SmartAirPurifier;
import smartHomeControlSystem.devices.basic.SmartLight;
import smartHomeControlSystem.devices.basic.SmartLock;

public class BasicSmartHomeFactory implements ISmartDeviceFactory {
    @Override
    public SmartLight createSmartLight(String name) {
        return new SmartLight(name);
    }

    @Override
    public SmartAirPurifier createSmartAirPurifier(String name) {
        return new SmartAirPurifier(name);
    }

    @Override
    public SmartLock createSmartLock(String name, String pincode) {
        return new SmartLock(name,pincode);
    }
}
