package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.*;

public class OI{
    public Joystick stick = new Joystick(Constants.kJoyStick);
    public Button steer = new JoystickButton(stick, Constants.kSteerButton);
    public OI(){
        //steer.whenPressed(new GoToEncoderPos(300, Robot.module));
    }
}