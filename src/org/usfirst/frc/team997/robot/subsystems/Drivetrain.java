package org.usfirst.frc.team997.robot.subsystems;

import static org.usfirst.frc.team997.robot.RobotMap.DriveTrainDistancePerPulse;
import static org.usfirst.frc.team997.robot.RobotMap.driveVelCal;
import static org.usfirst.frc.team997.robot.RobotMap.leftDriveEncoder1;
import static org.usfirst.frc.team997.robot.RobotMap.leftDriveEncoder2;
import static org.usfirst.frc.team997.robot.RobotMap.maxAccelDrive;
import static org.usfirst.frc.team997.robot.RobotMap.rightDriveEncoder1;
import static org.usfirst.frc.team997.robot.RobotMap.rightDriveEncoder2;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Gyro myGyro;
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
	public Drivetrain(SpeedController left, SpeedController right, Encoder leftEncoder, Encoder rightEncoder, Gyro gyro) {
		leftMotor = left;
		rightMotor = right;
		leftEnc = leftEncoder;
		rightEnc = rightEncoder;
		leftEnc.setDistancePerPulse(DriveTrainDistancePerPulse);
		rightEnc.setDistancePerPulse(DriveTrainDistancePerPulse);
		leftAccelMotor = new AccelMotor(new VelMotor(leftMotor, leftEnc, driveVelCal), maxAccelDrive, "left");
		rightAccelMotor = new AccelMotor(new VelMotor(rightMotor, rightEnc, driveVelCal), maxAccelDrive, "right");
		currentMode = RobotMap.defaultDriveMode;
		myGyro = gyro;
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

	public void SmartDashboard() {
		SmartDashboard.putString("Drivetrain Mode", modeAsString());
	}
	
	public String modeAsString() {
		if(currentMode == VoltageMode)
			return "Voltage";
		if (currentMode == AccelorationMode) 
			return "Acceloration";
		if (currentMode == VelocityMode) 
			return "Velocity";
			return null;
	}
	
	public double getGyro() {
		return myGyro.getAngle();
	}
}

