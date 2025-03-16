package smartHomeControlSystem;
//Legacy interface for work adapter .

public class Alexa {
    public void turnOnDevice(String deviceName) {
        System.out.println("Alexa: Turning on " + deviceName);
    }

    public void turnOffDevice(String deviceName) {
        System.out.println("Alexa: Turning off " + deviceName);
    }

    public void setBrightness(String deviceName, int brightness) {
        System.out.println("Alexa: Setting brightness of " + deviceName + " to " + brightness);
    }
}