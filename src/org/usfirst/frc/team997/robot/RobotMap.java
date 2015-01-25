package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.subsystems.Drivetrain;

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
	public static final int gatherer = 0;
	public static final int leftDrive = 2;
	public static final int rightDrive = 3;
	
	public static final int leftDriveEncoder1 = 1;
	public static final int leftDriveEncoder2 = 2;
	public static final int rightDriveEncoder1 = 3;
	public static final int rightDriveEncoder2 = 4;
	public static final int defaultDriveMode = Drivetrain.VoltageMode;
	public static double driveVelCal = .1;
	public static double maxAccelDrive = 10;
}
