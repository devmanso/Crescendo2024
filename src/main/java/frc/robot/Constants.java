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

        public static final int COMPRESSOR_ID = 28;

        public static class ClimberConstants {
                public static final int CLIMBER_LEFT = 30;
                public static final int CLIMBER_RIGHT = 31;
                public static final int CLIMBER_MOTOR_ID = 40;
        }

        public static class WestCoastDriveTrain {
                public static final int MASTER_LEFT = 1;
                public static final int FOLLOWER_LEFT = 2;
                public static final int MASTER_RIGHT = 3;
                public static final int FOLLOWER_RIGHT = 4;

                public static final int LED_ID = 9;
                public static final int LED_BUFFER = 60;
        }

        public static class OperatorConstants {
                // controllers
                public static final int kDriverControllerPort = 0;
                public static final int ButtonBoardPort = 1;

                // joystick buttons
                public static final int RunIntake = 1;
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
                
                public static final int RunAllSubsystems = 10;

        }

        public static class ShooterAndFeeder {
                public static final int BOTTOM_SHOOTER_ID = 22;
                public static final int TOP_SHOOTER_ID = 23;
                public static final int FEEDER_ID = 25;
                public static final int FeederSwitchPort = 0;
        }

        public static class IntakeConstants {
                public static final int INTAKE_ID = 30;
                public static final int LIMITSWITCH_ID = 0;
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
                public static final double MountAngleDegrees = 12; //72.5;
                public static final double LensHeightInches = 29.5;
                public static final double GoalHeightInches = 52; // real is 52
                //public static double StartingDistanceFromSpeaker = LimeLightCamera.estimateDistance(MountAngleDegrees, LensHeightInches, GoalHeightInches);
        }
}