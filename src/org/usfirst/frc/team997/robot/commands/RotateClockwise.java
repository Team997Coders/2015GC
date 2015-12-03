package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateClockwise extends Command {
	private double actAngle;
	private boolean mClockwise;
	
    public RotateClockwise(double angle, boolean clockwise) {  	
        // Use requires() here to declare subsystem dependencies
        requires(Robot.subDriveTrain());
        actAngle = angle;
        if (actAngle == 180){
        	actAngle = 180;
        }
        if(actAngle == 90) {
        	actAngle = 65;
        }
        mClockwise = clockwise;
        	if (mClockwise) {
        		actAngle = -(actAngle);
        	}
        }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.subDriveTrain().resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.subDriveTrain().drive((.7), (-.7));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.subDriveTrain().getGyro()<actAngle;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.subDriveTrain().drive(0, 0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.subDriveTrain().drive(0, 0);

    }
}
