// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LimeLightCamera;
import frc.robot.subsystems.WCPDriveTrain;

public class LimelightGoForward extends Command {
  private WCPDriveTrain driveTrain;
  private LimeLightCamera limelight;

  /** Creates a new LimelightGoForward. */
  // put limelight in constructor in order to more easily
  // identify that this command works with the limelight
  public LimelightGoForward(WCPDriveTrain driveTrain, LimeLightCamera limelight) {
    this.driveTrain = driveTrain;
    this.limelight = limelight;

    addRequirements(driveTrain, limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
