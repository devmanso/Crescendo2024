// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

  private TalonFX left = new TalonFX(Constants.ClimberConstants.CLIMBER_LEFT);
  private TalonFX right = new TalonFX(Constants.ClimberConstants.CLIMBER_RIGHT);

  /** Creates a new Climber. */
  public Climber() {
    left.setNeutralMode(NeutralModeValue.Brake);
    right.setNeutralMode(NeutralModeValue.Brake);
  }

  public void coupledUp() {
    left.set(.3);
    right.set(.3);
  }

  public void coupledDown() {
    left.set(-.3);
    right.set(-.3);
  }

  public void leftUp() {
    left.set(.3);
  }

  public void leftDown() {
    left.set(-.3);
  }

  public void rightUp() {
    right.set(.3);
  }

  public void rightDown() {
    right.set(-.3);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
