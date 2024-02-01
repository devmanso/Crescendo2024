// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class ReverseIntake extends Command {
  /** Creates a new ReverseIntake. */
  private Intake intake;
  
  public ReverseIntake(Intake intake) {
    this.intake = intake;

    addRequirements(intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  @Override
  public void initialize() {}

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    intake.spitNote();
  }

  @Override
  public void end(boolean interrupted) {
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
