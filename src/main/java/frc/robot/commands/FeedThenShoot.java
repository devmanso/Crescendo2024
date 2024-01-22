// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

public class FeedThenShoot extends Command {
  Shooter shooter = new Shooter();
  Feeder feeder = new Feeder();

  /** Creates a new FeedThenShoot. */
  public FeedThenShoot(Shooter shooter, Feeder feeder) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    this.feeder = feeder;
    addRequirements(shooter, feeder);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // spin feeder motor at 100% speed for 1.5 seconds on a separate thread
    // to avoid blocking the main thread
    new Thread(() -> {
      feeder.feed();
      try {
        Thread.sleep(1500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      feeder.stopFeeder();
    }).start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // TODO: this is probably bad code, fix later
    // same thing here
    new Thread(() -> {
      shooter.shoot(1);
      try {
        Thread.sleep(1500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      shooter.stopShooter();
    }).start();
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
