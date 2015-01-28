
package org.usfirst.frc.team997.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VelMotor {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private SpeedController myMotor;
	private Encoder myEncoder;
    private double desiredVelocity;
	private double currentCurrent;
	private double calibrationFactor;
    public VelMotor(SpeedController motor, Encoder encoder, double calibrationFac) {
		myMotor = motor;
		myEncoder = encoder;
		calibrationFactor = calibrationFac;
		desiredVelocity = 0;
	}
    private double deadband(double a, double band) {
		if (Math.abs(a)<band) {
			return 0;
		} else {
			return a;
		}
	}
    private TimerTask update = new TimerTask() {
		
		@Override
		public void run() {
			update();
		}
	};
	private void update() {
		double error = deadband((desiredVelocity - myEncoder.getRate()), .05);
		currentCurrent = max(currentCurrent + (error*calibrationFactor),1);
		myMotor.set(currentCurrent);
		SmartDashboard.putNumber("encoderRate",myEncoder.getRate());
		SmartDashboard.putNumber("encoderCounts" ,myEncoder.get());
		SmartDashboard.putNumber("currentCurrent" ,currentCurrent);
		SmartDashboard.putNumber("desiredVel(vel)", desiredVelocity);
	
	}
	public double max(double a, double max){
		if (a>max) {
			return max;
		} else if (a<-max) {
			return -max;
		} else {
			return a;
		}
	}
	public void start() {
		try{
		Timer myTimer = new Timer();
		myTimer.schedule(update,0,5);
		} catch (Exception a) {
			
		}
	}
	
	public void setDesiredVelocity(double rate) {
		desiredVelocity = rate;
	}
	
}

