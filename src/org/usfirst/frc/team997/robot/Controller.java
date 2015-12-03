package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Chris G.
 * ASF #LAG
 */
//extends Joystick so that buttons can still work in OI @Christopher
public class Controller extends Joystick {
	/**
	 * Da joystick
	 */
	private Joystick j;
	
	/**
	 * Give the dang port for da Controooler
	 * @param porterino
	 */
	public Controller(int porterino) {
		super(porterino);
		j = new Joystick(porterino);
	}
	
	/**
	 * Left x
	 */
	public double getLX() {
		return j.getRawAxis(0);
	}
	
	/**
	 * Left y
	 */
	public double getLY() {
		return -j.getRawAxis(1);
	}
	
	/**
	 * Right x
	 */
	public double getRX() {
		return j.getRawAxis(4);
	}

	/**
	 * Right y
	 */
	public double getRY() {
		return -j.getRawAxis(5);
	}
	
	/**
	 * Left trigger
	 */
	public boolean getLT() {
		return (j.getRawAxis(2)<-.75);
	}
	
	public double getTriggersRaw() {
		return j.getRawAxis(2);
	}
	
	/**
	 * Right trigger
	 */
	public boolean getRT() {
		return (j.getRawAxis(2)>.75);
	}
	

	/**
	 * Left Button (above trigger)
	 */
	public boolean getLB() {
		return j.getRawButton(4);
	}
	
	/**
	 * Right button (above trigger)
	 */
	public boolean getRB() {
		return j.getRawButton(5);
	}
	
	public boolean getRawButton(int b){
		return j.getRawButton(b);
	}

	public double getTriggersAsAxis() {
		return j.getRawAxis(2)-j.getRawAxis(3);
	}

	public double getModZ() {
		double d = (j.getZ() - 1)/2;
		return d;
	}
	
	
}