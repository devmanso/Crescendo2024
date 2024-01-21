// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.swerve;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.swerve.SwerveXModule;

public class SwerveXDriveTheory extends SubsystemBase {

  private final SwerveXModule frontLeft = new SwerveXModule(1, 2, false, false);
  private final SwerveXModule frontRight = new SwerveXModule(3, 4, false, false);
  private final SwerveXModule backLeft = new SwerveXModule(5, 6, false, false);
  private final SwerveXModule backRight = new SwerveXModule(7, 8, false, false);

  // I HATE MATH I HATE MATH I HATE MATH djwejifjwefji
  private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
    new Translation2d(.5, .5), // front left
    new Translation2d(.5, -.5), // front right
    new Translation2d(-.5, .5), // back left
    new Translation2d(-.5, -.5) // back right
    // .5 x is .5 meters forwards from the center, and .5 y is .5 meters left
  );

  public void stopModules() {
    frontLeft.stop();
    frontRight.stop();
    backLeft.stop();
    backRight.stop();
  }

  private AHRS gyro = new AHRS(SPI.Port.kMXP);

  public void zeroHeading() {
    gyro.reset();
  }

  public double getHeading() {
    return Math.IEEEremainder(gyro.getAngle(), 360);
  }

  public Rotation2d getRotation2d() {
    return Rotation2d.fromDegrees(getHeading());
  }

  public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
    // Calculate the desired state for each module
    var states = kinematics.toSwerveModuleStates(
      fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, getRotation2d()) : new ChassisSpeeds(xSpeed, ySpeed, rot)
    );
    SwerveDriveKinematics.desaturateWheelSpeeds(states, 10.0);

    // Set the desired state for each module
    frontLeft.setDesiredState(states[0]);
    frontRight.setDesiredState(states[1]);
    backLeft.setDesiredState(states[2]);
    backRight.setDesiredState(states[3]);
  }


  /** Creates a new SwerveXDriveTheory. */
  public SwerveXDriveTheory() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
