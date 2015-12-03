package org.usfirst.frc.team997.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BinGrabber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public DoubleSolenoid mySolenoid;

	public BinGrabber(DoubleSolenoid sol) {
		mySolenoid = sol;
	}

	public void initDefaultCommand() {

	}

	public void open() {
		mySolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void close() {
		mySolenoid.set(DoubleSolenoid.Value.kReverse);
	}
}

