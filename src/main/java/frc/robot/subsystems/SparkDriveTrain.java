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
  private Spark leftMaster = new Spark(0);
  private Spark rightMaster = new Spark(1);
  private Spark leftFollower = new Spark(2);
  private Spark rightFollower = new Spark(3);

  private DifferentialDrive diffDrive = new DifferentialDrive(rightMaster, leftMaster);

  /** Creates a new SparkDriveTrain. */
  public SparkDriveTrain() {
    leftMaster.addFollower(leftFollower);
    rightMaster.addFollower(rightFollower);

    rightMaster.setInverted(true);
    rightFollower.setInverted(false);
  
  }

  public void arcadeDrive(double xSpd, double ySpd) {
    diffDrive.arcadeDrive(xSpd, ySpd);
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
