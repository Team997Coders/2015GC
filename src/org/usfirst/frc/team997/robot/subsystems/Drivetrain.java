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
	private DoubleSolenoid shiftSolenoid;

	private AccelMotor leftAccelMotor;
	private AccelMotor rightAccelMotor;

	private Encoder leftEnc;
	private Encoder rightEnc;
	public int gear;
	public static final int VoltageMode = 0;
	public static final int VelocityMode = 1;
	public static final int AccelorationMode = 2;

	private int currentMode;

	public Drivetrain(SpeedController left, SpeedController right,
			Encoder leftEncoder, Encoder rightEncoder, Gyro gyro) {
		try {
			leftMotor = left;
			rightMotor = right;
			leftEnc = leftEncoder;
			rightEnc = rightEncoder;
			leftEnc.setDistancePerPulse(DriveTrainDistancePerPulse);
			rightEnc.setDistancePerPulse(DriveTrainDistancePerPulse);

			leftAccelMotor = new AccelMotor(new VelMotor(leftMotor, leftEnc,
					driveVelCal, "left"), maxAccelDrive, "left");
			rightAccelMotor = new AccelMotor(new VelMotor(rightMotor, rightEnc,
					driveVelCal, "right"), maxAccelDrive, "right");

			shiftSolenoid = new DoubleSolenoid(RobotMap.ShifterSolenoidLow,
					RobotMap.ShifterSolenoidHigh);
			setMode(RobotMap.defaultDriveMode);
			myGyro = gyro;
			//myGyro.setSensitivity(RobotMap.GyroSensitivity);
			gear = 0;
			this.shift(0);
		} catch (Exception E) {
			E.printStackTrace();
			System.out.println("drivetrain subsystem");
		}
	}

	private void driveVoltage(double lSpeed, double rSpeed) {
		leftMotor.set(lSpeed);
		rightMotor.set(rSpeed);
	}
	
	public int getGear() {
		return this.gear;
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
			leftAccelMotor.start();
			rightAccelMotor.start();
		} else if (mode == AccelorationMode) {
			leftAccelMotor.setMaxAccel(RobotMap.maxAccelDrive);
			rightAccelMotor.setMaxAccel(RobotMap.maxAccelDrive);
			leftAccelMotor.start();
			rightAccelMotor.start();
		}
		currentMode = mode;
	}

	public void shift(int toShift) {
		if (toShift == 0 && gear == 1) {
			gear = 0;
			shiftSolenoid.set(DoubleSolenoid.Value.kForward);
		}

		if (toShift == 1 && gear == 0) {
			gear = 1;
			shiftSolenoid.set(DoubleSolenoid.Value.kReverse);

		}
		SmartDashboard.putNumber("ShiftGear", gear);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}

	public void SmartDashboard() {
		SmartDashboard.putString("Drivetrain Mode", modeAsString());
		SmartDashboard.putNumber("Left Drive Encoder",
				this.leftEnc.getDistance());
		SmartDashboard.putNumber("Right Drive Encoder",
				this.rightEnc.getDistance());
		SmartDashboard.putNumber("Left Drive rate Encoder",
				this.leftEnc.getRate());
		SmartDashboard.putNumber("Right Drive rate Encoder",
				this.rightEnc.getRate());
		SmartDashboard.putNumber("Gyro Angle", this.getGyro());
	}

	public String modeAsString() {
		if (currentMode == VoltageMode)
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

	public void resetGyro() {
		myGyro.reset();
	}

	public void resetEncoders() {
		leftEnc.reset();
		rightEnc.reset();
	}

	public double getRightEncoder() {
		return rightEnc.getDistance();
	}

	public double getLeftEncoder() {
		return leftEnc.getDistance();
	}

	public void gyroInit() {
		myGyro.initGyro();
		this.resetGyro();
	}
	
	public void initDrive() {
		this.resetEncoders();
	}
}
