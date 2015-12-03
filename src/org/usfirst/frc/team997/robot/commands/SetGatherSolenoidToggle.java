package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetGatherSolenoidToggle extends Command {

	boolean mExtended;

	public SetGatherSolenoidToggle() {
		requires(Robot.myGatherer());


	}
	
	@Override
	protected void initialize() {
		
		
	}

	@Override
	protected void execute() {
		Robot.myGatherer().toggleSolenoid();
	}

	

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void interrupted() {

	}
	
	@Override
	protected void end() {

	}

}
