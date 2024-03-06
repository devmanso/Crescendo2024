// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto.distance;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LimelightConstants;
import frc.robot.subsystems.LimeLightCamera;
import frc.robot.subsystems.WCPDriveTrain;

public class DistanceBasedAutoMovement extends Command {

  private WCPDriveTrain driveTrain;

  /** Creates a new NewDistanceBasedAuton. */
  public DistanceBasedAutoMovement(WCPDriveTrain driveTrain, LimeLightCamera limelight) {
    this.driveTrain = driveTrain;

    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (LimeLightCamera.hasValidTargets() == 1) {
      if (LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees, LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches) < LimelightConstants.secondNoteDistance) {
        driveTrain.driveForward();
       }
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