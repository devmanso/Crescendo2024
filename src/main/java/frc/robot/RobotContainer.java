// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.RunAllSubsystems;
import frc.robot.commands.StopAll;
import frc.robot.commands.auto.AutoMovement;
import frc.robot.commands.auto.GetThirdNote;
import frc.robot.commands.auto.autoRunIntake;
import frc.robot.commands.driveTrains.HighGear;
import frc.robot.commands.driveTrains.LowGear;
import frc.robot.commands.driveTrains.WCPTeleopDrive;
import frc.robot.commands.feeder.ReverseFeeder;
import frc.robot.commands.feeder.RunFeeder;
import frc.robot.commands.feeder.ContainNote;
import frc.robot.commands.feeder.ContainNoteAuto;
import frc.robot.commands.intake.ReverseIntake;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.pneumatics.RunCompressor;
import frc.robot.commands.shooter.TeleopAutoShoot;
import frc.robot.commands.shooter.ShootWithTimer;
import frc.robot.commands.shooter.SpinUpShooter;
import frc.robot.subsystems.AirCompressor;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimeLightCamera;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SparkDriveTrain;
import frc.robot.subsystems.WCPDriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
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
  //private final Trigger teleopAutoShoot = controller.y();  
  
  private final WCPDriveTrain driveTrain = new WCPDriveTrain();
  private final SparkDriveTrain sparkDriveTrain = new SparkDriveTrain();

  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();
  private final Feeder feeder = new Feeder();
  private final LimeLightCamera camera = new LimeLightCamera();
  private final AirCompressor andyMarkCompressor = new AirCompressor();

  public void stopAllSubsystemsMotors() {
    intake.stop();
    shooter.stopShooter();
    feeder.stopFeeder();
  }

  // Button Board
  private Joystick buttonBoard = new Joystick(OperatorConstants.ButtonBoardPort);

  // Button Board Buttons
  private JoystickButton feedBtn, RunIntake, shootBtn, 
                        reverseFeederBtn, releaseNoteBtn, stopAllBtn,
                        automaticShoot, automaticIntake, getInRange, runAll;
                    

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Button Board Button Objects
    feedBtn = new JoystickButton(buttonBoard, OperatorConstants.FeedBtn);
    RunIntake = new JoystickButton(buttonBoard, OperatorConstants.RunIntake);
    automaticIntake = new JoystickButton(buttonBoard, OperatorConstants.GrabNoteOnPressBtn); // "retract"
    shootBtn = new JoystickButton(buttonBoard, OperatorConstants.ShootBtn);
    automaticShoot = new JoystickButton(buttonBoard, OperatorConstants.ShootOnPressBtn); // "extend"
    reverseFeederBtn = new JoystickButton(buttonBoard, OperatorConstants.ReverseFeederBtn);
    releaseNoteBtn = new JoystickButton(buttonBoard, OperatorConstants.ReleaseNoteBtn);
    stopAllBtn = new JoystickButton(buttonBoard, OperatorConstants.StopAllBtn);
    getInRange = new JoystickButton(buttonBoard, OperatorConstants.GetInRangeBtn);
    runAll = new JoystickButton(buttonBoard, OperatorConstants.RunAllSubsystems);
    getInRange = new JoystickButton(buttonBoard, OperatorConstants.GetInRangeBtn);

    configureBindings();
        
    
    //andyMarkCompressor.setDefaultCommand(new InstantCommand(() -> andyMarkCompressor.enableCompressor()));
    andyMarkCompressor.setDefaultCommand(new RunCompressor(andyMarkCompressor));
    driveTrain.setDefaultCommand(new WCPTeleopDrive(controller, driveTrain));
    //sparkDriveTrain.setDefaultCommand(new SparkDrive(sparkDriveTrain, controller));

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

    //teleopAutoShoot.onTrue(new TeleopAutoShoot(shooter, feeder, camera));
    new Trigger(controller.leftBumper()).onTrue(new LowGear(driveTrain));
    new Trigger(controller.rightBumper()).onTrue(new HighGear(driveTrain));
    
    getInRange.onTrue(new TeleopAutoShoot(driveTrain));
    
    runAll.whileTrue(new RunAllSubsystems(intake, feeder, shooter));

    feedBtn.whileTrue(new RunFeeder(feeder));
    reverseFeederBtn.whileTrue(new ReverseFeeder(feeder));

    RunIntake.onTrue(new RunIntake(intake));

    automaticIntake.onTrue(new RunIntake(intake)
    .alongWith(new ContainNote(feeder))
    .until(() -> intake.getNoteSwitch() == false));

    releaseNoteBtn.whileTrue(new ReverseIntake(intake));

    shootBtn.onTrue(new ShootWithTimer(shooter));


    automaticShoot.onTrue( new SpinUpShooter(shooter).withTimeout(3)
    .andThen(new AutoShoot(shooter, feeder).withTimeout(3))
    .andThen(new StopAll(shooter, feeder, intake))
    );

    stopAllBtn.onTrue(new StopAll(shooter, feeder, intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
      // 100 % working auton when testing with Smokey JR, must refactor when programming new robot
       return new SpinUpShooter(shooter).withTimeout(2)
      .andThen(new AutoShoot(shooter, feeder).withTimeout(1.5))


      .andThen(new AutoMovement(driveTrain)
        .alongWith(new autoRunIntake(intake)
          .alongWith(new ContainNoteAuto(feeder))
          .until(
            () -> !intake.getNoteSwitch()
          )
        )
      )

      .andThen(new SpinUpShooter(shooter)
        .withTimeout(2))
      .andThen(new AutoShoot(shooter, feeder)
        .withTimeout(2))
      
      .andThen(new GetThirdNote(driveTrain)
        .alongWith(new autoRunIntake(intake)
          .alongWith(new ContainNote(feeder))
          .until(
            () -> !intake.getNoteSwitch()
          )
        )
      )

      .andThen(new SpinUpShooter(shooter)
        .withTimeout(2))
      .andThen(new AutoShoot(shooter, feeder)
        .withTimeout(2));
  }
}
