package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Chris G.
 * ASF #LAG
 */
public class Controller {
	/**
	 * Da joystick
	 */
	private Joystick j;
	
	/**
	 * Give the dang port for da Controooler
	 * @param porterino
	 */
	public Controller(int porterino) {
		j = new Joystick(porterino);
	}
	
	/**
	 * Left x
	 */
	public double getX() {
		return j.getX();
	}
	
	/**
	 * Left y
	 */
	public double getLY() {
		return -j.getY();
	}
	
	/**
	 * Left x
	 */
	public double getLX() {
		return j.getX();
	}
	
	/**
	 * Left y
	 */
	public double getY() {
		return -j.getY();
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
		return (j.getRawAxis(2)>.75);
	}
	/**
	 * Right trigger
	 */
	public boolean getRT() {
		return (j.getRawAxis(3)>.75);
	}

	/**
	 * Left Button (above trigger)
	 */
	public boolean getLB() {
		return j.getRawButton(5);
	}
	
	/**
	 * Right button (above trigger)
	 */
	public boolean getRB() {
		return j.getRawButton(6);
	}
	
	public boolean getRawButton(int b){
		return j.getRawButton(b);
	}
}
