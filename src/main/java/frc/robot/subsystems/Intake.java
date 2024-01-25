// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private TalonFX intakeMotor = new TalonFX(IntakeConstants.INTAKE_ID);
  private DigitalInput noteSwitch = new DigitalInput(IntakeConstants.LIMITSWITCH_ID);

  /**
   * returns the state of the limit switch
   * @return
   */
  public boolean getNoteSwitch() {
    return noteSwitch.get();
  }

  /**
   * sets motors to 50 percent
   */
  public void grabNote(double speed) {
    intakeMotor.set(speed); // 50 percent
  }

  /**
   * sets motors to -50 percent
   */
  public void spitNote(double speed) {
    intakeMotor.set(-speed);
  }

  public void stop() {
    intakeMotor.set(0);
  }

  /** Creates a new Intake. */
  public Intake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
