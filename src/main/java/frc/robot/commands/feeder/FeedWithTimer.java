// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.feeder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Feeder;

public class FeedWithTimer extends Command {
  private Feeder feeder;
  private double speed;
  private boolean end = false;
  private Timer timer = new Timer();
  /** Creates a new SetFeederSpeed. */

  public FeedWithTimer(Feeder feeder, double speed) {
    this.feeder = feeder;
    this.speed = speed;

    addRequirements(feeder);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  double startTime, currentTime;

  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    currentTime = Timer.getFPGATimestamp() - startTime;
    if (currentTime <= 3) {
      feeder.setFeederSpeed(speed);
    } else {
      end = true;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return end;
  }
}
