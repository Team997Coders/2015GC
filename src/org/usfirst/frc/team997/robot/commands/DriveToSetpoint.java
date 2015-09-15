package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team997.robot.Robot.subDriveTrain;

/**
 *
 */
public class DriveToSetpoint extends Command {
	
	double Setpoint;

    public DriveToSetpoint( double setpoint) {
    	requires(subDriveTrain);
    	Setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	subDriveTrain.drive(0.5 - gyroAdjust(), 0.5 + gyroAdjust());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return subDriveTrain.getAverageEncoders() > Setpoint;
    }

    // Called once after isFinished returns true
    protected void end() {
    	subDriveTrain.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	subDriveTrain.Stop();
    }
    
    private double gyroAdjust() {
        double i = subDriveTrain.getGyro() / 10;
        if (Math.abs(i) > .1) {
            if (i > 0) {
                return .1;
            } else if (i < 0) {
                return -.1;
            }
        } else {
            return i;
        }
        return 0;
    }
}
