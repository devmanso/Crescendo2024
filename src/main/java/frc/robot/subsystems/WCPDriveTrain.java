// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WestCoastDriveTrain;

public class WCPDriveTrain extends SubsystemBase {
  //AHRS navx;

  Orchestra orchestra = new Orchestra();

  TalonFX leftMaster = new TalonFX(WestCoastDriveTrain.MASTER_LEFT);
  TalonFX leftFollower = new TalonFX(WestCoastDriveTrain.FOLLOWER_LEFT);
  TalonFX rightMaster = new TalonFX(WestCoastDriveTrain.MASTER_RIGHT);
  TalonFX rightFollower = new TalonFX(WestCoastDriveTrain.FOLLOWER_RIGHT);

  DoubleSolenoid shifter = new DoubleSolenoid(28, PneumaticsModuleType.REVPH, 8, 9);

  public void arcadeDrive(double xSpd, double zRot) {
    drive.arcadeDrive(xSpd, zRot, true);
  }

  public void setBrakeMode() {
    leftMaster.setNeutralMode(NeutralModeValue.Brake);
    leftFollower.setNeutralMode(NeutralModeValue.Brake);
    rightMaster.setNeutralMode(NeutralModeValue.Brake);
    rightFollower.setNeutralMode(NeutralModeValue.Brake);
  }

  public void setCoastMode() {
    leftMaster.setNeutralMode(NeutralModeValue.Coast);
    leftFollower.setNeutralMode(NeutralModeValue.Coast);
    rightMaster.setNeutralMode(NeutralModeValue.Coast);
    rightFollower.setNeutralMode(NeutralModeValue.Coast);
  }
  
  public void driveBackwardSlant() {
    leftSide.set(.25);
    rightSide.set(.15);
  }

  public void driveForwardSlant() {
    leftSide.set(-.4);
    rightSide.set(-.2);
  }

  public void driveForward() {
    leftSide.set(-.16);
    rightSide.set(-.16);
  }

  public void taxiOut() {
    leftSide.set(-.2);
    rightSide.set(-.2);
  }

  public void rotate() {
    leftSide.set(.1);
    rightSide.set(-.1);
  }

  public void driveForwardFaster() {
    leftSide.set(-.3);
    rightSide.set(-.3);
  }

  public void driveBackward() {
    leftSide.set(.16);
    rightSide.set(.16);
  }

  public void drive(double xSpd) {
    leftSide.set(xSpd);
    rightSide.set(-xSpd);
  }

  public void driveToSpeaker() {
    leftSide.set(.4);
    rightSide.set(-.5);
  }

  public void highGear() {
    shifter.set(Value.kForward);
  }

  public void lowGear() {
    shifter.set(Value.kReverse);
  }

  public void shifterOff() {
    shifter.set(Value.kOff);
  }

  public void setupInstruments() {
    orchestra.addInstrument(leftMaster);
    orchestra.addInstrument(leftFollower);
    orchestra.addInstrument(rightMaster);
    orchestra.addInstrument(rightFollower);
  }

  // if you are using Spark uncomment this code and comment the code above
  
  // Spark leftMaster = new Spark(2);
  // Spark leftFollower = new Spark(3);
  // Spark rightMaster = new Spark(0);
  // Spark rightFollower = new Spark(1);

  /*
   * be sure to have a .chrp file in the deploy directory, then insert the file name in the param
   */
  public void loadSong(String chrpFile) {
    orchestra.loadMusic(chrpFile);
  }

  /**
   * plays song loaded on Orchestra object
   */
  public void playSong() {
    orchestra.play();
  } 

  public void testMotor(int motorID) {
    switch (motorID) {
      case 0:
        rightMaster.set(1);
      case 1:
        rightFollower.set(1);
        break;
      case 2:
        leftMaster.set(1);
        break;
      case 3:
        leftFollower.set(1);
      default:
        break;
    }
  }
  // over here we'd tupically use MotorControllerGroup, but it's been deprecated
  // please look at the constructor of this class to see how to set follower motors

  MotorControllerGroup leftSide = new MotorControllerGroup(leftMaster, leftFollower);
  MotorControllerGroup rightSide = new MotorControllerGroup(rightMaster, rightFollower);
  DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);

  /** Creates a new WCPDriveTrain. */
  public WCPDriveTrain() {
    //navx = new AHRS(SPI.Port.kMXP);
    // Ensure follower motors mimic master motors
    //leftFollower.setControl(new Follower(leftMaster.getDeviceID(), false));
    //rightFollower.setControl(new Follower(rightMaster.getDeviceID(), false));
    
    //rightMaster.addFollower(rightFollower);
    //leftMaster.addFollower(leftFollower);

    // set the right motors to be inverted
    //rightMaster.setInverted(true);
    //rightFollower.setInverted(false); // technician wired this wierdly, this quick hack
    // should fix this ^^ - Mansour Quddus, if your going w/ TalonFX's then invert the right again
    //rightFollower.setInverted(true);
    // ensure left motors are not inverted
    //leftMaster.setInverted(false);
    //leftFollower.setInverted(false);
    
    // USE FOR KRAKENS
    // TalonFXConfiguration config = new TalonFXConfiguration();

    // config.CurrentLimits.StatorCurrentLimit = 60;
    // config.CurrentLimits.StatorCurrentLimitEnable = true;

    // rightFollower.getConfigurator().apply(config);
    // rightMaster.getConfigurator().apply(config);
    // leftMaster.getConfigurator().apply(config);
    // leftFollower.getConfigurator().apply(config);

    TalonFXConfiguration drivetrainConfig = new TalonFXConfiguration();
    drivetrainConfig.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = 1;

    rightFollower.getConfigurator().apply(drivetrainConfig);
    rightMaster.getConfigurator().apply(drivetrainConfig);
    leftMaster.getConfigurator().apply(drivetrainConfig);
    leftFollower.getConfigurator().apply(drivetrainConfig);


    rightSide.setInverted(true);

    //setupInstruments();
    //loadSong("ninmusic.chrp");
    //playSong();
  }

  /**
   * Sets the left motors to a certain speed.
   * @param speed - from -1 to 1
   */
  public void setLeftMotors(double speed) {
    leftMaster.set(speed);
    leftFollower.set(speed);
  }

  /**
   * Sets the right motors to a certain speed.
   * @param speed - from -1 to 1
   */
  public void setRightMotors(double speed) {
    rightMaster.set(speed);
    rightFollower.set(speed);
  }

  public void stop() {
    // this is a bit unnecessary, but it doesn't hurt to quadruple check lmao
    setLeftMotors(0);
    setRightMotors(0);
    rightMaster.stopMotor();
    rightFollower.stopMotor();
    leftMaster.stopMotor();
    leftFollower.stopMotor();
  }
  
  // public void displayDriveTrainData() {
  //   Shuffleboard.getTab("DriveTrain")
  //   .addNumber("LM_VOLT", () -> leftMaster.getMotorVoltage().getValueAsDouble());

  //   Shuffleboard.getTab("DriveTrain")
  //   .addNumber("LF_VOLT", () -> leftFollower.getMotorVoltage().getValueAsDouble());

  //   Shuffleboard.getTab("DriveTrain")
  //   .addNumber("RM_VOLT", () -> rightMaster.getMotorVoltage().getValueAsDouble());

  //   Shuffleboard.getTab("DriveTrain")
  //   .addNumber("RF_VOLT", () -> rightFollower.getMotorVoltage().getValueAsDouble());
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
