// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.LimelightConstants;
import frc.robot.commands.feeder.RunFeeder;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.LimeLightCamera;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.WCPDriveTrain;

import java.util.Timer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TeleopAutoShoot extends Command {

  // private Shooter shooter;
  // private Feeder feeder;
  private WCPDriveTrain driveTrain;
  private double startTime;
  

  /** Creates a new KelvinIsBalding. */
  public TeleopAutoShoot( WCPDriveTrain driveTrain) {
    this.driveTrain = driveTrain;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (LimeLightCamera.hasValidTargets() == 1) {
      System.out.println("APRILTAG DETECTED!!!!!!!!");
      if(Math.floor(LimeLightCamera.estimateDistance(
        LimelightConstants.MountAngleDegrees,
        LimelightConstants.LensHeightInches, 
        LimelightConstants.GoalHeightInches))
         <= 25) {
        System.out.println("IN RANGE!");
      }
      else if(Math.floor(LimeLightCamera.estimateDistance(
        LimelightConstants.MountAngleDegrees,
        LimelightConstants.LensHeightInches, 
        LimelightConstants.GoalHeightInches))
         >= 25) {
          System.out.println("NOT IN RANGE! MOVING!");
          driveTrain.driveForward();
         }
    } else {
      System.out.println("NO APRILTAG DETECTED!");
    }
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  @Override
  public boolean isFinished() {
    //return false;

    // untested code
    if(LimeLightCamera.hasValidTargets() == 1) {
      if(Math.floor(LimeLightCamera.estimateDistance(
        LimelightConstants.MountAngleDegrees,
        LimelightConstants.LensHeightInches, 
        LimelightConstants.GoalHeightInches))
         <= 25) {
          return true;
         }
         else {return false;}
         
    } else {return false;}

  }
}
