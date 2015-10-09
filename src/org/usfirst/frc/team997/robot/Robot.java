
package org.usfirst.frc.team997.robot;

import static org.usfirst.frc.team997.robot.RobotMap.ElevatorMotorSlot;
import static org.usfirst.frc.team997.robot.RobotMap.ElevatorSolenoidAft;
import static org.usfirst.frc.team997.robot.RobotMap.ElevatorSolenoidFore;
import static org.usfirst.frc.team997.robot.RobotMap.dElev;
import static org.usfirst.frc.team997.robot.RobotMap.elevatorMaxAccel;
import static org.usfirst.frc.team997.robot.RobotMap.elevatorVelCal;
import static org.usfirst.frc.team997.robot.RobotMap.iElev;
import static org.usfirst.frc.team997.robot.RobotMap.leftDrive;
import static org.usfirst.frc.team997.robot.RobotMap.pElev;
import static org.usfirst.frc.team997.robot.RobotMap.rightDrive;

import org.usfirst.frc.team997.robot.commands.DriveStraightCommand;
import org.usfirst.frc.team997.robot.commands.SingleToteClockBump;
import org.usfirst.frc.team997.robot.commands.SingleToteClockNoBump;
import org.usfirst.frc.team997.robot.commands.SingleToteCounterClockBump;
import org.usfirst.frc.team997.robot.commands.SingleToteCounterClockNoBump;
import org.usfirst.frc.team997.robot.subsystems.Drivetrain;
import org.usfirst.frc.team997.robot.subsystems.Elevator;
import org.usfirst.frc.team997.robot.subsystems.ElevatorSpeedController;
import org.usfirst.frc.team997.robot.subsystems.Gatherer;
import org.usfirst.frc.team997.robot.subsystems.RSpeedController;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private static Compressor compressor;
	public static final Compressor compressor() {
		if(compressor == null) {
			try {
				compressor = new Compressor();
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("compressor");
			}
		}
		return compressor;
	}
	public static final PowerDistributionPanel pdp() {
		if(pdp == null) {
			try {
				pdp = new PowerDistributionPanel();
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("pdp");
			}
		}
		return pdp;
	}
	private static PowerDistributionPanel pdp;

	private static Gatherer myGatherer=null;
	public static final Gatherer myGatherer() {
		if(myGatherer == null) {   
			try {myGatherer = new Gatherer(
					new DoubleSolenoid(RobotMap.gatherSol1, RobotMap.gatherSol2),
					new Talon(RobotMap.gathererLeft),
					new Talon(RobotMap.gathererRight),
					RobotMap.ultrasonicSlot
					);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("gatherer");
			}
		}
		return myGatherer;
	}

	public static Elevator myElevator() {
		if(myElevator == null) {   
			try {myElevator = new Elevator(
					new ElevatorSpeedController(
							new Talon(ElevatorMotorSlot), 
							new DoubleSolenoid(ElevatorSolenoidFore, ElevatorSolenoidAft)), 
							
					elevatorVelCal, 
					elevatorMaxAccel, 
					//	elevatorEncoder1, 
				//	elvatorEncoder2, 
					RobotMap.LimitTopSlot,
					RobotMap.LimitLowSlot,
					pElev, 
					iElev, 
					dElev,
					RobotMap.clawSlot1,
					RobotMap.clawSlot2
				);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("elevator");
			}
		}
		return myElevator;
	}
	private static Elevator myElevator = null;

	public static Drivetrain subDriveTrain() {
		if(subDriveTrain == null) {   
			try {subDriveTrain  = new Drivetrain(
					new RSpeedController(new VictorSP(leftDrive),false),
					new RSpeedController(new VictorSP(rightDrive),true),
					new Encoder(RobotMap.leftDriveEncoder1,RobotMap.leftDriveEncoder2),
					new Encoder(RobotMap.rightDriveEncoder1, RobotMap.rightDriveEncoder2),
					new Gyro(RobotMap.gyroSlot)
					);
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("subDriveTrain")
				;
			}
		}
		return subDriveTrain;
	}

	private static Drivetrain subDriveTrain = null;

	public static OI oi;

	Command autonomousCommand;   
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	
	CameraServer server;
	SendableChooser AutoChooser = new SendableChooser();
	public void robotInit() {
		oi = new OI();
		// instantiate the command used for the autonomous period
		AutoChooser.addDefault("1 tote, counterclockwise, NO BUMB", new SingleToteCounterClockNoBump());
		AutoChooser.addObject("1 tote, clockwise, NO BUMB", new SingleToteClockNoBump());
		AutoChooser.addObject("1 tote, counterclockwise, BUMB", new SingleToteCounterClockBump());
		AutoChooser.addObject("1 tote, clockwise, BUMB", new SingleToteClockBump());
		AutoChooser.addObject("drive straight", new DriveStraightCommand());
		AutoChooser.addObject("do nothing", null);
		SmartDashboard.putData("Autonomous?", AutoChooser);
		compressor().start();
        pdp().clearStickyFaults();
        compressor().clearAllPCMStickyFaults();
	
		server = CameraServer.getInstance();
		server.setQuality(50);
//		// the camera name (ex "cam0") can be found through the roborio web interface
		server.startAutomaticCapture("cam1");
		subDriveTrain().gyroInit();
		subDriveTrain().shift(1);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		subDriveTrain.shift(1);
		autonomousCommand = (Command) AutoChooser.getSelected();
		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();

	}

	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	public void disabledInit(){

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		SmartDashboard();
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void SmartDashboard() {
//		myGatherer().SmartDashboard();
//		oi.SmartDashboard();
	subDriveTrain().SmartDashboard();
//		myElevator().SmartDashboard();
	}
}
