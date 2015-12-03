package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetElevatorPosition extends Command {

	double desiredPosition;

    public SetElevatorPosition(double target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.myElevator());
    	desiredPosition = -target;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.myElevator().setPIDtarget(0);
    	Robot.myElevator().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.myElevator().setPIDtarget(desiredPosition);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.myElevator().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ELEVATOR AUTO IS DONE", "DONE");
    	Robot.myElevator().disable();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.myElevator().disable();
    }
}
