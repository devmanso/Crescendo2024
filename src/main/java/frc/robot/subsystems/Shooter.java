// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterAndFeeder;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  // private WPI_TalonFX shooterMotor = new WPI_TalonFX(); Falcon500 is no longer WPI_TalonFX

  private TalonFX bottomShooter = new TalonFX(ShooterAndFeeder.BOTTOM_SHOOTER_ID);
  private TalonFX topShooter = new TalonFX(ShooterAndFeeder.TOP_SHOOTER_ID);

  public Shooter() {
    bottomShooter.setInverted(true);
    topShooter.setInverted(true);
    TalonFXConfiguration shooterConfig = new TalonFXConfiguration();
    shooterConfig.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = 1;
    bottomShooter.getConfigurator().apply(shooterConfig);
    topShooter.getConfigurator().apply(shooterConfig);
  }

  /**
   * spins both motors at desired speed, however feeder motor only spins at 1/4 of that speed
   * @param speed - from -1 to 1 representing -100% to 100%
   */
  public void shoot(double speed) {
    bottomShooter.set(speed);
    topShooter.set(speed);
  }

  /**
   * stops feeder and shooter motor
   */
  public void stopShooter() {
    bottomShooter.stopMotor();
    topShooter.stopMotor();;
  }

  public void displayShooterData() {
    SmartDashboard.putNumber("SHOOTER ACCEL", bottomShooter.getAcceleration().getValueAsDouble());
    SmartDashboard.putNumber("SHOOTER VEL", bottomShooter.getVelocity().getValueAsDouble());
  }

  @Override
  public void periodic() {
    displayShooterData();
    // This method will be called once per scheduler run
  }
}
