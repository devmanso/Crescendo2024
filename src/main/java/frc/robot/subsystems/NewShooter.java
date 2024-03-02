// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.NewFeederAndShooter;

public class NewShooter extends SubsystemBase {

  // id = 1
  private TalonFX shooterTop = new TalonFX(NewFeederAndShooter.NEW_SHOOTER_TOP_ID);

  // id = 22
  private TalonFX shooterBottom = new TalonFX(NewFeederAndShooter.NEW_SHOOTER_BOTTOM_ID);

  // private CANSparkMax feederMotor = new CANSparkMax(62, MotorType.kBrushless);


  /** Creates a new NewShooter. */
  public NewShooter() {
  }

  public void newShoot(){
    shooterTop.set(0.95);
    shooterBottom.set(0.95);
    // feederMotor.set(0.95);
  }

  public void stopMotors(){
    shooterTop.stopMotor();
    shooterBottom.stopMotor();
    // feederMotor.stopMotor();
  }

  private XboxController xbox = new XboxController(1);

  @Override
  public void periodic() {
    if (xbox.getAButtonPressed() == true ){
      shooterTop.set(-0.95);
      shooterBottom.set(-0.95);
      //feederMotor.set(0.95);
    } 
    
    if (xbox.getBButtonPressed() == true){
      shooterTop.stopMotor();
      shooterBottom.stopMotor();
      //feederMotor.stopMotor();
    }
    // This method will be called once per scheduler run
  }
}
