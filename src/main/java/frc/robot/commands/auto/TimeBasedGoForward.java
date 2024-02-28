// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SparkDriveTrain;

public class TimeBasedGoForward extends Command {
  /** Creates a new TimeBasedBackUp. */

  private SparkDriveTrain driveTrain;

  private double startTime;

  public TimeBasedGoForward(SparkDriveTrain driveTrain) {
    this.driveTrain = driveTrain;

    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double time = Timer.getFPGATimestamp();
    System.out.println(time - startTime);

    if (time - startTime < 1.5) {
      driveTrain.driveBackward(-.4); // -.4
    } else {
      driveTrain.stopDriveTrain();
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
