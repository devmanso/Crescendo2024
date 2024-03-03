// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.testingCommands.combinedCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.testingCommands.feeder.NewFeederRun;
import frc.robot.commands.testingCommands.intake.NewIntakeRun;
import frc.robot.commands.testingCommands.shooter.NewShoot;
import frc.robot.subsystems.TestingSubsystems.NewFeeder;
import frc.robot.subsystems.TestingSubsystems.NewIntake;
import frc.robot.subsystems.TestingSubsystems.NewShooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class NewAutoShoot extends ParallelCommandGroup {
  private NewShooter shooter;
  private NewIntake intake;
  private NewFeeder feeder;

  public NewAutoShoot(NewShooter shooter, NewIntake intake, NewFeeder feeder) {
    this.feeder = feeder;
    this.shooter = shooter;
    this.intake = intake;
  }

  /** Creates a new NewAutoShoot. */
  public NewAutoShoot() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new NewShoot(shooter), new NewFeederRun(feeder), new NewIntakeRun(intake));
  }
}
