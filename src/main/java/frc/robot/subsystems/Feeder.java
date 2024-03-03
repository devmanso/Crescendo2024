// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterAndFeeder;

/**
 * The Feeder, also known as the bin, is a weight in which the note is held, the feeder's objective is to
 * hold the note in place and to feed it into the shooter when ready.
 */
public class Feeder extends SubsystemBase {
  /** Creates a new Feeder. */
  public Feeder() {
    feederMotor.setInverted(true);
  }

  private TalonFX feederMotor = new TalonFX(ShooterAndFeeder.FEEDER_ID);
  //private DigitalInput feederSwitch = new DigitalInput(ShooterAndFeeder.FeederSwitchPort);

  /**
   * sets feeder motor to -50% speed
   */
  public void feed() {
    feederMotor.set(-1.0);
  }

  public void grabIn() {
    feederMotor.set(-0.8);
  }

  public void grabInAuto() {
    feederMotor.set(-0.8); // -.3 before
  }

  public void feederSetSpeed(double speed) {
    feederMotor.set(speed);
  }

  // public boolean getFeederSwitch() {
  //   return feederSwitch.get();
  // }

  /**
   * stops feeder motor
   */
  public void stopFeeder() {
    feederMotor.stopMotor();
  }

  /* sets feeder motor to 35% */
  public void reverseFeeder() {
    feederMotor.set(0.45);
  }

  /**
   * use when you want to set feeder motor to specific speed
   * @param speed
   */
  public void setFeederSpeed(double speed) {
    feederMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
