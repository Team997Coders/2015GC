package org.usfirst.frc.team997.robot.commands;

import static org.usfirst.frc.team997.robot.Robot.subDriveTrain;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
       requires(subDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double max = 1;
    	if (max<Math.abs(Robot.oi.getDesiredArcadeLeftSpeed())){
    		max = Math.abs(Robot.oi.getDesiredArcadeLeftSpeed());
    	}
    	if (max<Math.abs(Robot.oi.getDesiredArcadeRightSpeed())){
    		max = Math.abs(Robot.oi.getDesiredArcadeRightSpeed());
    	}
    	
    	subDriveTrain.drive(Robot.oi.getDesiredArcadeLeftSpeed()/max, Robot.oi.getDesiredArcadeRightSpeed()/max);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	subDriveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	subDriveTrain.drive(0, 0);
    }
}
