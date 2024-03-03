// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.robot.subsystems.LimeLightCamera;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
        // all motor id's are placeholders and are subject to change

        public static final int COMPRESSOR_ID = 20;

        public static class ClimberConstants {
                public static final int CLIMBER_LEFT = 30;
                public static final int CLIMBER_RIGHT = 31;
        }

        public static class TestingMotorIds {
                public static final int NEW_SHOOTER_TOP_ID = 40;
                public static final int NEW_SHOOTER_BOTTOM_ID = 41;

                public static final int NEW_FEEDER_ID = 42;
                public static final int NEW_INTAKE_ID = 43;
        }

        public static class WestCoastDriveTrain {
                public static final int MASTER_LEFT = 0;// 1
                public static final int FOLLOWER_LEFT = 2;
                public static final int MASTER_RIGHT = 3;
                public static final int FOLLOWER_RIGHT = 4;
        }

        public static class OperatorConstants {
                // controllers
                public static final int kDriverControllerPort = 0;
                public static final int ButtonBoardPort = 1;
                public static final int XboxControllerPort = 0;

                // joystick buttons
                public static final int GrabNoteBtn = 1;
                public static final int FeedBtn = 3;
                public static final int ShootBtn = 5;
                public static final int ReverseFeederBtn = 4;
                public static final int ReleaseNoteBtn = 2;
                public static final int StopAllBtn = 6;
                // public static final int GrabNoteNoOverride = 7;
                public static final int ShootOnPressBtn = 8;
                // public static final int ShootFeedBtn = 0;
                public static final int GrabNoteOnPressBtn = 7;
                public static final int GetInRangeBtn = 9;

        }

        public static class ShooterAndFeeder {
                public static final int SHOOTER_ID = 22;// 22
                public static final int FEEDER_ID = 19;// 4 // Spark Motor
                public static final int FeederSwitchPort = 0;
        }

        public static class IntakeConstants {
                public static final int INTAKE_ID = 20;
                public static final int LIMITSWITCH_ID = 0;
        }

        public static final class ModuleConstants {
                // TODO: ask mechanical about these
                public static final double kWheelDiameterMeters = Units.inchesToMeters(4);
                public static final double kDriveMotorGearRatio = 1 / 5.8462;
                public static final double kTurningMotorGearRatio = 1 / 18.0;
                public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
                public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
                public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
                public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;
                public static final double kPTurning = 0.5;
        }

        public static final class DriveConstants {
                // TODO: get distance from left and right wheels
                public static final double kTrackWidth = Units.inchesToMeters(21);
                // TODO: get distance from front and back wheels
                public static final double kWheelBase = Units.inchesToMeters(25.5);
                public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
                        new Translation2d(-kWheelBase / 2, kTrackWidth / 2)
                );

                // TODO: MODIFY THESE PORTS FOR THE ROBOT
                public static final int kFrontLeftDriveMotorPort = 10;
                public static final int kBackLeftDriveMotorPort = 9;
                public static final int kFrontRightDriveMotorPort = 5;
                public static final int kBackRightDriveMotorPort = 15;

                public static final int kFrontLeftTurningMotorPort = 11;
                public static final int kBackLeftTurningMotorPort = 16;
                public static final int kFrontRightTurningMotorPort = 8;
                public static final int kBackRightTurningMotorPort = 3;

                // TODO: all turning encoders are reversed
                // TODO: front and back right drive encoders are NOT reversed
                // ask mechanical about these
                public static final boolean kFrontLeftTurningEncoderReversed = true;
                public static final boolean kBackLeftTurningEncoderReversed = true;
                public static final boolean kFrontRightTurningEncoderReversed = true;
                public static final boolean kBackRightTurningEncoderReversed = true;

                public static final boolean kFrontLeftDriveEncoderReversed = true;
                public static final boolean kBackLeftDriveEncoderReversed = true;
                public static final boolean kFrontRightDriveEncoderReversed = true;
                public static final boolean kBackRightDriveEncoderReversed = true;

                // TODO: ask mechanical about these
                public static final int kFrontLeftDriveAbsoluteEncoderPort = 11;
                public static final int kBackLeftDriveAbsoluteEncoderPort = 16;
                public static final int kFrontRightDriveAbsoluteEncoderPort = 8;
                public static final int kBackRightDriveAbsoluteEncoderPort = 15;

                // TODO: Use SmartDashboard to find the right value for these
                public static final boolean kFrontLeftDriveAbsoluteEncoderReversed = false;
                public static final boolean kBackLeftDriveAbsoluteEncoderReversed = false;
                public static final boolean kFrontRightDriveAbsoluteEncoderReversed = false;
                public static final boolean kBackRightDriveAbsoluteEncoderReversed = false;

                // TODO: if you dont know, ask mechanical or something, try to have them align it perfectly as possible
                // if this is all to difficult, then just set it to 0 and pray to Allah the code works
                public static final double kFrontLeftDriveAbsoluteEncoderOffsetRad = 0;
                public static final double kBackLeftDriveAbsoluteEncoderOffsetRad = 0;
                public static final double kFrontRightDriveAbsoluteEncoderOffsetRad = 0;
                public static final double kBackRightDriveAbsoluteEncoderOffsetRad = 0;

                // if robot drives too slow, try increasing this
                public static final double kPhysicalMaxSpeedMetersPerSecond = 10; // 5
                public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;

                public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 4;
                public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = kPhysicalMaxAngularSpeedRadiansPerSecond / 4;
                public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
                public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;
        }

        public static final class AutoConstants {
                public static final double kMaxSpeedMetersPerSecond = DriveConstants.kPhysicalMaxSpeedMetersPerSecond / 4;
                public static final double kMaxAngularSpeedRadiansPerSecond = DriveConstants.kPhysicalMaxAngularSpeedRadiansPerSecond / 10;
                public static final double kMaxAccelerationMetersPerSecondSquared = 3;
                public static final double kMaxAngularAccelerationRadiansPerSecondSquared = Math.PI / 4;
                public static final double kPXController = 1.5;
                public static final double kPYController = 1.5;
                public static final double kPThetaController = 3;

                public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
                        kMaxAngularSpeedRadiansPerSecond,
                        kMaxAngularAccelerationRadiansPerSecondSquared
                );
        }

        public static final class OIConstants {
                public static final int kDriverControllerPort = 0;

                public static final int kDriverYAxis = 1;
                public static final int kDriverXAxis = 0;
                public static final int kDriverRotAxis = 4;
                public static final int kDriverFieldOrientedButtonIdx = 1;

                public static final double kDeadband = 0.05;
        }

        public static class CameraSettings {
                public static final int width = 1920;
                public static final int height = 1080;
                public static final String name = "Logitech Cam";
                public static final int USBPort = 1;
        }

        public static class LimelightConstants {
                // I'm not sure if these are actually accurate (other than GoalHeightInches ofc)
                public static final double MountAngleDegrees = 48.5;
                public static final double LensHeightInches = 14;
                public static final double GoalHeightInches = 52;
                public static double StartingDistanceFromSpeaker = LimeLightCamera.estimateDistance(MountAngleDegrees, LensHeightInches, GoalHeightInches);
        }
}