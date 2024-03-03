// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {

  private CANSparkMax intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_ID, MotorType.kBrushless);
  private DigitalInput noteSwitch = new DigitalInput(1);
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
  public void grabNote() {
    intakeMotor.set(-1); // 95 percent
  }

  public void autoGrabNote() {
    intakeMotor.set(-1); // -.95 then .8 before
  }

  /**
   * sets motors to -50 percent
   */
  public void spitNote() {
    intakeMotor.set(1);
  }

  public void stop() {
    intakeMotor.set(0);
  }

  /** Creates a new Intake. */
  public Intake() {}

  public void displayIntakeData() {
    SmartDashboard.putNumber("INTAKE AMPS", intakeMotor.getOutputCurrent());
    SmartDashboard.putNumber("INTAKE TEMP", (intakeMotor.getMotorTemperature() * 1.8) + 32);
  }

  @Override
  public void periodic() {
    displayIntakeData();
    boolean switchStatus = getNoteSwitch();
    SmartDashboard.putBoolean("Limit Switch", switchStatus);
    // This method will be called once per scheduler run
  }
}
