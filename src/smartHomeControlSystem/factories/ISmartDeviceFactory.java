package smartHomeControlSystem.factories;

import smartHomeControlSystem.devices.basic.SmartAirPurifier;
import smartHomeControlSystem.devices.basic.SmartLight;
import smartHomeControlSystem.devices.basic.SmartLock;
//interface of fabric for creating devices. This fabric realizing easy to create devices.
public interface ISmartDeviceFactory {
    SmartLight createSmartLight(String name);
    SmartAirPurifier createSmartAirPurifier(String name);
    SmartLock createSmartLock(String name, String pincode);
}