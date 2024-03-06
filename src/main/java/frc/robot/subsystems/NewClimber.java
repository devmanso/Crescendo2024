// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NewClimber extends SubsystemBase {

  private TalonFX climberMotor = new TalonFX(Constants.ClimberConstants.CLIMBER_MOTOR_ID);

  /** Creates a new NewClimber. */
  public NewClimber() {
    climberMotor.setNeutralMode(NeutralModeValue.Brake);
  }

  public void stop() {
    climberMotor.stopMotor();
  }

  public void climberUp() {
    climberMotor.set(.05);
  }

  public void climberDown() {
    climberMotor.set(-.05);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
