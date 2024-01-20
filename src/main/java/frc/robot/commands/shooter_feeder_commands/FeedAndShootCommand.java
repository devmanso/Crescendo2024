// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter_feeder_commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;


public class FeedAndShootCommand extends Command {

  private Shooter shooter;
  private Feeder feeder;
  private double shooterSpeed;
  private double feederSpeed;

  /** Creates a new ShootCommand. */
  public FeedAndShootCommand(Shooter shooter, Feeder feeder, double shooterSpeed, double feederSpeed) {

    this.shooter = shooter;
    this.feeder = feeder;
    this.shooterSpeed = shooterSpeed;
    this.feederSpeed = feederSpeed;

    addRequirements(shooter, feeder);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.shoot(shooterSpeed);
    feeder.feed(feederSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.shooterStop();
    feeder.feederStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
