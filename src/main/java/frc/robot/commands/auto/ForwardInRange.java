// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LimelightConstants;
import frc.robot.subsystems.LimeLightCamera;
import frc.robot.subsystems.SparkDriveTrain;
import frc.robot.subsystems.WCPDriveTrain;

public class ForwardInRange extends Command {

  private WCPDriveTrain driveTrain;

  /** Creates a new GetInRangeSpark. */
  public ForwardInRange(WCPDriveTrain driveTrain, LimeLightCamera camera) {
    this.driveTrain = driveTrain;
    addRequirements(driveTrain, camera);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //driveTrain.stopDriveTrain();
    driveTrain.setBrakeMode();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("GETTING IN RANGE");
    System.out.println(LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches));

    if(LimeLightCamera.hasValidTargets() >= 1) {
      System.out.println(" BACK UP DISTANCE " + LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches));

      if(LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches) > 65) {

        driveTrain.driveBackward(); //.25

        System.out.println("MOVING");
      } else {System.out.println("NOT MOVING");}

    }
    System.out.println("IN RANGE; STILL RUNNING. NO VALID TARGET");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
    driveTrain.setCoastMode();
    //System.out.println("IN RANGE; END");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    if(LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches) <= 65) {
        System.out.println("FINISHED : TRUE");
        return true;
    } else {
      System.out.println("FINISHED : FALSE");
      return false;}
  }
}
