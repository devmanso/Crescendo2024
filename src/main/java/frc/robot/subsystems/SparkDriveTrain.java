// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SparkDriveTrain extends SubsystemBase {
  private Spark leftMaster = new Spark(2);
  private Spark rightMaster = new Spark(0);
  private Spark leftFollower = new Spark(5); // DO NOT USE 3 AS IT GOT BURNT LMAO
  private Spark rightFollower = new Spark(1);

  private MotorControllerGroup leftSide = new MotorControllerGroup(leftMaster, leftFollower);
  private MotorControllerGroup rightSide = new MotorControllerGroup(rightMaster, rightFollower);
  private DifferentialDrive diffDrive = new DifferentialDrive(rightSide, leftSide);

  /** Creates a new SparkDriveTrain. */
  public SparkDriveTrain() {
    leftMaster.addFollower(leftFollower);
    rightMaster.addFollower(rightFollower);

    rightMaster.setInverted(false); // might actually be set to true
    rightFollower.setInverted(false);
  
  }

  // public double driveFilter(double startSpeed){

  // }

  public void arcadeDrive(double xSpd, double ySpd) {
    diffDrive.arcadeDrive(xSpd, ySpd);
  }

  public void driveForward(double xSpd) {
    leftSide.set(xSpd);
    rightSide.set(-xSpd -.05);
  }

  public void driveBackward(double xSpd) {
    leftSide.set(xSpd +0.05);
    rightSide.set(xSpd);
  }

  public void stopDriveTrain() {
    leftMaster.stopMotor();
    rightMaster.stopMotor();
    leftFollower.stopMotor();
    rightFollower.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
