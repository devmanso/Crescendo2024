// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;


public class IntakeAndFeederThenShoot extends Command {
  /** Creates a new IntakeAndFeederThenShoot. */

  private Intake intake;
  private Feeder feeder;
  private Shooter shooter;

  private double intakeSpeed;
  private double feederSpeed;
  private double shooterSpeed;

  private boolean commandEnd = false;

  private int noteLimitSwitchPort = 0;// temp variable

  private final DigitalInput intakeLimitSwitch = new DigitalInput(noteLimitSwitchPort);

  public IntakeAndFeederThenShoot(Intake intake, Feeder feeder, Shooter shooter,
                                  double intakeSpeed, double feederSpeed, double shooterSpeed) {

                  
    this.intake = intake;
    this.feeder = feeder;
    this.shooter = shooter;
    
    this.intakeSpeed = intakeSpeed;
    this.feederSpeed = feederSpeed;
    this.shooterSpeed = shooterSpeed;

    addRequirements(intake, feeder, shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public void getLimitSwitchStatus(){
    intakeLimitSwitch.get();
  }

  private double startTime;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    while(Timer.getFPGATimestamp() <= 3) {
      if(Timer.getFPGATimestamp() - startTime <= 2) {
        shooter.shoot(shooterSpeed);
        feeder.stopFeeder();
        intake.stopIntake();
      } else {
        shooter.shoot(feederSpeed);// maybe at 1 --> (100%)
        feeder.feed(feederSpeed);// feeder is slower than intake
        intake.grabNote(intakeSpeed);// faster than feeder
      }
    }

    commandEnd = true;

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stopIntake();
    feeder.stopFeeder();
    shooter.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return commandEnd;
  }
}
