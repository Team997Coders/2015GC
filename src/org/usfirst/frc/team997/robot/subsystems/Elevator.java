package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.ElevatorRaw;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	private ElevatorSpeedController mySpeedController;
	//private Encoder myEncoder;
	private DigitalInput limitTop;
	private DigitalInput limitLow;
	private DoubleSolenoid claw;
	
    // Initialize your subsystem here
    public Elevator(
    		ElevatorSpeedController motor, 
    		double velCal, 
    		double accelMax, 
    	//	int encoder1, 
    	//	int encoder2, 
    		int limitTopSlot,
    		int limitLowSlot,
    		double p, 
    		double i, 
    		double d,
    		int sol1,
    		int sol2
    		) 
    {
    	
       super(p, i, d);
       try {
    	
    	   limitLow = new DigitalInput(limitLowSlot);
           limitTop = new DigitalInput(limitTopSlot);
	       motor.addLimits(limitTop, limitLow);
	       setAbsoluteTolerance(RobotMap.absuluteElevatorTolerance);
	       setPercentTolerance(RobotMap.percentTolerance);
	       mySpeedController = motor;
	       
	     claw = new DoubleSolenoid(sol1, sol2);
	  //     myEncoder = new Encoder(encoder1,encoder2);
	  //     myEncoder.setDistancePerPulse(RobotMap.ElevatorDistancePerPulse);
	    //added a println
	    //@ Madison
    	} catch (Exception E) {
    		E.printStackTrace();
    		System.out.println("elevator subsytem");
    	}
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new ElevatorRaw());
    }
    
    protected double returnPIDInput() {
   // 	return myEncoder.getDistance();
    return 0;
    }
    
    protected void usePIDOutput(double output) {
      mySpeedController.set(output);
    }
    public void setPIDtarget(double target){
    	setSetpoint(target);
    }
    public void setVoltage(double voltage){
    	mySpeedController.set(voltage);
    }

	public void SmartDashboard() {
		SmartDashboard.putNumber("Elevator Target", this.getSetpoint());
		//SmartDashboard.putNumber("Elevator Encoder", this.myEncoder.getDistance());
		SmartDashboard.putBoolean("TopLimit", getTopLimit());
		SmartDashboard.putBoolean("Limit low", getLowLimit());
		SmartDashboard.putData(this);
	}

	public boolean getLowLimit() {
		boolean value = !limitLow.get();
		if (value) {
			zeroEncoder();
		}
		return value;
	}

	public boolean getTopLimit() {
		return !limitTop.get();
	}
	
	public void zeroEncoder(){
	//	myEncoder.reset();
	}
	
	private boolean clawOpen = true;
	public void openClaw() {
		claw.set(Value.kForward);
		clawOpen = true;
	}
	public void closeClaw() {
		claw.set(Value.kReverse);
		clawOpen = false;
	}
	
	public void toggleClaw() {
		if (clawOpen) {
			closeClaw();
		} else {
			openClaw();
		}
	}
	
}
