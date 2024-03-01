// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NewShooter extends SubsystemBase {

  private TalonFX topShooterMotor = new TalonFX(6);
  private TalonFX bottomShooterMotor = new TalonFX(7);

  private CANSparkMax feederMotor = new CANSparkMax(1, MotorType.kBrushless); 



  /** Creates a new Shooter. */
  public NewShooter() {

  }

  public void shoot(){
    topShooterMotor.set(0.95);
    bottomShooterMotor.set(0.95);
    feederMotor.set(0.95);
  }

  public void stopMotors(){
    topShooterMotor.stopMotor();
    bottomShooterMotor.stopMotor();
    feederMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
