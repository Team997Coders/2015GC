package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClaw extends Command {

	public ToggleClaw() {
		requires(Robot.myElevator());
	}
	@Override
	protected void end() {

	}

	@Override
	protected void execute() {
		Robot.myElevator().toggleClaw();
	}

	@Override
	protected void initialize() {
	
	}	
	@Override
	protected void interrupted() {
	
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
