package smartHomeControlSystem.factories;

import smartHomeControlSystem.devices.basic.SmartAirPurifier;
import smartHomeControlSystem.devices.advanced.AdvancedSmartAirPurifier;
import smartHomeControlSystem.devices.advanced.AdvancedSmartLight;
import smartHomeControlSystem.devices.advanced.AdvancedSmartLock;
import smartHomeControlSystem.devices.basic.SmartLight;
import smartHomeControlSystem.devices.basic.SmartLock;

public class AdvancedSmartHomeFactory implements SmartDeviceFactory {
    @Override
    public SmartLight createSmartLight(String advancedSmartLockname) {
        return new AdvancedSmartLight(advancedSmartLockname);
    }

    @Override
    public SmartAirPurifier createSmartAirPurifier(String advancedSmartLockname) {
        return new AdvancedSmartAirPurifier(advancedSmartLockname);
    }

    @Override
    public SmartLock createSmartLock(String advancedSmartLockname,  String advancedSmartLockPinCode) {
        return new AdvancedSmartLock(advancedSmartLockname,advancedSmartLockPinCode);
    }
}
