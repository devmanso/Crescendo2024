// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LimelightConstants;
import frc.robot.subsystems.LimeLightCamera;
import frc.robot.subsystems.SparkDriveTrain;

public class GetInRangeSpark extends Command {

  private SparkDriveTrain driveTrain;

  /** Creates a new GetInRangeSpark. */
  public GetInRangeSpark(SparkDriveTrain driveTrain, LimeLightCamera camera) {
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
    System.out.println("GETTING IN RANGE");
    System.out.println(LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches));

    if(LimeLightCamera.hasValidTargets() == 1) {
      System.out.println("VALID TARGET");
      // go back until we're in range
      while(LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches) < 65) {

        if(LimeLightCamera.hasValidTargets() == 1) {
          driveTrain.drive(.5);
        }
        
        System.out.println("MOVING");
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
    return true;
  }
}
