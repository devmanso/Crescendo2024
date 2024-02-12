// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class StopAll extends InstantCommand {

  private Shooter shooter;
  private Feeder feeder;
  private Intake intake;
  
  public StopAll(Shooter shooter, Feeder feeder, Intake intake) {
    this.shooter = shooter;
    this.feeder = feeder;
    this.intake = intake;

    addRequirements(shooter, feeder, intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.stopShooter();
    feeder.stopFeeder();
    intake.stop();
  }

}
