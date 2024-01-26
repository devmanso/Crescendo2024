// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.hyperdrive.hyperlib.LimeLight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLightCamera extends SubsystemBase {
  /** Creates a new LimeLightCamera. */
  public LimeLightCamera() {}

  public double getDistanceFromAprilTag(double limelightMountAngleDegrees, 
  double goalHeightInches, double limelightLensHeightInches) {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);

    double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

    //calculate distance
    double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) 
    / Math.tan(angleToGoalRadians);
    return distanceFromLimelightToGoalInches;
  }

  public double autoEstimateDistance() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);

    // how many degrees back is your limelight rotated from perfectly vertical?
    double limelightMountAngleDegrees = 0.0; 

    // distance from the center of the Limelight lens to the floor
    double limelightLensHeightInches = 8.5; 

    // distance from the target to the floor
    double goalHeightInches = 32; 

    double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

    //calculate distance
    double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)
     / Math.tan(angleToGoalRadians);
    return distanceFromLimelightToGoalInches;
  }


  /**
   * 
   * @return - 1 if there is a valid target, 0 if not
   */
  public double hasValidTargets() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
  }

  /**
   * 
   * @return - vertical offset of crosshair to target
   */
  public double getVerticalOffset() {
     return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  }

  /**
   * 
   * @return - horizontal offset of crosshair to target
   */
  public double getHorizontalOffset() {
     return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  /**
   * changes pipeline to 0 (AprilTag pipeline), be sure that the name of the limelight is set to "limelight"
   */
  public void setToAprilTagPipeline() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
  }

  /**
   * 
   * @return - AprilTag ID as double array
   */
  public double[] getTagID() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tid").getDoubleArray(new double[6]);
  }

  /**
   * 
   * @param mountAngleDegrees - what is the pitch of your limelight?
   * @param lensHeightInches - how high is how limelight?
   * @param goalheightInches - how high is your target?
   * @return - distance as double
   */
  public double estimateDistance(double mountAngleDegrees, double lensHeightInches, double goalheightInches) {
    double verticalOffset = getVerticalOffset();
    double angleToGoalDegrees = mountAngleDegrees + verticalOffset;
    double angleToGoalRadians = angleToGoalDegrees * (3.14159/ 180);
    double distance = (goalheightInches - lensHeightInches) / Math.tan(angleToGoalRadians);
    return distance;
  }

  @Override
  public void periodic() {
    
    if(hasValidTargets() == 1) {
      System.out.println(
        autoEstimateDistance()
      );
      
    }
  }
}
