// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.feeder.FeedWithTimer;
import frc.robot.commands.feeder.StopFeeder;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootWithFeeder extends SequentialCommandGroup {
  /** Creates a new ShootWithFeeder. */
  private Shooter shooter;
  private Feeder feeder;

  public ShootWithFeeder(Shooter shooter, Feeder feeder) {
    this.shooter = shooter;
    this.feeder = feeder;

    addRequirements(shooter,feeder);
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ShootWithTimer(shooter), new FeedWithTimer(feeder, -.5), new StopFeeder(feeder), new StopShooter(shooter));
  }
}
