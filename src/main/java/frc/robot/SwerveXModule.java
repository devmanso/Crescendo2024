package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

public class SwerveXModule {
    // Each module has two motors
    private final TalonFX driveMotor;
    private final TalonFX turningMotor;
    private boolean driveMotorReversed;
    private boolean turningMotorReversed;

    public SwerveXModule(int driveMotorId, int turningMotorId, boolean driveMotorReversed, boolean turningMotorReversed) {
        driveMotor = new TalonFX(driveMotorId);
        turningMotor = new TalonFX(turningMotorId);

        driveMotor.setInverted(driveMotorReversed);
        turningMotor.setInverted(turningMotorReversed);
        this.driveMotorReversed = driveMotorReversed;
        this.turningMotorReversed = turningMotorReversed;
    }

    
}
