// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ControlSwerve;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.RunIntakeAndFeeder;
import frc.robot.commands.driveTrains.HighGear;
import frc.robot.commands.driveTrains.SparkDrive;
import frc.robot.commands.driveTrains.WCPTeleopDrive;
import frc.robot.commands.feeder.Feed;
import frc.robot.commands.intake.ReverseIntake;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.intake.StopIntake;
import frc.robot.commands.pneumatics.RunCompressor;
import frc.robot.commands.shooter.SpinUpShooter;
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
  
  private final SwerveDrive swerveDrive = new SwerveDrive();
  
  //private final WCPDriveTrain driveTrain = new WCPDriveTrain();
  
  //private final SparkDriveTrain sparkDriveTrain = new SparkDriveTrain();
  //private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();
  private final Feeder feeder = new Feeder();
  private final LimeLightCamera camera = new LimeLightCamera();
  private final AirCompressor andyMarkCompressor = new AirCompressor();
  
  //private final Shooter shooter = new Shooter();
  //private final Feeder feeder = new Feeder();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // driveTrain.setupInstruments();
    // driveTrain.loadSong("CRANK_DAT");
    // driveTrain.playSong();
    
    // TODO: UNCOMMENT ME FOR SWERVE
    
    
    swerveDrive.setDefaultCommand(new ControlSwerve(swerveDrive,
     () -> controller.getRawAxis(1),
      () -> controller.getRawAxis(0),
       () -> controller.getRawAxis(4),
        () -> false)); // turn FOC off for now
    
    
    //andyMarkCompressor.setDefaultCommand(new InstantCommand(() -> andyMarkCompressor.enableCompressor()));
    andyMarkCompressor.setDefaultCommand(new RunCompressor(andyMarkCompressor));
    // driveTrain.setDefaultCommand(new WCPTeleopDrive(xboxController, driveTrain));
    //sparkDriveTrain.setDefaultCommand(new SparkDrive(sparkDriveTrain, xboxController));

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

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //xboxController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    
    //xboxController.a().onTrue(new RunIntakeAndFeeder(intake, feeder)
    //.until( () -> intake.getNoteSwitch() ) ); // if .until doesn't work, try inversing it with !
    // or use .unless

    //xboxController.x().onTrue(new InstantCommand( () -> driveTrain.highGear()));
    //xboxController.b().onTrue(new InstantCommand( () -> driveTrain.lowGear()));
    //xboxController.a().onTrue(new InstantCommand( () -> driveTrain.shifterOff()));
    // TODO: implement shooting w/ feeder command. PLS USE COMMMAND COMPOSITION!!!

    controller.a().onTrue(new InstantCommand( () -> swerveDrive.resetModuleEncoders()));
    //xboxController.a().onTrue(new SpinUpShooter(shooter).andThen(new Feed(feeder)));
    
    //xboxController.a().onTrue(new SpinUpShooter(shooter));
    //xboxController.b().onTrue(new Feed(feeder));
  
    //controller.a().onTrue(new RunIntake(intake));
    //controller.b().onTrue(new StopIntake(intake));
    //controller.y().onTrue(new ReverseIntake(intake));// make oppsite

    // xboxController.rightBumper().onTrue(new HighGear(driveTrain));
    // TODO: UNCOMMENT ME FOR SWERVE
    /* 
    new JoystickButton(controller, 1)
    .onTrue(new InstantCommand(() -> swerveDrive.zeroHeading()));
    */
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
