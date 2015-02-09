
package org.usfirst.frc.team997.robot;

import static org.usfirst.frc.team997.robot.RobotMap.ElevatorMotorSlot;
import static org.usfirst.frc.team997.robot.RobotMap.ElevatorSolenoidAft;
import static org.usfirst.frc.team997.robot.RobotMap.ElevatorSolenoidFore;
import static org.usfirst.frc.team997.robot.RobotMap.dElev;
import static org.usfirst.frc.team997.robot.RobotMap.elevatorEncoder1;
import static org.usfirst.frc.team997.robot.RobotMap.elevatorMaxAccel;
import static org.usfirst.frc.team997.robot.RobotMap.elevatorVelCal;
import static org.usfirst.frc.team997.robot.RobotMap.elevatorEncoder2;
import static org.usfirst.frc.team997.robot.RobotMap.iElev;
import static org.usfirst.frc.team997.robot.RobotMap.leftDrive;
import static org.usfirst.frc.team997.robot.RobotMap.pElev;
import static org.usfirst.frc.team997.robot.RobotMap.rightDrive;

import org.usfirst.frc.team997.robot.commands.AutonomousCommandGroup;
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

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	CameraServer server;

	// this will force the compressor to start, even it I haven't instanciated
	// a pneumatic solenoid yet.
	public static final Compressor myCompressor = new Compressor(0);
	
	// let's do the power panel too.  Just to be complete.
	public static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public static final Gatherer myGatherer = new Gatherer(
			new Talon(RobotMap.gathererLeft),
			new Talon(RobotMap.gathererRight));
	
	public static final Elevator myElevator = new Elevator(
			new ElevatorSpeedController(
					new Talon(ElevatorMotorSlot), 
					new DoubleSolenoid(ElevatorSolenoidFore, ElevatorSolenoidAft)), 
			elevatorVelCal, 
			elevatorMaxAccel,
			elevatorEncoder1, 
			elevatorEncoder2, 
			pElev, 
			iElev, 
			dElev);
	
	public static final Drivetrain subDriveTrain = new Drivetrain(
			new RSpeedController(new VictorSP(leftDrive),true),
			new RSpeedController(new VictorSP(rightDrive),true),
			new Encoder(RobotMap.leftDriveEncoder1,RobotMap.leftDriveEncoder2),
			new Encoder(RobotMap.rightDriveEncoder1, RobotMap.rightDriveEncoder2),
			new DoubleSolenoid(RobotMap.DriveShiftLowSolenoid, RobotMap.DriveShiftHighSolenoid),
			new Gyro(RobotMap.gyroSlot));
	
	public static OI oi;

    Command autonomousCommand;
    Command gatherIn;
    Command gatherOut;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousCommandGroup();
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
   }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
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
    	myGatherer.SmartDashboard();
    	oi.SmartDashboard();
    	subDriveTrain.SmartDashboard();
    	myElevator.SmartDashboard();
    }
}
