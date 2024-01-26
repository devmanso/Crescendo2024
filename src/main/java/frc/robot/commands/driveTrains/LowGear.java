// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveTrains;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.WCPDriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LowGear extends InstantCommand {
  WCPDriveTrain driveTrain;

  public LowGear(WCPDriveTrain driveTrain) {
    this.driveTrain = driveTrain;

    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.lowGear();
  }
}
