package smartHomeControlSystem.devices.advanced;

import smartHomeControlSystem.devices.basic.SmartLock;

public class AdvancedSmartLock extends SmartLock {
    private boolean fingerprintScanner;

    public AdvancedSmartLock(String advancedSmartLockName, String advancedSmartLockPinCode ) {
        super(advancedSmartLockName, advancedSmartLockPinCode);
        this.fingerprintScanner = false;
    }

    public void enableFingerprintScanner(boolean enable) {
        this.fingerprintScanner = enable;
        System.out.println(getName() + ": Fingerprint scanner " + (enable ? "enabled" : "disabled"));
    }

    @Override
    public void performFunction() {
        System.out.println(getName() + " is securing with " + (fingerprintScanner ? "fingerprint access" : "standard access"));
    }
}