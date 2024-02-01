// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private CANSparkMax intakeMotor = new CANSparkMax(1, MotorType.kBrushless);
  private DigitalInput noteSwitch = new DigitalInput(0);
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
    intakeMotor.set(-0.75); // 50 percent
  }

  /**
   * sets motors to -50 percent
   */
  public void spitNote() {
    intakeMotor.set(0.45);
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
