// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class StopShooter extends Command {
  Shooter shooter;
  public StopShooter(Shooter shooter) {
    this.shooter = shooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  private boolean end = false;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.stopMotors();
  }

  @Override
  public void execute(){
    SmartDashboard.putBoolean("Ended", end);
    end = true;
  }

  @Override 
  public void end(boolean interrupted){
    end = true;
  }

  @Override
  public boolean isFinished() {
    return end;
  }
}
