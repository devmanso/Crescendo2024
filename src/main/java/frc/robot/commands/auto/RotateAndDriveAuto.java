// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.WCPDriveTrain;

public class RotateAndDriveAuto extends Command {
  WCPDriveTrain driveTrain;
  double startTime;

  /** Creates a new RotateDriveTrainAuto. */
  public RotateAndDriveAuto(WCPDriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
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
    if(time -startTime < 1) {
      driveTrain.rotate();
    } else if( time -startTime > 1.5 && time -startTime < 3.5) {
      driveTrain.driveForward();
    } else if (time -startTime > 4 && time -startTime < 6) {
      driveTrain.driveBackward();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - startTime > 6.5;
  }
}
