// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.pneumatics;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AirCompressor;

public class RunCompressor extends Command {
  
  AirCompressor compressor;
  
  /** Creates a new RunCompressor. */
  public RunCompressor(AirCompressor compressor) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.compressor = compressor;
    addRequirements(compressor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    compressor.enableCompressor();
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
