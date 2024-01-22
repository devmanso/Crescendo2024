// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WestCoastDriveTrain;

public class WCPDriveTrain extends SubsystemBase {

  Spark leftMaster = new Spark(WestCoastDriveTrain.MASTER_LEFT);
  Spark leftFollower = new Spark(WestCoastDriveTrain.FOLLOWER_LEFT);
  Spark rightMaster = new Spark(WestCoastDriveTrain.MASTER_RIGHT);
  Spark rightFollower = new Spark(WestCoastDriveTrain.FOLLOWER_RIGHT);

  DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);
  // over here we'd tupically use MotorControllerGroup, but it's been deprecated
  // please look at the constructor of this class to see how to set follower motors

  /** Creates a new WCPDriveTrain. */
  public WCPDriveTrain() {
    leftMaster.addFollower(leftFollower);
    rightMaster.addFollower(rightFollower);
    
    // set the right motors to be inverted
    rightMaster.setInverted(true);
    rightFollower.setInverted(true);

    // ensure left motors are not inverted
    leftMaster.setInverted(false);
    leftFollower.setInverted(false);
  }

  /**
   * Sets the left motors to a certain speed.
   * @param speed - from -1 to 1
   */
  public void setLeftMotors(double speed) {
    leftMaster.set(speed);
    leftFollower.set(speed);
  }

  /**
   * Sets the right motors to a certain speed.
   * @param speed - from -1 to 1
   */
  public void setRightMotors(double speed) {
    rightMaster.set(speed);
    rightFollower.set(speed);
  }

  public void stop() {
    // this is a bit unnecessary, but it doesn't hurt to quadruple check lmao
    setLeftMotors(0);
    setRightMotors(0);
    rightMaster.stopMotor();
    rightFollower.stopMotor();
    leftMaster.stopMotor();
    leftFollower.stopMotor();
  }

  /**
   * Drives the robot using arcade drive.
   * @param speed - speed on x axis
   * @param rotation - rotation on y axis
   */
  public void drive(double speed, double rotation) {
    drive.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
