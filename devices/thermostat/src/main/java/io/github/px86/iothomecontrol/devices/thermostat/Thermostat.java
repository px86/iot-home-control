package io.github.px86.iothomecontrol.devices.thermostat;

import org.springframework.stereotype.Component;

@Component
public class Thermostat {
  private String deviceId;
  private String deviceName;
  private double currentTemperature;
  private double targetTemperature;

  public Thermostat() {}

  public Thermostat(
      String deviceId, String deviceName, double currentTemperature, double targetTemperature) {
    this.deviceId = deviceId;
    this.deviceName = deviceName;
    this.currentTemperature = currentTemperature;
    this.targetTemperature = targetTemperature;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public double getCurrentTemperature() {
    return currentTemperature;
  }

  public void setCurrentTemperature(double currentTemperature) {
    this.currentTemperature = currentTemperature;
  }

  public double getTargetTemperature() {
    return targetTemperature;
  }

  public void setTargetTemperature(double targetTemperature) {
    this.targetTemperature = targetTemperature;
  }

  @Override
  public String toString() {
    return "Thermostat [deviceId="
        + deviceId
        + ", deviceName="
        + deviceName
        + ", currentTemperature="
        + currentTemperature
        + ", targetTemperature="
        + targetTemperature
        + "]";
  }
}
