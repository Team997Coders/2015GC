package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.ArcadeDrive;
import org.usfirst.frc.team997.robot.commands.DriveStraight;
import org.usfirst.frc.team997.robot.commands.ElevatorRaw;
import org.usfirst.frc.team997.robot.commands.GatherIn;
import org.usfirst.frc.team997.robot.commands.GatherOut;
import org.usfirst.frc.team997.robot.commands.GatherRotate;
import org.usfirst.frc.team997.robot.commands.SetGatherSolenoid;
import org.usfirst.frc.team997.robot.commands.SetGatherSolenoidToggle;
import org.usfirst.frc.team997.robot.commands.ToggleClaw;
import org.usfirst.frc.team997.robot.commands.ToggleShift;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Controller myController;
	public Controller jumpPad;
	
	public Button setSolenoidButton;
	public Button shiftButton;
	public Button GatherOutButton;
	public Button GatherInButton;
	public Button GatherRotateButton;
	public Button ToggleClawButton;
	
	
	public OI () {
		myController = new Controller(0);
		jumpPad = new Controller(1);
		
		setSolenoidButton = new JoystickButton(myController, 1);
		shiftButton = new JoystickButton(myController, 3);
		GatherInButton = new JoystickButton(myController, 5);
		GatherOutButton = new JoystickButton(myController, 6);
		GatherRotateButton = new JoystickButton(myController, 2);
		ToggleClawButton = new JoystickButton(myController, 4);
		
		setSolenoidButton.whenPressed(new SetGatherSolenoidToggle());
		shiftButton.whenPressed(new ToggleShift());
		GatherInButton.whileHeld(new GatherIn());
		GatherOutButton.whileHeld(new GatherOut());
		GatherRotateButton.whileHeld(new GatherRotate());
		ToggleClawButton.whenPressed(new ToggleClaw());
		
		
		
		SmartDashboard.putData("TogggleShift", new ToggleShift());
		SmartDashboard.putData("ManipulatorShift toggle", new SetGatherSolenoidToggle());
		SmartDashboard.putData("ManipulatorShift on ", new SetGatherSolenoid(true));
		SmartDashboard.putData("ManipulatorShift off", new SetGatherSolenoid(false));
		
		SmartDashboard.putData("Arcade Drive", new ArcadeDrive());
		SmartDashboard.putData("ElevatorRaw", new ElevatorRaw());
		SmartDashboard.putData("DriveStright", new DriveStraight());

		
	}
	
	
	public double getDesiredArcadeLeftSpeed() {
		return getGear() * deadBand(getController().getLY() + getController().getRX(),.25);
	} 
	
	public double getDesiredArcadeRightSpeed() {
		return getGear()*deadBand(getController().getLY() - getController().getRX(),.25);
	}
	
	public Controller getController() {
		if (jumpPad.getRawButton(1)) {
			return jumpPad;
		} else {
			return myController;
		}
	}
	
	public double deadBand(double a, double dead) {
		if (Math.abs(a)<dead){
			return 0;
		} else {
			return a;
		}
	}
	
	public double getGear() {
		return .5;
	}

	public void SmartDashboard() {
//		SmartDashboard.putNumber("left X", myController.getLX());
//		SmartDashboard.putNumber("right X", myController.getRX());
//		SmartDashboard.putNumber("left y", myController.getLY());
//		SmartDashboard.putNumber("right y", myController.getRY());
//		SmartDashboard.putNumber("desired arcade left", getDesiredArcadeLeftSpeed());
//		SmartDashboard.putNumber("desired arcade right", getDesiredArcadeRightSpeed());
		
	}

	public double getElevatorVoltage() {
		return getController().getTriggersAsAxis();
	}
}

