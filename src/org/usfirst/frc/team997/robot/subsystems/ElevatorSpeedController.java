
package org.usfirst.frc.team997.robot.subsystems;

import static org.usfirst.frc.team997.robot.RobotMap.ElevatorMaxCurrent;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorSpeedController implements SpeedController {

	private SpeedController motor;
	private DoubleSolenoid brake;
	private DigitalInput High;
	private DigitalInput Low;
	
	public ElevatorSpeedController(SpeedController m, DoubleSolenoid b) {
		motor = m;
		brake = b;
	
	}
	
	public void addLimits( DigitalInput high, DigitalInput low) {
		
		High = high;
		Low = low;
		
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
	public boolean getInverted() {
		return motor.getInverted();
	}

	@Override
	public void setInverted(boolean set) {
		motor.setInverted(set);
	}

	@Override
	public double get() {
		return motor.get();
	}

	@Override
	public void set(double speed) {
		double lspeed = speed;
		double current = getElevatorCurrent();
		if (lspeed<0 && (!High.get() || (current  > ElevatorMaxCurrent))) {
			lspeed = 0;		
		} 
		if(lspeed>0 && (!Low.get()) || (current > ElevatorMaxCurrent) ) {
			lspeed = 0;		
		}
		if (Math.abs(lspeed)< .05) {
			motor.set(0);
			brake.set(DoubleSolenoid.Value.kForward);
		} else if (getElevatorCurrent()>ElevatorMaxCurrent) {
			motor.set(0);
		} else {
			brake.set(DoubleSolenoid.Value.kReverse);
			motor.set(lspeed);
		}
	}

	public void set(double arg0, byte arg1) {
		set(arg0);
	}
	
	private double maxCurrent1;
	private double maxCurrent2;
	PowerDistributionPanel panel = new PowerDistributionPanel();
	private double getElevatorCurrent() {
		double current1 = panel.getCurrent(RobotMap.ElevatorMotorCurrentSlot1);
		double current2 = panel.getCurrent(RobotMap.ElevatorMotorCurrentSlot2);
		if (current1> maxCurrent1) {
			maxCurrent1 = current1;
		}
		if (current2> maxCurrent2) {
			maxCurrent2 = current2;
		}
		SmartDashboard.putNumber("Elevator Max Current 1", maxCurrent1);
		SmartDashboard.putNumber("Elevator Max Current 2", maxCurrent2);
		SmartDashboard.putNumber("Elevator Current 1", current1);
		SmartDashboard.putNumber("Elevator Current 2", current2);
		double current = current1;
		if (current2> current) {
			current = current2;
		}
		SmartDashboard.putNumber("Elevator current", current);
		return current;
	}
}
