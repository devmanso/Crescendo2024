// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LimelightConstants;
import frc.robot.subsystems.LimeLightCamera;
import frc.robot.subsystems.SparkDriveTrain;

public class BackUpInRangeSpark extends Command {

  private SparkDriveTrain driveTrain;

  /** Creates a new GetInRangeSpark. */
  public BackUpInRangeSpark(SparkDriveTrain driveTrain, LimeLightCamera camera) {
    this.driveTrain = driveTrain;
    addRequirements(driveTrain, camera);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //driveTrain.stopDriveTrain();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("GETTING IN RANGE");
    System.out.println(LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches));

    while(LimeLightCamera.hasValidTargets() >= 1) {
      System.out.println(LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches));
      //.out.println("VALID TARGET");
      // go back until we're in range
      // original distance to travel was 65
      if(LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches) < 40) {

        // Redundant check lmao, but code works so idc
        if(LimeLightCamera.hasValidTargets() >= 1) {
          driveTrain.drive(.30); //.25
        }

        //System.out.println("MOVING");
      }

    }
    //System.out.println("IN RANGE; STILL RUNNING");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stopDriveTrain();
    //System.out.println("IN RANGE; END");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
