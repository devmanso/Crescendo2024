// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterAndFeeder;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  // private WPI_TalonFX shooterMotor = new WPI_TalonFX(); Falcon500 is no longer WPI_TalonFX

  private TalonFX shooterMotor = new TalonFX(ShooterAndFeeder.SHOOTER_ID);
  private TalonFX feederMotor = new TalonFX(ShooterAndFeeder.FEEDER_ID);
  

  public Shooter() {}

  /**
   * spins both motors at desired speed, however feeder motor only spins at 1/4 of that speed
   * @param speed - from -1 to 1 representing -100% to 100%
   */
  public void shoot(double speed) {
    shooterMotor.set(speed);
    feederMotor.set(speed * 0.25);
    // feederMotor.set(speed * reductionSpeed);
  }

  /*
   * reverses the feeder motor
   */
  public void feederReverse(double speed) {
    feederMotor.set(-speed);
  }

  /**
   * stops feeder and shooter motor
   */
  public void stopShooter() {
    shooterMotor.stopMotor();
    feederMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
