package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Chris G.
 */
public class ControllerTwoJoysticks {
	/**
	 * Da joystick
	 */
	private Joystick j;
	
	/**
	 * Give the dang port for da Controooler
	 * @param porterino
	 */
	public ControllerTwoJoysticks(int porterino) {
		j = new Joystick(porterino);
	}

    /**
     * Left x
     */
    public double getLX() {
        return j.getX(Hand.kLeft);
    }

	/**
	 * Left y
	 */
	public double getLY() {
		return -j.getY(Hand.kLeft);
	}

	/**
	 * Right x
	 */
	public double getRX() {
		return j.getX(Hand.kRight);
	}

	/**
	 * Right y
	 */
	public double getRY() {
		return -j.getY(Hand.kRight);
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
	
	public boolean getRawButton(int port){
		return j.getRawButton(port);
	}

    public double getRawAxis(int port) {
        return j.getRawAxis(port);
    }

	public void setLed(int led, boolean value) {
		j.setOutput(led, value);
	}
}
