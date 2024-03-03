package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class ElevateClimbers extends Command {
  private Climber climber;
  /** Creates a new ElevateClimbers. */
  public ElevateClimbers(Climber climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climber.coupledUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.stopBoth();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
