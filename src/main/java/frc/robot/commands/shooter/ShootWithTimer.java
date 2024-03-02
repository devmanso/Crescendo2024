// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ShootWithTimer extends Command {
  private Timer timer = new Timer();
  private Shooter shooter;
  private double currentTime;
  private boolean end = false;

  /** Creates a new ShooterWithTimer. */
  public ShootWithTimer(Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  double startTime;
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
    startTime = timer.getFPGATimestamp();
    end = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    // SmartDashboard.putNumber("Shooter time: ", currentTime);
    // double time = Timer.getFPGATimestamp();
    currentTime = timer.getFPGATimestamp() - startTime;
    if (currentTime <= 3) {
      shooter.shoot(-0.95);;// -0.95
    } else {
      timer.stop();
      timer.reset();
      end = true;
      currentTime = 0;
    }
    SmartDashboard.putNumber("Shooter time: ", currentTime);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    timer.reset();
    end = true;
    // SmartDashboard.putNumber("TShooter", Timer.getFPGATimestamp());

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return end;
  }
}
