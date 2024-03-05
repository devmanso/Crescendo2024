// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.hal.REVPHJNI;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AirCompressor extends SubsystemBase {

  private Compressor compressor = new Compressor(Constants.COMPRESSOR_ID, PneumaticsModuleType.REVPH);
  
  public void enableCompressor() {
    compressor.enableDigital();
    compressor.enableHybrid(70, 120);
  }

  public void disableCompressor() {
    compressor.disable();
  }

  public void displayCompressorData() {
    boolean enabled = compressor.isEnabled();
    boolean pressureSwitch = compressor.getPressureSwitchValue();
    double pressure = compressor.getPressure();
    double current = compressor.getCurrent();
    
    SmartDashboard.putBoolean("Compresser enabled: ", enabled);
    SmartDashboard.putNumber("Compressor Current", current);
    SmartDashboard.putNumber("Pressure: ", pressure);
    SmartDashboard.putBoolean("Pressure Switch: ", pressureSwitch);
  }

  /** Creates a new AirCompressor. */
  public AirCompressor() {}

  @Override
  public void periodic() {
    displayCompressorData();
  }
}
