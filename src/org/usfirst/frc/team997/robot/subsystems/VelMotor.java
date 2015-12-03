
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
	private String name;
    public VelMotor(SpeedController motor, Encoder encoder, double calibrationFac, String name) {
		myMotor = motor;
		myEncoder = encoder;
		calibrationFactor = calibrationFac;
		desiredVelocity = 0;
		this.name = name;
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
	
	private double getEncoder() {
		//return myEncoder.getRate();
		return currentCurrent;
	}
	private void update() {
		double encoderRate = getEncoder();
		double error = deadband((desiredVelocity - encoderRate), .05);
		
		
		SmartDashboard.putNumber(name + "error", error);
		
		double adjustedError = error*calibrationFactor;
		
		SmartDashboard.putNumber(name + "adjustederror", adjustedError);

		currentCurrent = max(currentCurrent + adjustedError,1);
		 
		myMotor.set(deadband(currentCurrent, .05));
		SmartDashboard.putNumber(name +"encoderRate",myEncoder.getRate());
		SmartDashboard.putNumber(name +"currentCurrent" ,currentCurrent);
		SmartDashboard.putNumber(name +"desiredVel(vel)", desiredVelocity);
	
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

