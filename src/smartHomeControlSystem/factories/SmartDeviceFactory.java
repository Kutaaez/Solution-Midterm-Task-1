package smartHomeControlSystem.factories;

import smartHomeControlSystem.devices.basic.SmartAirPurifier;
import smartHomeControlSystem.devices.basic.SmartLight;
import smartHomeControlSystem.devices.basic.SmartLock;

public interface SmartDeviceFactory {
    SmartLight createSmartLight(String name);
    SmartAirPurifier createSmartAirPurifier(String name);
    SmartLock createSmartLock(String name, String pincode);
}