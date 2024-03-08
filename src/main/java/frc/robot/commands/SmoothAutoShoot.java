// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.feeder.FeedWithTimer;
import frc.robot.commands.shooter.ShootWithFeeder;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

public class SmoothAutoShoot extends Command {

  Shooter shooter;
  Feeder feeder;

  double startTime;

  /** Creates a new SmoothAutoShoot. */
  public SmoothAutoShoot(Shooter shooter, Feeder feeder) {
    this.shooter = shooter;
    this.feeder = feeder;
    addRequirements(shooter, feeder);
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

    if(time -startTime <4) {
      shooter.shoot(1);
    } else if(time -startTime >3 && time - startTime < 4 ) {
      feeder.feed();
    }
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
