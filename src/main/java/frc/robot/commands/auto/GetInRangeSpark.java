// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LimeLightCamera;
import frc.robot.subsystems.SparkDriveTrain;

public class GetInRangeSpark extends Command {

  private SparkDriveTrain driveTrain;
  private LimeLightCamera camera;

  /** Creates a new GetInRangeSpark. */
  public GetInRangeSpark(SparkDriveTrain driveTrain, LimeLightCamera camera) {
    this.driveTrain = driveTrain;
    this.camera = camera;
    addRequirements(driveTrain, camera);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //driveTrain.stopDriveTrain();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("AUTO RUNNING");
    System.out.println(camera.estimateDistance(0, 21, 23));

    if(camera.hasValidTargets() == 1) {
      // go back until we're in range
      if(camera.estimateDistance(0, 21, 29) < 40) {
        driveTrain.arcadeDrive(-.1, 0);
      } else {
        end(true);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stopDriveTrain();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
