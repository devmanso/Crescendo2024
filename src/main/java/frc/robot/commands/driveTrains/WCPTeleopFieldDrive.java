// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveTrains;

import com.hyperdrive.hyperlib.DriveFilter;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.DrivingFilter;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.WCPDriveTrain;

public class WCPTeleopFieldDrive extends Command {
  CommandXboxController controller;
  WCPDriveTrain driveTrain;

  /** Creates a new WCPTeleopDrive. */
  public WCPTeleopFieldDrive(CommandXboxController controller, WCPDriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controller = controller;
    this.driveTrain = driveTrain;
    
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // get robot yaw orientation
    double angle = driveTrain.getGyroYaw();

    // angle might be in radians
    double theta = Math.toRadians(angle);

    // get field relative input
    double v_y_field = controller.getLeftY();
    double v_x_field = controller.getLeftX();

    // transform to robot relative inputs
    double v_x_robot = v_x_field * Math.cos(theta) + v_y_field * Math.sin(theta);
    double v_y_robot = -v_x_field * Math.sin(theta) + v_y_field * Math.cos(theta);

    // Calculate motor speeds for tank drive
    double left_motor_speed = v_x_robot - v_y_robot;  // Left motor speed
    double right_motor_speed = v_x_robot + v_y_robot;  // Right motor speed

    driveTrain.setLeftMotors(left_motor_speed * .3);
    driveTrain.setRightMotors(right_motor_speed * .3);
    
    //SmartDashboard.putNumber("", right_motor_speed)
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
