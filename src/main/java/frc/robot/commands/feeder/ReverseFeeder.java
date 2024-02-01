// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.feeder;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Feeder;

public class ReverseFeeder extends Command {
  private Feeder feeder;

  /** Creates a new ReverseFeeder. */
  public ReverseFeeder(Feeder feeder) {
    this.feeder = feeder;
    
    addRequirements(feeder);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    feeder.reverseFeeder();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    feeder.stopFeeder();
  }

  @Override
  public boolean isFinished(){
    return false;
  }
}
