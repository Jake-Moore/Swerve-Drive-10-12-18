package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.SwerveModule;

public class GoToEncoderPos extends Command {
  double currentPos, sumError, changeInError, lastError, CurrentError;
  int setpoint;
  SwerveModule mymodule;
  public GoToEncoderPos(int pos, SwerveModule module){
    this.mymodule = module;
    this.setpoint = pos;
    sumError = 0;
    lastError = getError();
    CurrentError = lastError;
  }
  public int getError(){
    int E1, E2;
    if (setpoint > mymodule.getSteeringEncoder()){
      E1 = ((-(1023 - setpoint)) - mymodule.getSteeringEncoder());
    }else{
      E1 = (setpoint + (1023 - mymodule.getSteeringEncoder()));
    }
    E2 = setpoint - mymodule.getSteeringEncoder();
    if (Math.abs(E1) > (Math.abs(E2))){
      //System.out.println("E2");
      return E2;
    }else{
      //System.out.println("E1");
      return E1;
    }
  }
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    lastError = CurrentError;
    CurrentError = getError();
    sumError += getError();
    changeInError = lastError - CurrentError;
    double output = (Constants.SwerveModulekP * getError() + Constants.SwerveModulekI * sumError - Constants.SwerveModulekD * changeInError);
    mymodule.setSteering(output);
    System.out.println("Error: " +getError()+ "Pos:" +mymodule.getSteeringEncoder());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
