package org.usfirst.frc.team997.robot;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Controller myController;
	public Controller jumpPad;
	public OI () {
		myController = new Controller(0);
		jumpPad = new Controller(1);
	}
	
	public double getDesiredArcadeLeftSpeed() {
		return deadBand((myController.getY() + myController.getRX()),.1);
	}
	
	public double getDesiredArcadeRightSpeed() {
		return deadBand((myController.getY() - myController.getRX()),.1);
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
		jumpPad.setLed(led, value);
	}

	public void SmartDashboard() {
		
	}
}