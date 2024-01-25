// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake_feeder_shooter_commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

/**
 * PLEASE DO NOT USE THIS COMMAND.
 */
public class FeedThenShoot extends Command {
  Shooter shooter = new Shooter();
  Feeder feeder = new Feeder();
  double startTime, feederSpeed, shooterSpeed;

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
    startTime = Timer.getFPGATimestamp();
    // spin feeder motor at 100% speed for 1.5 seconds on a separate thread
    // to avoid blocking the main thread

    /* 
    new Thread(() -> {
      shooter.shoot(1);
      try {
        Thread.sleep(1500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      //shooter.shoot(1);
    }).start();
    */
    
    


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    shooter.shoot(shooterSpeed);
    if(Timer.getFPGATimestamp() - startTime >= 2) {
      feeder.feed(feederSpeed);
    }

    // if (Timer.getFPGATimestamp() - startTime >= 2) {
    //   feeder.stopFeeder();
    //   shooter.stopShooter();
    // } else {
    //   shooter.shoot(1);
    //   feeder.feed();
    // }

    // feeder.feed();
    // shooter.shoot(1);
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
