package org.usfirst.frc.team997.robot;


import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public ControllerTwoJoysticks myController;
	public Joystick jumpPad;
	public OI () {
		myController = new ControllerTwoJoysticks(0);
		jumpPad = new Joystick(1);
	}
	
	public double getDesiredArcadeLeftSpeed() {
		return deadBand((myController.getLY() + myController.getRX()),.1);
	}
	
	public double getDesiredArcadeRightSpeed() {
		return deadBand((myController.getLY() - myController.getRX()),.1);
	}
	
	public double getDesiredElevatorPosition() {
		return jumpPad.getY();
	}
	
	public double deadBand(double a, double dead) {
		if (Math.abs(a)<dead){
			return 0;
		} else {
			return a;
		}
	}
	
	public void setLED (int led, boolean value){
		jumpPad.setOutput(led, value);
	}

	public void SmartDashboard() {
		
	}
}