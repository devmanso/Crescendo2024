// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.LimelightConstants;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.feeder.RunFeeder;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.LimeLightCamera;
import frc.robot.subsystems.Shooter;
import java.util.Timer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TeleopAutoShoot extends ParallelCommandGroup {

  private Shooter shooter;
  private Feeder feeder;
  private double startTime;
  private LimeLightCamera limelight;

  /** Creates a new KelvinIsBalding. */
  public TeleopAutoShoot(Shooter shooter, Feeder feeder, LimeLightCamera limelight) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    if (LimeLightCamera.hasValidTargets() == 1) {
      System.out.println("APRILTAG DETECTED!!!!!!!!");
      if(Math.floor(LimeLightCamera.estimateDistance(LimelightConstants.MountAngleDegrees,
        LimelightConstants.LensHeightInches, LimelightConstants.GoalHeightInches)) <= 25) {
          System.out.println("IN RANGE!");
          System.out.println("SHOOTING!");
          // CommandScheduler.getInstance().schedule(new SpinUpShooter(shooter)
          // .withTimeout(2).andThen(new AutoShoot(shooter, feeder)))
          addCommands(new SpinUpShooter(shooter), new RunFeeder(feeder));
        } else {
      System.out.println("NOT IN RANGE! GET BACK TO WORK!");
    } 
    } else {
      System.out.println("NO APRILTAG DETECTED! GET BETTER!");
    }
  } 
}
