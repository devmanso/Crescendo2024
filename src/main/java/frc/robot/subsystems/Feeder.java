// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterAndFeeder;

/**
 * The Feeder, also known as the bin, is a weight in which the note is held, the feeder's objective is to
 * hold the note in place and to feed it into the shooter when ready.
 */
public class Feeder extends SubsystemBase {
  /** Creates a new Feeder. */
  public Feeder() {}

  private TalonFX feederMotor = new TalonFX(ShooterAndFeeder.FEEDER_ID);

  /**
   * sets feeder motor to 100% speed
   */
  public void feed(double feederSpd) {
    feederMotor.set(feederSpd);
  }

  /**
   * stops feeder motor
   */
  public void stopFeeder() {
    feederMotor.stopMotor();
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
