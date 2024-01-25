// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class IntakeFeederShooter extends Command {
  
  private Intake intake;
  private Feeder feeder;
  private Shooter shooter;
  private double startTime;
  private boolean end = false;
  private double intakeSpeed, feederSpeed, shooterSpeed;

  /** Creates a new IntakeFeederShooter. */
  public IntakeFeederShooter(Intake intake, Feeder feeder, Shooter shooter
                            , double intakeSpeed, double feederSpeed, double shooterSpeed) {
    // Use addRequirements() here to declare sub  system dependencies.
    this.intake = intake;
    this.feeder = feeder;
    this.shooter = shooter;
    this.intakeSpeed = intakeSpeed;
    this.feederSpeed = feederSpeed;
    this.shooterSpeed = shooterSpeed;
    addRequirements(intake, feeder, shooter);
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.grabNote(intakeSpeed); // 50%
    feeder.feed(feederSpeed); // 40%
    if (Timer.getFPGATimestamp() - startTime >= 2) {
      intake.stop();
      feeder.stopFeeder();
      shooter.shoot(shooterSpeed); // 100%
      end = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return end;
  }
}
