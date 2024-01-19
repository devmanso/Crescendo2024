// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorIds;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  // private WPI_TalonFX shooterMotor = new WPI_TalonFX(); Falcon500 is no longer WPI_TalonFX

  private TalonFX shooterMotor = new TalonFX(MotorIds.SHOOTER_MOTOR_ID);
  private TalonFX feederMotor = new TalonFX(MotorIds.FEEDER_MOTOR_ID);
  

  public Shooter() {}

  public void shoot(double speed, double reductionSpeed) {
    shooterMotor.set(speed);
    feederMotor.set(speed * 0.25);
    // feederMotor.set(speed * reductionSpeed);
  }

  public void feederReverse(double speed) {
    feederMotor.set(-speed);
  }

  
  public void stopShooter() {
    shooterMotor.stopMotor();
    feederMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
