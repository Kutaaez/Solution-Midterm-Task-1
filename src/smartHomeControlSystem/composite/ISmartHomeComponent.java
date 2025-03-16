package smartHomeControlSystem.composite;
//create interface for realize component design pattern
public interface ISmartHomeComponent {
    void turnOn();
    void turnOff();
    String getName();
    boolean isOn();
}
