// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LimelightConstants;
import frc.robot.subsystems.LimeLightCamera;
import frc.robot.subsystems.SparkDriveTrain;

public class GetBackToSpeaker extends Command {

  private SparkDriveTrain driveTrain;

  /** Creates a new GetBackToSpeaker. */
  public GetBackToSpeaker(SparkDriveTrain driveTrain, LimeLightCamera camera) {
    this.driveTrain = driveTrain;

    addRequirements(driveTrain, camera);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    System.out.println(LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches));

    SmartDashboard.putNumber("Limelight Distance: ", LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches));

    while (LimeLightCamera.hasValidTargets() >= 1) {
      if (LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
      LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches) > LimelightConstants.StartingDistanceFromSpeaker) {
        if (LimeLightCamera.hasValidTargets() >= 1) {
          // Negative is backward, positive is forwards
          driveTrain.drive(-.3);
        }
      }
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stopDriveTrain();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
