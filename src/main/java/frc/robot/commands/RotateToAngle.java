// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.WCPDriveTrain;

public class RotateToAngle extends Command {

  private WCPDriveTrain driveTrain;
  private NavX navX;
  private double desiredAngle;

  /** Creates a new RotateToAngle. */
  public RotateToAngle(WCPDriveTrain driveTrain, NavX navX, double desiredAngle) {
    this.driveTrain = driveTrain;
    this.navX = navX;
    this.desiredAngle = desiredAngle;

    addRequirements(driveTrain, navX);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Math.floor(navX.getRoll()) < desiredAngle) {
      driveTrain.rotate();
    } else if (Math.floor(navX.getRoll()) > desiredAngle) {
      driveTrain.rotateOtherWay();
    } else if (Math.floor(navX.getRoll()) == desiredAngle) {
      isFinished();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
