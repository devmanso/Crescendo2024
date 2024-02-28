// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LimelightConstants;
import frc.robot.subsystems.LimeLightCamera;

public class TeleOpAutoShooter extends Command {

  private LimeLightCamera limelight;

  /** Creates a new TeleOpAutoShooter. */
  public TeleOpAutoShooter(LimeLightCamera limelight) {
    this.limelight = limelight;

    addRequirements(limelight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (LimeLightCamera.hasValidTargets() == 1) {
      System.out.println("AprilTag DETECTED");

      if(Math.floor(LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
       LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches)) <= 25) {
        System.out.println("IN RANGE!");
       } else {
        System.out.println("NOT IN RANGE");
       }
    } else {
      System.out.println("No AprilTags found");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}