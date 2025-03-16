package smartHomeControlSystem.devices.basic;

import smartHomeControlSystem.devices.SmartDevice;

public class SmartLock extends SmartDevice {
    private boolean smartLockLocked;
    private String smartLockPinCode;
    public SmartLock(String smartLockName, String pinCode) {
        super(smartLockName);
        this.smartLockLocked = true;
        this.smartLockPinCode = pinCode;
    }

    public boolean isSmartLockLocked() {
        return smartLockLocked;
    }

    public String getSmartLockPinCode() {
        return smartLockPinCode;
    }

    public void lock(String enteredPin) {
        if (enteredPin.equals(smartLockPinCode)) {
            if (!smartLockLocked) {
                smartLockLocked = true;
                System.out.println(getName() + " is now LOCKED.");
            } else {
                System.out.println(getName() + " is already locked.");
            }
        } else {
            System.out.println("Incorrect PIN. Access denied.");
        }
    }
    public void unlock(String enteredPin) {
        if (enteredPin.equals(smartLockPinCode)) {
            if (smartLockLocked) {
                smartLockLocked = false;
                System.out.println(getName() + " is now UNLOCKED.");
            } else {
                System.out.println(getName() + " is already unlocked.");
            }
        } else {
            System.out.println("Incorrect PIN. Access denied.");
        }
    }
    public void checkLockStatus() {
        System.out.println(getName() + " is currently " + (smartLockLocked ? "LOCKED" : "UNLOCKED") + ".");
    }

    @Override
    public void performFunction() {
        checkLockStatus();
    }
}
