// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.Autos;
import frc.robot.commands.ControlSwerve;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.StopAll;
import frc.robot.commands.auto.BackUpInRangeSpark;
import frc.robot.commands.auto.GetBackToSpeaker;
import frc.robot.commands.auto.Nothing;
import frc.robot.commands.auto.autoRunIntake;
import frc.robot.commands.driveTrains.HighGear;
import frc.robot.commands.driveTrains.SparkDrive;
import frc.robot.commands.driveTrains.WCPTeleopDrive;
import frc.robot.commands.feeder.ReverseFeeder;
import frc.robot.commands.feeder.RunFeeder;
import frc.robot.commands.feeder.ContainNote;
import frc.robot.commands.feeder.ContainNoteAuto;
import frc.robot.commands.feeder.FeedWithTimer;
import frc.robot.commands.feeder.StopFeeder;
import frc.robot.commands.intake.ReverseIntake;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.intake.StopIntake;
import frc.robot.commands.pneumatics.RunCompressor;
import frc.robot.commands.shooter.ShootWithFeeder;
import frc.robot.commands.shooter.ShootWithTimer;
import frc.robot.commands.shooter.SpinUpShooter;
import frc.robot.commands.shooter.StopShooter;
import frc.robot.subsystems.AirCompressor;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimeLightCamera;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SparkDriveTrain;
import frc.robot.subsystems.WCPDriveTrain;
import frc.robot.subsystems.swerve.SwerveDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController controller =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  
  //private final WCPDriveTrain driveTrain = new WCPDriveTrain();
  private final SparkDriveTrain sparkDriveTrain = new SparkDriveTrain();

  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();
  private final Feeder feeder = new Feeder();
  private final LimeLightCamera camera = new LimeLightCamera();
  private final AirCompressor andyMarkCompressor = new AirCompressor();

  // Button Board
  private Joystick buttonBoard = new Joystick(OperatorConstants.ButtonBoardPort);

  // Button Board Buttons
  private JoystickButton feedBtn, grabNoteBtn, shootBtn, 
                        reverseFeederBtn, releaseNoteBtn, stopAllBtn,
                        automaticShoot, automaticIntake, getInRange;
                    

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Button Board Button Objects
    feedBtn = new JoystickButton(buttonBoard, OperatorConstants.FeedBtn);
    grabNoteBtn = new JoystickButton(buttonBoard, OperatorConstants.GrabNoteBtn);
    automaticIntake = new JoystickButton(buttonBoard, OperatorConstants.GrabNoteOnPressBtn); // "retract"
    shootBtn = new JoystickButton(buttonBoard, OperatorConstants.ShootBtn);
    automaticShoot = new JoystickButton(buttonBoard, OperatorConstants.ShootOnPressBtn); // "extend"
    reverseFeederBtn = new JoystickButton(buttonBoard, OperatorConstants.ReverseFeederBtn);
    releaseNoteBtn = new JoystickButton(buttonBoard, OperatorConstants.ReleaseNoteBtn);
    stopAllBtn = new JoystickButton(buttonBoard, OperatorConstants.StopAllBtn);
    getInRange = new JoystickButton(buttonBoard, OperatorConstants.GetInRangeBtn);
    configureBindings();
        
    
    //andyMarkCompressor.setDefaultCommand(new InstantCommand(() -> andyMarkCompressor.enableCompressor()));
    andyMarkCompressor.setDefaultCommand(new RunCompressor(andyMarkCompressor));
    // driveTrain.setDefaultCommand(new WCPTeleopDrive(xboxController, driveTrain));
    sparkDriveTrain.setDefaultCommand(new SparkDrive(sparkDriveTrain, controller));

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    
    getInRange.onTrue(new BackUpInRangeSpark(sparkDriveTrain, camera));

    feedBtn.whileTrue(new RunFeeder(feeder));
    reverseFeederBtn.whileTrue(new ReverseFeeder(feeder));

    grabNoteBtn.onTrue(new RunIntake(intake));

    automaticIntake.onTrue(new RunIntake(intake)
    .alongWith(new ContainNote(feeder))
    .until(() -> intake.getNoteSwitch() == false));

    releaseNoteBtn.whileTrue(new ReverseIntake(intake));

    shootBtn.onTrue(new ShootWithTimer(shooter));

    // straight up awful code
    // automaticShoot.onTrue(new ShootWithFeeder(shooter, feeder)
    // .withTimeout(7)
    // .andThen(new StopShooter(shooter)
    // .alongWith(new StopFeeder(feeder))));

    automaticShoot.onTrue( new SpinUpShooter(shooter).withTimeout(3)
    .andThen(new AutoShoot(shooter, feeder).withTimeout(3))
    );

    stopAllBtn.onTrue(new StopAll(shooter, feeder, intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return new BackUpInRangeSpark(sparkDriveTrain, camera);
    // return new SpinUpShooter(shooter).withTimeout(3)
    //   .andThen(new AutoShoot(shooter, feeder).withTimeout(1.5))
    //   .andThen(new autoRunIntake(intake))
    //     .alongWith(new ContainNote(feeder))
    //     .until(() -> !intake.getNoteSwitch())
    //       .alongWith(new BackUpInRangeSpark(sparkDriveTrain, camera));

    // this routine also works and is pretty ok
    // return new SpinUpShooter(shooter).withTimeout(3)
    //   .andThen(new AutoShoot(shooter, feeder).withTimeout(1.5))
    //   .andThen(new autoRunIntake(intake)
    //       .alongWith(new ContainNote(feeder))
    //           .alongWith(new BackUpInRangeSpark(sparkDriveTrain, camera))
    //           .until( () -> intake.getNoteSwitch() == false)
    //     );

    //intake.setDefaultCommand(new autoRunIntake(intake).until(() -> !intake.getNoteSwitch()));

    // return new autoRunIntake(intake)
    //   .alongWith(new SpinUpShooter(shooter).withTimeout(2))
    //         .andThen(new AutoShoot(shooter, feeder).withTimeout(1.5)
    //         .andThen(new BackUpInRangeSpark(sparkDriveTrain, camera))
    //         .andThen(new ContainNote(feeder)).withTimeout(1.5));


      //best working auton routine so far.
      return new SpinUpShooter(shooter).withTimeout(3)
      .andThen(new AutoShoot(shooter, feeder).withTimeout(1.5))
      .andThen(new BackUpInRangeSpark(sparkDriveTrain, camera)
        .alongWith(new autoRunIntake(intake)
          .alongWith(new ContainNoteAuto(feeder))
          .until(
            () -> intake.getNoteSwitch() == false
          )
        )
      );
    /*
     * andThen GetBackToSpeaker
     * andThen SpinUpShooter with a 3 second timeout
     * andThen finish AutoShoot
     * 
     */

     /*
    return new SpinUpShooter(shooter).withTimeout(3)
      .andThen(new AutoShoot(shooter, feeder).withTimeout(1.5))
      .andThen(new BackUpInRangeSpark(sparkDriveTrain, camera)
        .alongWith(new autoRunIntake(intake)
          .alongWith(new ContainNote(feeder))
          .until(
            () -> intake.getNoteSwitch() == false
          )
        )
      )
      .andThe(new GetBackToSpeaker(sparkDriveTrain)
        .andThen(new SpinUpShooter(shooter).withTimeout(3)
        .andThen(new AutoShoot(shooter, feeder))
        )
      );
      */
  }
}
