package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * YAY!
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//gatherer
	public static final int gathererLeft = 0;
	public static final int gathererRight = 1;
	public static final int leftDrive = 2;
	public static final int rightDrive = 3;
	public static final int ElevatorMotorSlot = 4;

	
	public static final int leftDriveEncoder1 = 1;
	public static final int leftDriveEncoder2 = 2;
	public static final int rightDriveEncoder1 = 3;
	public static final int rightDriveEncoder2 = 4;
	public static final int elevatorEncoder1 = 5;
	public static final int elvatorEncoder2 = 6;
	public static final int gyroSlot = 7;
	
	public static final int ElevatorSolenoidFore = 0;
	public static final int ElevatorSolenoidAft = 1;
	
	public static final int defaultDriveMode = Drivetrain.VoltageMode;
	public static final double driveVelCal = .1;
	public static final double maxAccelDrive = 10;
	public static final double pElev = 1;
	public static final double iElev = 0;
	public static final double dElev = 0;
	public static final double elevatorMaxAccel = .25;
	public static final double elevatorVelCal = .1;
	public static final double ElevatorDistancePerPulse =.00267;
	public static final double DriveTrainDistancePerPulse =.00267;
	public static final double absuluteElevatorTolerance = .01;
	public static final double percentTolerance = 1;
	public static final double ElevatorMaxCurrent = 2000;
	

	
	
}
