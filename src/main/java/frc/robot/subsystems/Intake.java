// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private TalonFX bottomMotor = new TalonFX(10);
  private TalonFX topMotor = new TalonFX(11);
  private DigitalInput noteChecker = new DigitalInput(0);
  
  /**
   * Returns true if note stuck in intake
   */
  public boolean stuckNote() {
    return noteChecker.get();
  }

  /**
   * sets motors to 50 percent
   */
  public void grabNote() {
    bottomMotor.set(.5); // 50 percent
    topMotor.set(.5);
  }

  /**
   * sets motors to -50 percent
   */
  public void spitNote() {
    bottomMotor.set(-.5);
    topMotor.set(-.5);
  }

  public void stop() {
    bottomMotor.set(0);
    topMotor.set(0);
  }

  /** Creates a new Intake. */
  public Intake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
