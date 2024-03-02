// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.TestingSubsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TestingMotorIds;

// nafi was here

public class NewFeeder extends SubsystemBase {
  /** Creates a new NewFeeder. */

  // 62
  private CANSparkMax feeder = new CANSparkMax(TestingMotorIds.NEW_FEEDER_ID, MotorType.kBrushless);
  public NewFeeder() {}

  public void runFeeder(){
    feeder.set(0.95);
  }

  public void reverseFeeder(){
    feeder.set(-0.95);
  }

  public void stopFeeder(){
    feeder.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
