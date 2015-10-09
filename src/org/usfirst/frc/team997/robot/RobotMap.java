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
	
	//motor controllers
	public static final int gathererLeft = 0;
	public static final int gathererRight = 1;
	public static final int leftDrive = 3;
	public static final int rightDrive = 2;
	public static final int ElevatorMotorSlot = 4;

	// digital inputs
	public static final int LimitTopSlot = 2;
	public static final int LimitLowSlot = 7;
	public static final int rightDriveEncoder1 = 0;
	public static final int rightDriveEncoder2 = 1;
//	public static final int elevatorEncoder1 = 3;
//	public static final int elvatorEncoder2 = 4;
	public static final int leftDriveEncoder1 = 5;
	public static final int leftDriveEncoder2 = 6;
	
	// analog input
	public static final int gyroSlot = 1;
	public static final int ultrasonicSlot = 3;
	
	// pneumatics
	public static final int ElevatorSolenoidFore = 2;
	public static final int ElevatorSolenoidAft = 3;
	public static final int ShifterSolenoidLow = 0;
	public static final int ShifterSolenoidHigh = 1;
	public static int gatherSol1 = 4;
	public static int gatherSol2 = 5;
	
	// constants
	public static double GyroSensitivity = .007;
	public static final int defaultDriveMode = Drivetrain.VelocityMode;
	public static final double driveVelCal = .1;
	public static final double maxAccelDrive = 2;
	public static final double pElev = 10;
	public static final double iElev = 0;
	public static final double dElev = 0;
	public static final double elevatorMaxAccel = .25;
	public static final double elevatorVelCal = 0;
	public static final double ElevatorDistancePerPulse =.0001977777;
	public static final double DriveTrainDistancePerPulse =.000030409;
	public static final double absuluteElevatorTolerance = .02;
	public static final double percentTolerance = 2;
	public static final double ElevatorMaxCurrent = 65;
	public static final int ElevatorMotorCurrentSlot1 = 12;
	public static final int ElevatorMotorCurrentSlot2 = 13;
	public static final double CalibrationUltrasonic = 102.396;
	
	public static final int clawSlot2 = 7;
	public static final int clawSlot1 = 6;
	

	

	
	
}
