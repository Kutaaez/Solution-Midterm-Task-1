package smartHomeControlSystem.devices.advanced;

import smartHomeControlSystem.devices.SmartAirPurifier;

public class AdvancedSmartAirPurifier extends SmartAirPurifier {
    private boolean ionizer;

    public AdvancedSmartAirPurifier(String advancedSmartAirPurifierName) {
        super(advancedSmartAirPurifierName);
        this.ionizer = false;
    }

    public void enableIonizer(boolean enable) {
        this.ionizer = enable;
        System.out.println(getName() + ": Ionizer " + (enable ? "enabled" : "disabled"));
    }

    @Override
    public void performFunction() {
        System.out.println(getName() + " is purifying air with " + (ionizer ? "ionizer on" : "standard mode"));
    }
}


