package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	private ElevatorSpeedController mySpeedController;
	//private AccelMotor myAccelMotor;
	private Encoder myEncoder;
	
    // Initialize your subsystem here
    public Elevator(
    		ElevatorSpeedController motor, 
    		double velCal, 
    		double accelMax, 
    		int encoder1, 
    		int encoder2, 
    		double p, 
    		double i, 
    		double d) 
    {
       super(p, i, d);
       setAbsoluteTolerance(RobotMap.absuluteElevatorTolerance);
       setPercentTolerance(RobotMap.percentTolerance);
       mySpeedController = motor;
       myEncoder = new Encoder(encoder1,encoder2);
       myEncoder.setDistancePerPulse(RobotMap.ElevatorDistancePerPulse);
       //myAccelMotor = new AccelMotor(new VelMotor(motor, myEncoder, RobotMap.elevatorVelCal), RobotMap.elevatorMaxAccel);
       LiveWindow.addActuator("Elevator", "PIDSubsystem Controller", getPIDController());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
    	return myEncoder.getDistance();
    }
    
    protected void usePIDOutput(double output) {
       //myAccelMotor.setDesiredVelocity(output);
       mySpeedController.set(output);
    }
    public void setPIDtarget(double target){
    	setSetpoint(target);
    }
    public void elevatorUp(){
    	mySpeedController.set(1);
    }
    
    public void elevatorDown(){
    	mySpeedController.set(-1);
    }
    public void stop() {
    	mySpeedController.set(0);
    }

	public void SmartDashboard() {
		SmartDashboard.putNumber("Elevator Target", this.getSetpoint());
	}
   
}
