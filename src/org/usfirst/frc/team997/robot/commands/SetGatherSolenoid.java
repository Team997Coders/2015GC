package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetGatherSolenoid extends Command {

	boolean mExtended;

	public SetGatherSolenoid(boolean extended) {
		requires(Robot.myGatherer());
		
		mExtended = extended;


	}
	
	@Override
	protected void initialize() {
		
		
	}

	@Override
	protected void execute() {
		Robot.myGatherer().setSolenoid(mExtended);
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
