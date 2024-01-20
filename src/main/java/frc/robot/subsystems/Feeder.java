// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase {

  private TalonFX feederMotor = new TalonFX(0);

  /** Creates a new Feeder. */
  public Feeder() {}

  public void feed(double speed) {
    feederMotor.set(speed);
  }

  public void reverseFeeder(double speed) {
    feederMotor.set(-speed);
  }

  public void feederStop() {
    feederMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
