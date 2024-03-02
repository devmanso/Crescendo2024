// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.TestingSubsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TestingMotorIds;

public class NewIntake extends SubsystemBase {

  private CANSparkMax intakeMotor = new CANSparkMax(TestingMotorIds.NEW_INTAKE_ID, MotorType.kBrushless);
  /** Creates a new NewIntake. */
  public NewIntake() {}

  public void runIntake(){
    intakeMotor.set(-0.95);
  }

  public void reverseIntake(){
    intakeMotor.set(0.95);
  }

  public void stopIntake(){
    intakeMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
