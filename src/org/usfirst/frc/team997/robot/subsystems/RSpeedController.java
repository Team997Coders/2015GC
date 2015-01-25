package org.usfirst.frc.team997.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;

public class RSpeedController implements SpeedController{
	
	SpeedController motor;
	boolean mReversed;
	public RSpeedController(SpeedController m, boolean reversed) {
		motor = m;
		mReversed  = reversed;
	}
	
	@Override
	public void pidWrite(double arg0) {
		motor.pidWrite(arg0);
		
	}

	@Override
	public void disable() {
		motor.disable();	
	}

	@Override
	public double get() {
		return motor.get();
	}

	@Override
	public void set(double arg0) {
		if (mReversed){
			motor.set(-arg0);
		} else {
			motor.set(arg0);
		}
	}

	@Override
	public void set(double arg0, byte arg1) {
		if (mReversed){
			motor.set(-arg0,arg1);
		} else {
			motor.set(arg0,arg1);
		}		
	}

}
