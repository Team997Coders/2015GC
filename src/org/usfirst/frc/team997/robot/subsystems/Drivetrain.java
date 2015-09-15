package org.usfirst.frc.team997.robot.subsystems;

import static org.usfirst.frc.team997.robot.RobotMap.DriveTrainDistancePerPulse;
import static org.usfirst.frc.team997.robot.RobotMap.driveVelCal;
import static org.usfirst.frc.team997.robot.RobotMap.maxAccelDrive;


import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.ArcadeDrive;


import edu.wpi.first.wpilibj.DoubleSolenoid;
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
	public int gear; // 0 = Low, 1 = High
	public DoubleSolenoid shifter;
	
	public static final int VoltageMode = 0;
	public static final int VelocityMode = 1;
	public static final int AccelorationMode = 2;
	
	private int currentMode;
	public Drivetrain(SpeedController left, SpeedController right, Encoder leftEncoder, Encoder rightEncoder, DoubleSolenoid inShifter, Gyro gyro) {
		leftMotor = left;
		rightMotor = right;
		leftEnc = leftEncoder;
		rightEnc = rightEncoder;
		leftEnc.setDistancePerPulse(DriveTrainDistancePerPulse);
		rightEnc.setDistancePerPulse(DriveTrainDistancePerPulse);
		leftAccelMotor = new AccelMotor(new VelMotor(leftMotor, leftEnc, driveVelCal), maxAccelDrive, "left");
		rightAccelMotor = new AccelMotor(new VelMotor(rightMotor, rightEnc, driveVelCal), maxAccelDrive, "right");
		myGyro = gyro;
		shifter = inShifter;
		
		initDrive();
	}
	
	private void initDrive() {
		currentMode = RobotMap.defaultDriveMode;
		gear = 0;
		this.resetEncoders();
		myGyro.initGyro();
		this.setGear(0);
	}
	
	public void resetEncoders() {
		rightEnc.reset();
		leftEnc.reset();
	}
	
	public double getAverageEncoders() {
		return ((leftEnc.getRaw() + rightEnc.getRaw()) / 2.0);
	}
	
	private void driveVoltage(double lSpeed, double rSpeed) {
		leftMotor.set(lSpeed);
		rightMotor.set(rSpeed);
	}
	
	public void Stop () {
		leftMotor.set(0);
		rightMotor.set(0);
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
	
	// gear: 0 = low, 1 = high
	// assumes that kForward = Hi Gear, and kReverse = Low Gear
	public void setGear(int toGear) {
		if (shifter.get() == RobotMap.DriveShiftLowDirection && gear == 1){
			gear = 0;
		}
		else if (shifter.get() == RobotMap.DriveShiftHighDirection && gear == 0){
			gear = 1;
		}
		else {
			gear = 0;  // probably in neutral, set to low gear
			shifter.set(RobotMap.DriveShiftLowDirection);
		}
		
		if (gear == 0 && toGear == 1){
			gear = 1;
			shifter.set(RobotMap.DriveShiftHighDirection);
		}
		
		if (gear == 1 && toGear == 0){
			gear = 0;
			shifter.set(RobotMap.DriveShiftLowDirection);
		}
	}
	
	public void setNeutral() {
		gear = 0;
		shifter.set(DoubleSolenoid.Value.kOff);
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

