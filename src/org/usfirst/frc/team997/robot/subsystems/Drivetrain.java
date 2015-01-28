package org.usfirst.frc.team997.robot.subsystems;

import static org.usfirst.frc.team997.robot.RobotMap.*;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private SpeedController leftMotor;
	private SpeedController rightMotor;
	private AccelMotor leftAccelMotor;
	private AccelMotor rightAccelMotor;
	private Encoder leftEnc;
	private Encoder rightEnc;
	
	public static final int VoltageMode = 0;
	public static final int VelocityMode = 1;
	public static final int AccelorationMode = 2;
	
	private int currentMode;
	public Drivetrain(SpeedController left, SpeedController right) {
		leftMotor = left;
		rightMotor = right;
		leftEnc = new Encoder(leftDriveEncoder1,leftDriveEncoder2);
		rightEnc = new Encoder(rightDriveEncoder1,rightDriveEncoder2);
		leftEnc.setDistancePerPulse(DriveTrainDistancePerPulse);
		rightEnc.setDistancePerPulse(DriveTrainDistancePerPulse);
		leftAccelMotor = new AccelMotor(new VelMotor(leftMotor, leftEnc, RobotMap.driveVelCal), RobotMap.maxAccelDrive);
		leftAccelMotor = new AccelMotor(new VelMotor(leftMotor, rightEnc, RobotMap.driveVelCal), RobotMap.maxAccelDrive);
		currentMode = RobotMap.defaultDriveMode;
	}
	
	private void driveVoltage(double lSpeed, double rSpeed) {
		leftMotor.set(lSpeed);
		rightMotor.set(rSpeed);
	}
	
	private void driveAcceleration(double lSpeed, double rSpeed) {
		leftAccelMotor.setDesiredVelocity(lSpeed);
		rightAccelMotor.setDesiredVelocity(rSpeed);
	}
	
	public void drive(double lSpeed, double rSpeed) {
		if (currentMode == VoltageMode) {
			driveVoltage(lSpeed, rSpeed);
		} else {
			driveAcceleration(lSpeed, rSpeed);
		}
	}
	
	public void setMode(int mode) {
		if (mode == VelocityMode) {
			leftAccelMotor.setMaxAccel(0);
			rightAccelMotor.setMaxAccel(0);
		} else if (mode == AccelorationMode) {
			leftAccelMotor.setMaxAccel(RobotMap.maxAccelDrive);
			rightAccelMotor.setMaxAccel(RobotMap.maxAccelDrive);
		}
		currentMode = mode;
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }
}

