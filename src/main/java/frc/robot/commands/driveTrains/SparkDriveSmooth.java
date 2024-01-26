// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveTrains;

import com.hyperdrive.hyperlib.DriveFilter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SparkDriveTrain;

public class SparkDriveSmooth extends Command {
  /** Creates a new SparkDrive. */
  private SparkDriveTrain driveTrain;
  private CommandXboxController xbox;
  DriveFilter speed, turn;


  public SparkDriveSmooth(SparkDriveTrain driveTrain, CommandXboxController xbox) {
    this.driveTrain = driveTrain;
    this.xbox = xbox;

    speed = new DriveFilter();
    turn = new DriveFilter();

    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.arcadeDrive(
      speed.get(xbox.getRawAxis(1), .9321),
      turn.get(xbox.getRawAxis(4), 0.92312));
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
